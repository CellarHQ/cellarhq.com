package com.cellarhq.auth.services.photo

import com.amazonaws.services.s3.model.ObjectMetadata
import com.cellarhq.auth.services.photo.model.PhotoDetails
import com.cellarhq.auth.services.photo.model.ResizeCommand
import com.cellarhq.auth.services.photo.writer.PhotoWriteStrategy
import com.cellarhq.common.services.S3Service
import com.cellarhq.domain.Photo
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.records.PhotoRecord
import com.cellarhq.jooq.BaseJooqService
import com.google.common.io.Files
import com.google.inject.Inject
import groovy.transform.CompileStatic
import io.netty.buffer.ByteBufInputStream
import org.imgscalr.Scalr
import org.jooq.DSLContext
import ratpack.exec.Blocking
import ratpack.exec.Promise
import ratpack.form.UploadedFile

import javax.imageio.ImageIO
import javax.sql.DataSource
import java.awt.image.BufferedImage
import java.time.LocalDate

import static com.cellarhq.generated.Tables.*
import static com.cellarhq.generated.Tables.CELLAR

/**
 * Provides a generic interface for working with photos in the application.
 *
 * The expectation is that when saving Photos, it is always being done as part of another transaction, so it never
 * will have its own jOOQ DSLContext object.
 *
 * In order to use this service:
 *
 * <code>
 *     DrinkRecord drink = create.newRecord(DRINK)
 *     PhotoRecord photo = photoService.createPhotoRecord(create, Photo.Type.DRINK, uploadedFile, [
 *             new ResizeCommand(Photo.Size.LARGE, 500)
 *     ])
 *     photo.description = 'added during documentation'
 *     photo.store()
 *
 *     drink.photoId = photo.id
 *     drink.store()
 * </code>
 *
 * If we're using the AmazonPhotoWriteStrategy, in the above code, we're taking an uploaded file and first putting the
 * original file into S3, then resizing the original according to the ResizeCommand and putting that into S3 as well.
 * Once all resizing has been done and files have been transferred into S3, the paths and their metadata
 * (width & height) will be applied to the photo record and returned to be attached to the DrinkRecord.
 *
 * @todo If resize is equal to or greater than original image size, do not resize: Only use original image.
 */
class PhotoService extends BaseJooqService {

  private final S3Service s3Service
  private final PhotoWriteStrategy writeStrategy

  @Inject
  PhotoService(DataSource dataSource,
               S3Service s3Service,
               PhotoWriteStrategy writeStrategy) {
    super(dataSource)

    this.s3Service = s3Service
    this.writeStrategy = writeStrategy

  }

  Promise<Photo> findByCellarId(Long cellarId) {
   Blocking.get {
      jooq { DSLContext create ->
        create.select(PHOTO.fields())
              .from(PHOTO)
              .join(CELLAR).onKey()
              .where(CELLAR.ID.eq(cellarId))
              .fetchOneInto(Photo)
      }
    }
  }

  Promise<Photo> findByOrganizationAndDrink(String brewerySlug, String beerSlug) {
   Blocking.get {
      jooq { DSLContext create ->
        create.select(PHOTO.fields())
          .from(PHOTO)
          .join(DRINK).onKey()
          .join(ORGANIZATION).onKey()
          .where(DRINK.SLUG.eq(beerSlug).and(ORGANIZATION.SLUG.eq(brewerySlug)))
          .fetchOneInto(Photo)
      }
    }
  }

  Promise<Photo> findByOrganization(String brewerySlug) {
   Blocking.get {
      jooq { DSLContext create ->
        create.select(PHOTO.fields())
          .from(PHOTO)
          .join(ORGANIZATION).onKey()
          .where(ORGANIZATION.SLUG.eq(brewerySlug))
          .fetchOneInto(Photo)
      }
    }
  }

  Promise<Photo> findByCellarSlug(String cellarSlug) {
   Blocking.get {
      jooq { DSLContext create ->
        create.select(PHOTO.fields())
              .from(PHOTO)
              .join(CELLAR).onKey()
              .where(CELLAR.SLUG.eq(cellarSlug))
              .fetchOneInto(Photo)
      }
    }
  }

  /**
   * Creates a photo record from a remote photo URL. This method does not save the record.
   */
  PhotoRecord createPhotoRecord(DSLContext create,
                                Photo.Type type,
                                String pictureUrl,
                                List<ResizeCommand> resizeCommands = null) {

    PhotoRecord photoRecord = create.newRecord(PHOTO)
    photoRecord.originalUrl = pictureUrl

    if (resizeCommands) {
      PhotoDetails photoDetails = resize(pictureUrl, type, resizeCommands)
      photoDetails.applyTo(photoRecord)
    }

    return photoRecord
  }

  /**
   * Creates a photo record from an uploaded file. This method does not save the record.
   */
  PhotoRecord createPhotoRecord(DSLContext create,
                                Photo.Type type,
                                UploadedFile uploadedFile,
                                List<ResizeCommand> resizeCommands = null) {

    String fileExtension = Files.getFileExtension(uploadedFile.fileName)
    String key = generateKey(type, fileExtension)
    s3Service.upload(key, uploadedFile.inputStream, new ObjectMetadata(
      contentLength: uploadedFile.bytes.size()
    ))

    PhotoRecord photoRecord = create.newRecord(PHOTO)
    photoRecord.originalUrl = s3Service.getObjectUrl(key)

    if (resizeCommands) {
      PhotoDetails photoDetails = resize(uploadedFile.inputStream, fileExtension, type, resizeCommands)
      photoDetails.applyTo(photoRecord)
    }

    return photoRecord
  }

  PhotoDetails resize(String photoUrl, Photo.Type type, List<ResizeCommand> resizeCommands) {
    BufferedImage image = ImageIO.read(new URL(photoUrl))
    String extension = Files.getFileExtension(photoUrl)

    return new PhotoDetails(resizeCommands.collect { ResizeCommand command ->
      return resizeAndWrite(generateKey(type, extension), image, command)
    })
  }

  PhotoDetails resize(InputStream is, String extension, Photo.Type type, List<ResizeCommand> resizeCommands) {
    ByteArrayInputStream baos
    if (is instanceof ByteBufInputStream) {
      // Something is totally fucked up with file uploads in Ratpack. Haven't figured out what yet, but it seems
      // that either Ratpack or Netty marks the buffer at the last index, so it can never be read from.
      baos = new ByteArrayInputStream((byte[]) ((ByteBufInputStream) is).bytes)
    } else {
      baos = new ByteArrayInputStream(is.bytes)
    }
    BufferedImage image = ImageIO.read(baos)

    return new PhotoDetails(resizeCommands.collect { ResizeCommand command ->
      return resizeAndWrite(generateKey(type, extension), image, command)
    })
  }

  private PhotoDetails.Detail resizeAndWrite(String key, BufferedImage image, ResizeCommand command) {
    BufferedImage resized = Scalr.resize(image, command.width)

    return new PhotoDetails.Detail(
      command.size,
      writeStrategy.write(key, resized),
      resized.width,
      resized.height
    )
  }

  private static String generateKey(Photo.Type type, String extension) {
    String root = type.toString().toLowerCase()
    LocalDate now = LocalDate.now()
    String uuid = UUID.randomUUID().toString()
    return "${root}/${now}/${uuid}.${extension}"
  }
}
