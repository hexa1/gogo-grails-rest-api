package api.auth

import org.bson.types.ObjectId

/**
 * TODO: Move to Redis backend
 */
class AuthenticationToken {

  ObjectId id
  String username
  String tokenValue
  Date   refreshed = new Date()

  static mapping = {
    version false
  }

  /**
   * Every time it is accessed, refresh
   */
  def afterLoad() {
    refreshed = new Date()
    it?.save()
  }
}
