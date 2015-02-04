package api

import grails.plugin.springsecurity.annotation.Secured

/**
 * Demonstrates use of Spring Security annotations
 *
 * @see http://grails-plugins.github.io/grails-spring-security-core/guide/requestMappings.html
 */
// Class-level security annotation:
@Secured(['denyAll'])
class TestAccessController {
  /**
   * Spring Security Annotation Standard Expressions
   *
   * hasRole(role) Returns true if the current principal has the specified role.
   * hasAnyRole([role1,role2]) Returns true if the current principal has any of the supplied roles (given as a comma-separated list of strings)
   * principal Allows direct access to the principal object representing the current user
   * authentication  Allows direct access to the current Authentication object obtained from the SecurityContext
   * permitAll Always evaluates to true
   * denyAll Always evaluates to false
   * isAnonymous() Returns true if the current principal is an anonymous user
   * isRememberMe()  Returns true if the current principal is a remember-me user
   * isAuthenticated() Returns true if the user is not anonymous
   * isFullyAuthenticated()  Returns true if the user is not an anonymous or a remember-me user
   * request the HTTP request, allowing expressions such as "isFullyAuthenticated() or request.getMethod().equals('OPTIONS')"
   *
   * Transitional or Expression
   * ROLE_ADMIN                         hasRole('ROLE_ADMIN')
   * ROLE_USER,ROLE_ADMIN               hasAnyRole('ROLE_USER','ROLE_ADMIN')
   * ROLE_ADMIN,IS_AUTHENTICATED_FULLY  hasRole('ROLE_ADMIN') and isFullyAuthenticated()
   * IS_AUTHENTICATED_ANONYMOUSLY       permitAll
   * IS_AUTHENTICATED_REMEMBERED        isAuthenticated() or isRememberMe()
   * IS_AUTHENTICATED_FULLY             isFullyAuthenticated()
   *
   */
  @Secured(["hasRole('ROLE_ADMIN')"])
  def test() {
    render "Only admins can see this"
  }

  def index() {
    render "Test!"
  }

  @Secured(['ROLE_ADMIN'])
  def admin() {
    render 'you have ROLE_ADMIN'
  }

  @Secured(['ROLE_ADMIN', 'ROLE_SUPERUSER'])
  def adminEither() {
    render 'you have ROLE_ADMIN or SUPERUSER'
  }

  @Secured(['permitAll'])
  def anybody() {
    render 'anyone can see this, or can they?'
  }
}
