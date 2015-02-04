package api

import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import grails.converters.JSON

/**
 * REST API Responses
 *
 * @todo This does not take into account content negotiation (json/xml)
 * @see http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletResponse.html
 */
class Api {
  final static String nothing = ''
  final static String emptyJson = '{}'

  /**
   *
   */
  static UsernamePasswordNotSpecified(c) {
    c.response.status = 400
    c.render new ApiResponse('Email and password must be specified', 1010)
  }

  /**
   *
   */
  static IncorrectUsernameOrPassword(c) {
    c.response.status = 401
    c.render new ApiResponse('Username / Password are incorrect', 1020)
  }

  /**
   *
   */
  static IncorrectFormat(c, field=null, more=null) {
    c.response.status = 400
    def str = 'Incorrect format.'
    if (field != null) {
      str = field.toUpperCase() + ' is not in correct format.'
    }
    if (more != null) { str += ' ' + more }
    c.render new ApiResponse(str, 1020)
  }

  /**
   *
   */
  static Unexpected(c, e = null) {
    c.response.status = 400
    def str = ('Unexpected Error ' + e?.message)?.trim()
    c.render new ApiResponse(str)
  }

  /**
   *
   */
  static Exception(c, e = null) {
    c.response.status = 400
    c.render new ApiResponse('Unexpected Error: ' + e?.message)
  }

  /**
   *
   */
  static EmailUsed(c) {
    c.response.status = 400
    c.render new ApiResponse('This email is already being used. Please choose another email.', 2001)
  }

  /**
   *
   */
  static MissingToken(c) {
    c.response.status = 400
    c.render new ApiResponse('Invalid or missing authentication token', 1010)
  }

  /**
   *
   */
  static MissingParameter(c, param=null, paramList=null) {
    c.response.status = 400
    def str = 'Missing parameter'
    if (param != null) {
      str += ' ' + param
    }
    c.render new ApiResponse(str, 1010, paramList)
  }

  /**
   *
   */
  static ProviderNotSupported(c) {
    c.response.status = 400
    c.render new ApiResponse('Cloud Account type not supported', 3004)
  }

  /**
   *
   */
  static InvalidProviderId(c) {
    c.response.status = 400
    c.render new ApiResponse('Invalid Provider Id specified', 3000)
  }

  /**
   *
   */
  static ProviderNotFound(c) {
    c.response.status = 400
    c.render new ApiResponse('Cloud Account id not found')
  }

  /**
   *
   */
  static InvalidInstanceParameter(c) {
    c.response.status = 400
    c.render new ApiResponse('Invalid Instance parameter specified', 4030)
  }

  /**
   *
   */
  static InvalidInstanceId(c) {
    c.response.status = 400
    c.render new ApiResponse('Invalid Instance Id', 4000)
  }

  /**
   *
   */
  static ServiceNotFound(c) {
    c.response.status = 400
    c.render new ApiResponse('Service alias not found')
  }

  /**
   *
   */
  static IncorrectServiceParameter(c, parameter, allowedValues) {
    c.response.status = 400
    c.render new ApiResponse("${parameter} is incorrect format, allowed values are: ${allowedValues}", 1020)
  }

  /**
   *
   */
  static InvalidUserServiceId(c) {
    c.response.status = 400
    c.render new ApiResponse('Invalid UserService Id', 4000)
  }

  /*
  400:
  private static final def INCORRECT_EMAIL = new JSONObject ([
    ('message'): 'Email is incorrect',
    ('errorCode'): 1020
  ])
  */

  /**
   *
   */
  static BadRequest(c) {
    c.response.status = 400
    c.render nothing
  }

  /**
   *
   */
  static ok(c, data=null) {
    c.response.status = 200
    def str = nothing
    if (data != null) {
      str = data as JSON
    }
    c.render str
  }

  /**
   *
   */
  static error(c, code=null) {
    if (code == null) { code = 500 }
    c.response.status = code
    c.render nothing
  }
}
