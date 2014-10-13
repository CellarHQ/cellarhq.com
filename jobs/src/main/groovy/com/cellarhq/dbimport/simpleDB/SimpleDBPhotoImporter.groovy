package com.cellarhq.dbimport.simpledb

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.records.CellarRecord
import com.cellarhq.generated.tables.records.DrinkRecord
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.cellarhq.generated.tables.records.PhotoRecord
import org.jooq.DSLContext
import org.jooq.UpdatableRecord
import org.jooq.exception.DataAccessException

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.sql.Timestamp
import java.time.LocalDateTime

class SimpleDBPhotoImporter {

    static String ATTR_PHOTO_URL = 'photoUrl'
    static String ATTR_PHOTO_FULL_SIZE_URL = 'photoFullSizeUrl'

    AmazonHelper amazonHelper = new AmazonHelper()
    DSLContext create

    SimpleDBPhotoImporter(DSLContext create) {
        this.create = create
    }

    void importPhoto(Item item, UpdatableRecord ownerRecord) {
        if (!hasPhoto(item)) {
            return
        }

        PhotoRecord photoRecord = create.newRecord(Tables.PHOTO).with { PhotoRecord self ->
            version = 0
            createdDate = Timestamp.valueOf(LocalDateTime.now())
            modifiedDate = createdDate
            originalUrl = getImageUrl(amazonHelper.getAttribute(item.attributes, ATTR_PHOTO_FULL_SIZE_URL).trim())
            thumbUrl = getImageUrl(amazonHelper.getAttribute(item.attributes, ATTR_PHOTO_URL).trim())
            largeUrl = thumbUrl
            description = 'imported from simpledb'

            return self
        }

        BufferedImage originalImage = getBufferedImage(photoRecord.originalUrl)
        if (!originalImage) {
            println('Could not read original image; aborting import of photo')
            return
        }

        BufferedImage thumbImage = getBufferedImage(photoRecord.thumbUrl)
        if (!thumbImage) {
            println('Could not read thumb image; replacing with original')
            photoRecord.thumbUrl = photoRecord.originalUrl
            photoRecord.largeUrl = photoRecord.originalUrl
            thumbImage = originalImage
        }

        photoRecord.with {
            thumbWidth = largeWidth = thumbImage.width
            thumbHeight = largeHeight = thumbImage.height
        }

        try {
            photoRecord.store()

            switch (ownerRecord.class) {
                case CellarRecord:
                    ((CellarRecord) ownerRecord).photoId = photoRecord.id
                    break

                case DrinkRecord:
                    ((DrinkRecord) ownerRecord).photoId = photoRecord.id
                    break

                case OrganizationRecord:
                    ((OrganizationRecord) ownerRecord).photoId = photoRecord.id
                    break
            }
        } catch (DataAccessException e) {
            println "Error inserting photo because ${e.message} with ${photoRecord}"
        }
    }

    private boolean hasPhoto(Item item) {
        return amazonHelper.getAttribute(item.attributes, ATTR_PHOTO_FULL_SIZE_URL).trim()
    }

    private String getImageUrl(String path) {
        return "http://storage-www.cellarhq.com.s3-website-us-east-1.amazonaws.com/${path}"
    }

    private BufferedImage getBufferedImage(String url) {
        try {
            return ImageIO.read(new URL(url))
        } catch (IOException e) {
            return null
        }
    }
}
