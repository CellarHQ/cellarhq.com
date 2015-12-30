package com.cellarhq.auth.profiles

import org.pac4j.http.profile.HttpProfile

interface CellarHQProfile {
  Long getCellarId()
}

class HttpCellarHQProfile extends HttpProfile implements CellarHQProfile {
  Long cellarId
}
