package com.cellarhq.services.photo.model

import com.cellarhq.domain.Photo
import groovy.transform.CompileStatic

@CompileStatic
class ResizeCommand {
    Photo.Size size
    int width

    ResizeCommand(Photo.Size size, int width) {
        this.size = size
        this.width = width
    }
}
