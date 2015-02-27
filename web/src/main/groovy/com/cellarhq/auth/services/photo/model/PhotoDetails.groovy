package com.cellarhq.auth.services.photo.model

import com.cellarhq.domain.Photo
import com.cellarhq.generated.tables.records.PhotoRecord
import groovy.transform.CompileStatic

@CompileStatic
class PhotoDetails {

    static class Detail {
        final Photo.Size size
        final String url
        final short width
        final short height

        Detail(Photo.Size size, String url, int width, int height) {
            this.size = size
            this.url = url
            this.width = Short.valueOf(width.toString())
            this.height = Short.valueOf(height.toString())
        }
    }

    final List<Detail> details

    PhotoDetails(List<Detail> details) {
        this.details = details
    }

    Detail size(Photo.Size size) {
        return details.find { it.size == size }
    }

    void applyTo(PhotoRecord photoRecord) {
        size(Photo.Size.THUMB)?.with {
            photoRecord.thumbUrl = it.url
            photoRecord.thumbWidth = it.width
            photoRecord.thumbHeight = it.height
        }
        size(Photo.Size.LARGE)?.with {
            photoRecord.largeUrl = it.url
            photoRecord.largeWidth = it.width
            photoRecord.largeHeight = it.height
        }
    }
}
