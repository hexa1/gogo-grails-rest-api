package api

import grails.plugin.springsecurity.annotation.Secured

@Secured(['denyAll'])
class ErrorController {
  def index() { }

  def error400() {
    return Api.error (this, 400)
  }

  def error401() {
    return Api.error (this, 401)
  }

  def error402() {
    return Api.error (this, 402)
  }

  def error403() {
    return Api.error (this, 403)
  }

  def error404() {
    return Api.error (this, 404)
  }

  def error405() {
    return Api.error (this, 405)
  }

  def error500() {
    return Api.error (this, 500)
  }
} // ErrorController
