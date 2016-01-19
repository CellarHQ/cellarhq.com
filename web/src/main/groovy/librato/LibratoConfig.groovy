package librato


class LibratoConfig implements Serializable {
  String email
  String token
  String sourceIdentifier

  boolean getIsValid() {
    email && token && sourceIdentifier
  }
}
