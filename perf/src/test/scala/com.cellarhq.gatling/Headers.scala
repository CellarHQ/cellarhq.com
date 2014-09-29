package com.cellarhq.gatling

object Headers {

  val any = Map(
    "Accept" -> "*/*"
  )

  val noCache = Map(
    """Cache-Control""" -> """no-cache""",
    """Pragma""" -> """no-cache"""
  )

  val resource = new {
    val css = Map(
      "Accept" -> "text/css,*/*;q=0.1"
    )
    val png = Map(
      "Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5"
    )
    val gif = Map(
      "Accept" -> "image/gif,image/*;q=0.8,*/*;q=0.5"
    )
  }

  val get = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
  )

  val post = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Content-Type" -> "application/x-www-form-urlencoded"
  )

  val xhr = Map(
    "X-Requested-With" -> "XMLHttpRequest"
  )

  val api = new {
    val get = Map(
      "Accept" -> "application/json, text/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01",
      "Accept-Charset" -> "ISO-8859-1,utf-8;q=0.7,*;q=0.7",
      "Accept-Encoding" -> "gzip,deflate",
      "Accept-Language" -> "en-us;q=0.7,en;q=0.3"
    ) ++ xhr

    val post = get ++ Map(
      "Content-Type" -> "application/json"
    )
  }
}
