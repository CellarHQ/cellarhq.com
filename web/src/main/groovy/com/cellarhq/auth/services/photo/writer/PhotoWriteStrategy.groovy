package com.cellarhq.auth.services.photo.writer

import java.awt.image.BufferedImage

interface PhotoWriteStrategy {

    /**
     * Writes the given BufferedImage to a store, returning the web-accessible URL.
     * @param key
     * @param image
     * @return
     */
    String write(String key, BufferedImage image) throws PhotoWriteFailedException
}
