## Example of a Grails API application

**Demonstrates the following concepts:**

- Spring Security
- Spring Security + REST token based authentication (via plugin)
- Simple User authentication mechanism and token pruning (via quartz plugin)
- Environment checking in bootstrap
- CORS
- Many config options, such as root context = /
- Abstracting API responses
- Securing controllers using annotations
- Example controller of various annotation options
- Sending notification emails using Postmark
- Sample utilitiy services for security and authentication
- Error mapping
- Replacing Tomcat with Jetty
- Deploying on Heroku
- Using with local MongoDB with support for heroku Mongo and Redis
- App version and build (using git) reporting through API (via _Events.groovy)
- Heroku ready (Procile, system.properties). Jetty used + Java 1.8
- Removal of sitemesh processing: API only app
- Removal of Hibernate
- Removal of web-app assets for front-end related things
- Removal of most of front-end related items
- Helpful links and resources in the code

## TODO

- API Blueprint

## Getting set up

1. Install latest mongoDB
2. Add mongoDB user with read/write permissions.
  You should use the following command (in mongo):

  ```
  use api
  db.createUser(
  {
    user: "test",
    pwd: "test",
    roles: ['readWrite']
  }
  )
  ````

3. Install grails 2.4.4 or use wrapper: ./grailsw

## Running in development (local)

1. `./grailsw run-app`

## Working with Heroku

1. Install the Heroku Toolbelt: https://toolbelt.heroku.com/
2. Follow instructions to login at the command line
3. Pushing the app to production: `$ git push heroku master`, then navigate to the app url
4. Check logs and status: `heroku logs`, `heroku ps`
5. Check heroku environment variables: `heroku config`

## CORS

Implemented using https://github.com/davidtinker/grails-cors plugin, v1.1.5, v1.1.6 does not seem to work

Another option to do it is via cors-filter: https://github.com/eBay/cors-filter, then for configuration, edit: src/templates/war/web.xml

## Notes

- Procfile is a Heroku config file (see: https://devcenter.heroku.com/articles/procfile)
- `system.properties` is also used by Heroku
- Java memory issues: https://devcenter.heroku.com/articles/java-memory-issues
- `$ heroku config:set JAVA_OPTS='-Xmx1024m -Xss512k -XX:+UseCompressedOops'`

## Authentication: Valuable Resources

- http://alvarosanchez.github.io/grails-spring-security-rest/docs/guide/single.html#grailsCache
- http://www.jamesward.com/2013/05/13/securing-single-page-apps-and-rest-services
- http://blog.brunoscopelliti.com/authentication-to-a-restful-web-service-in-an-angularjs-web-app

## Other Considerations

- Force SSL: Heroku and Spring Security will need to be patched to play nice: https://discussion.heroku.com/t/grails-spring-security-core-securechannel-definition-causes-redirect-loop/219/2
