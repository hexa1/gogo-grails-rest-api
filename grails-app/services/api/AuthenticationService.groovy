package api

/**
 * Encapsulates Spring Security Token-based authentication functions
 */
class AuthenticationService {
  def springSecurityService

  /**
   * Get the currently logged in user
   */
  def getLoggedInUser() {
    def user = springSecurityService.currentUser
    user
  } // getLoggedInUser
} // AuthService
