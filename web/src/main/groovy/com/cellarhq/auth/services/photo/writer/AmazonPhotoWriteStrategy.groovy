package com.cellarhq.auth.services.photo.writer

import com.amazonaws.AmazonClientException
import com.amazonaws.services.s3.model.ObjectMetadata
import com.cellarhq.common.services.S3Service
import com.cellarhq.util.LogUtil
import com.google.common.io.Files
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

@Slf4j
@CompileStatic
class AmazonPhotoWriteStrategy implements PhotoWriteStrategy {

    private final S3Service s3Service

    @Inject
    AmazonPhotoWriteStrategy(S3Service s3Service) {
        this.s3Service = s3Service
    }

    @Override
    String write(String key, BufferedImage image) throws PhotoWriteFailedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ImageIO.write(image, Files.getFileExtension(key), baos)
        InputStream is = new ByteArrayInputStream(baos.toByteArray())

        // TODO: Track who uploaded the photo.
        ObjectMetadata metadata = new ObjectMetadata(
                contentLength: baos.size()
        )

        try {
            s3Service.upload(key, is, metadata)
            log.info(LogUtil.toLog('PhotoUploaded', [
                    s3Key: key
            ]))
        } catch (AmazonClientException ace) {
            log.error(LogUtil.toLog('AmazonPhotoWriteFailure', [
                    msg: 'An exception occurred while putting a new image into S3'
            ]), ace)
            throw new PhotoWriteFailedException(ace)
        }

        return s3Service.getObjectUrl(key)
    }
}
