// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

// change this to alter the default package name and Maven publishing destination
grails.project.groupId = 'api'

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
  views {
    gsp {
      encoding  = 'UTF-8'
      htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
      codecs {
        expression  = 'html' // escapes values inside ${}
        scriptlet   = 'html' // escapes output from scriptlets in GSPs
        taglib      = 'none' // escapes output from taglibs
        staticparts = 'none' // escapes output from static template parts
      }
    }
    // escapes all not-encoded output at final stage of outputting
    // filteringCodecForContentType.'text/html' = 'html'
  }
}


grails.converters.encoding = "UTF-8"

// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false

// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true

// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// whether to disable processing of multi part requests
grails.web.disable.multipart = false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false

// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
  development {
    grails.logging.jul.usebridge = true
  }
  production {
    grails.logging.jul.usebridge = false
    grails.serverURL = "http://api.myapp.com"
  }
}

// log4j configuration
log4j.main = {
  // Example of changing the log pattern for the default console appender:
  //
  //appenders {
  //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
  //}

  debug  'api'
  error  'org.codehaus.groovy.grails.web.servlet',        // controllers
         'org.codehaus.groovy.grails.web.pages',          // GSP
         'org.codehaus.groovy.grails.web.sitemesh',       // layouts
         'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
         'org.codehaus.groovy.grails.web.mapping',        // URL mapping
         'org.codehaus.groovy.grails.commons',            // core / classloading
         'org.codehaus.groovy.grails.plugins',            // plugins
         'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
         'org.springframework',
         'org.hibernate',
         'net.sf.ehcache.hibernate'
  info   'org.eclipse.jetty'
  info   'org.springframework.security'
  debug  'grails.app.controllers',
         'grails.app.conf',
         'grails.app.domain',
         'grails.app.services',
         'grails.app.jobs'
  error  'grails.plugin.heroku',
         'grails.plugin.cloudsupport'
}


// Heroku plugin installs database session unless excluded in BuildConfig
grails.plugin.databasesession.enabled = false


// Disable Sitemesh, no views in an API app
// Also set in sitemesh.xml
// see: https://jira.grails.org/browse/GRAILS-5770
grails.views.gsp.sitemesh.preprocess = false

// API Config

def apiUri      = '/v1'
def tokenHeader = 'X-Auth-Token'

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName    = 'api.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'api.UserRole'
grails.plugin.springsecurity.authority.className               = 'api.Role'

/**
 * @see http://grails-plugins.github.io/grails-spring-security-core/guide/authentication.html#rememberMeCookie
 */

/*
grails.plugin.springsecurity.rememberMe.cookieName           = 'api_remember_me'
grails.plugin.springsecurity.rememberMe.alwaysRemember       = false
grails.plugin.springsecurity.rememberMe.tokenValiditySeconds = 60 //1209600 // 14 days
grails.plugin.springsecurity.rememberMe.parameter            = '_api_login_remember_me'
grails.plugin.springsecurity.rememberMe.key                  = 'api'
grails.plugin.springsecurity.rememberMe.persistent           = true
*/

/**
 * @see http://grails-plugins.github.io/grails-spring-security-core/guide/requestMappings.html
 */
// Pessimistic lock-down: reject all urls with no security definition
// Lock everything down by default, return 403
grails.plugin.springsecurity.rejectIfNoRule              = true
grails.plugin.springsecurity.fii.rejectPublicInvocations = false
grails.plugin.springsecurity.securityConfigType          = "Annotation"

//https://github.com/davidtinker/grails-cors
cors.url.pattern = ["${apiUri}/*"]

cors.expose.headers = tokenHeader
//cors.allow.headers = 'X-Auth-Token'

cors.headers = ['Access-Control-Allow-Headers': "${tokenHeader}, Origin, Authorization, Accept, Content-Type, X-Requested-With"]

// Grails Spring Security REST Plugin Configuration
// http://alvarosanchez.github.io/grails-spring-security-rest/docs/guide/single.html#grailsCache
grails.plugin.springsecurity.filterChain.chainMap = [
    (apiUri + '/**'): 'JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter',  // Stateless chain
    '/**': 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'                                                                          // Traditional chain
]

// Example: http://alvarosanchez.github.io/grails-spring-security-rest/docs/guide/single.html#
/*
grails {
    plugin {
        springsecurity {
            filterChain {
                chainMap = [
                    '/api/guest/**': 'anonymousAuthenticationFilter,restTokenValidationFilter,restExceptionTranslationFilter,filterInvocationInterceptor',
                    '/api/**': 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter',
                    '/**': 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
                ]
            }
            //Other Spring Security settings
            //...

            rest {
                token {
                    validation {
                        enableAnonymousAccess = true
                    }
                }
            }
        }
    }
}
*/

def rest = grails.plugin.springsecurity.rest

rest.login.active                       = true
rest.login.endpointUrl                  = apiUri + "/login"
rest.login.failureStatusCode            = 401

// TODO: enable me, it's more RESTFUL
rest.login.useJsonCredentials           = true
rest.login.usernamePropertyName         = 'username'
rest.login.passwordPropertyName         = 'password'

rest.login.useRequestParamsCredentials  = false
rest.login.usernamePropertyName         = 'username'
rest.login.passwordPropertyName         = 'password'

rest.logout.endpointUrl                 = apiUri + '/logout'
rest.token.validation.headerName        = tokenHeader

// Token Security
rest.token.generation.useSecureRandom   = true
rest.token.generation.useUUID           = false

// Token Storage: Memcached
rest.token.storage.useMemcached         = false
rest.token.storage.memcached.hosts      = 'localhost:11211'
rest.token.storage.memcached.username   = ''
rest.token.storage.memcached.password   = ''
rest.token.storage.memcached.expiration = 3600

// Token Storage: GORM
rest.token.storage.useGorm                     = true
rest.token.storage.gorm.tokenDomainClassName   = "api.auth.AuthenticationToken"
rest.token.storage.gorm.tokenValuePropertyName = 'tokenValue'
rest.token.storage.gorm.usernamePropertyName   = 'username'

rest.token.storage.useGrailsCache  = false
rest.token.storage.grailsCacheName = null

// Token Bearer: RFC 6750 Bearer Token: http://tools.ietf.org/html/rfc6750
rest.token.validation.useBearerToken         = false
rest.token.validation.headerName             = tokenHeader
rest.token.rendering.tokenPropertyName       = 'token'
rest.token.rendering.usernamePropertyName    = 'username'
rest.token.rendering.authoritiesPropertyName = 'roles'

rest.token.validation.active                 = true
rest.token.validation.headerName             = tokenHeader
rest.token.validation.endpointUrl            = apiUri + '/validate'


