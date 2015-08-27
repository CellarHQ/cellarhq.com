package com.cellarhq.auth.profiles

import org.pac4j.oauth.profile.twitter.TwitterProfile

class TwitterCellarHQProfile extends TwitterProfile implements CellarHQProfile {
  Long cellarId
}
