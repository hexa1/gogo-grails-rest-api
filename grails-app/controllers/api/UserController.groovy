package api

import grails.plugin.springsecurity.annotation.Secured

/**
 * Handles user life-cycle operations.
 * Note: login, logout, validate actions are provided by the
 * sprint-security-rest plugin
 */
// By default, require full token authentication for
// all controller methods
@Secured(['isFullyAuthenticated()'])
class UserController {
  def authenticationService
  def notificationService
  def securityService

  static allowedMethods = [
    signup  : 'POST',
    reset   : 'POST',
    account : ['GET', 'PUT']
    ]

  /**
   *
   */
  def index() {
    return Api.BadRequest(this)
  }

  /**
   *
   */
  @Secured(["permitAll"])
  def signup() {
    def email    = params.email
    def username = email
    def password = params.password
    if (!email || !password) {
      return Api.UsernamePasswordNotSpecified(this)
    }
    User newUser = User.createUserWithEmail(email, password)
    newUser.validate()
    // User is not valid
    if (newUser.hasErrors()) {
      def errors = newUser.errors
      if ('unique' == errors?.getFieldError('email')?.code) {
        return Api.EmailUsed(this)
      } else if ('email.invalid' == errors.getFieldError('email')?.code) {
        return Api.IncorrectFormat(this, 'email')
      } else if ('matches.invalid' == errors?.getFieldError('password')?.code) {
        return Api.IncorrectPassword(this, 'password', 'Must be at least 6 characters with 1 numeric character.')
      } else {
        return Api.Unexpected(this)
      }
    }
    User.withTransaction {
      newUser.save flush:true
    }
    return Api.ok(this)
  }

  /**
   *
   */
  @Secured(["permitAll"])
  def reset() {
    if (!params.email) {
      return Api.MissingParameter(this, "email")
    }
    User user = User.findByEmail(params.email)
    if (user) {
      String newPassword = securityService.generateStrongPassword()
      User.withTransaction {
        user.password = newPassword
        user.save(flush:true)
      }
      notificationService.sendEmailResetPassword(user.email, newPassword)
      return Api.ok(this)
    }
    // Don't encourage system introspection by hackers
    return Api.ok(this)
  }

  /**
   *
   */
  def account() {
    User user = authenticationService.getLoggedInUser()
    log.debug("User: ${user}")
    if (request.method == 'GET') {
      def responseBody = [
        'firstName' : user.firstName,
        'lastName'  : user.lastName,
        'email'     : user.email
      ]
      return Api.ok(this, responseBody)
    }
    if (request.method == 'PUT') {
      if (!params.firstName && !params.lastName && !params.email && !params.password) {
        return Api.MissingParameter(this, 'some of  First Name, Last Name, Email, Password')
      }
      User.withTransaction {
        if (params.firstName) {
          user.setFirstName(params.firstName)
        }
        if (params.lastName) {
          user.setLastName(params.lastName)
        }
        if (params.email) {
          user.setEmail(params.email)
        }
        if (params.password) {
          user.setPassword(params.password)
        }
        user.save(flush: true)
      }
      log.debug("Account details updated")
      def responseBody = [
        'firstName' : user.firstName,
        'lastName'  : user.lastName,
        'email'     : user.email
      ]
      return Api.ok(this, responseBody)
    }
    return Api.ok(this)
  }
}
