package com.cellarhq.services.photo

import static ratpack.rx.RxRatpack.observe

import com.amazonaws.services.s3.model.ObjectMetadata
import com.cellarhq.domain.Photo
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.records.PhotoRecord
import com.cellarhq.services.BaseJooqService
import com.cellarhq.services.S3Service
import com.cellarhq.services.photo.model.PhotoDetails
import com.cellarhq.services.photo.model.ResizeCommand
import com.cellarhq.services.photo.writer.PhotoWriteStrategy
import com.google.common.io.Files
import com.google.inject.Inject
import io.netty.buffer.ByteBufInputStream
import org.imgscalr.Scalr
import org.jooq.DSLContext
import ratpack.exec.ExecControl
import ratpack.form.UploadedFile

import javax.imageio.ImageIO
import javax.sql.DataSource
import java.awt.image.BufferedImage
import java.time.LocalDate

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
                 ExecControl execControl,
                 S3Service s3Service,
                 PhotoWriteStrategy writeStrategy) {
        super(dataSource, execControl)

        this.s3Service = s3Service
        this.writeStrategy = writeStrategy

    }

    rx.Observable<Photo> findByCellarId(Long cellarId) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select(Tables.PHOTO.fields())
                        .from(Tables.PHOTO)
                        .join(Tables.CELLAR).onKey()
                        .where(Tables.CELLAR.ID.eq(cellarId))
                        .fetchOneInto(Photo)
            }
        }).asObservable()
    }

    /**
     * Creates a photo record from a remote photo URL. This method does not save the record.
     */
    PhotoRecord createPhotoRecord(DSLContext create,
                                  Photo.Type type,
                                  String pictureUrl,
                                  List<ResizeCommand> resizeCommands = null) {

        PhotoRecord photoRecord = create.newRecord(Tables.PHOTO)
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

        PhotoRecord photoRecord = create.newRecord(Tables.PHOTO)
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
            baos = new ByteArrayInputStream((byte[]) ((ByteBufInputStream) is).buffer.array())
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
