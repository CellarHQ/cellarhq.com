package com.cellarhq.auth.services.photo.writer

class PhotoWriteFailedException extends RuntimeException {

  PhotoWriteFailedException() {
    super('Could not write photo')
  }

  PhotoWriteFailedException(Throwable cause) {
    super('Could not write photo', cause)
  }
}
