package librato

/**
 * Converts Heroku formatted envrionment variables for librato into
 * a config source for ratpack.
 */
class HerokuLibratoConfigUtility {

  static Map<String, String> getLibratoProperties() {

    String source = System.getenv('LIBRATO_SOURCE')
    String token  = System.getenv('LIBRATO_TOKEN')
    String user = System.getenv('LIBRATO_USER')

    Map<String, String> properties = [:]

    if (source) { properties.put('librato.sourceIdentifier', source) }
    if (token) { properties.put('librato.token', token) }
    if (user) { properties.put('librato.email', user) }

    return properties
  }
}
