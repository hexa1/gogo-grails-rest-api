package api

import grails.plugin.springsecurity.annotation.Secured

class StatusController {
  static allowedMethods = [
    index  : 'GET'
  ]

  def grailsApplication

  /**
   *
   */
  @Secured(["permitAll"])
  def index() {
    def statusResponse = [
      'api' : [
        'version'       : grailsApplication.metadata['app.version'],
        'build'         : grailsApplication.metadata['app.buildId'],
        'lastUpdated'   : grailsApplication.metadata['app.buildDate']],
      'status' : "up"
    ]
    Api.ok(this, statusResponse)
  } // index
} // StatusController
