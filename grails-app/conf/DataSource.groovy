environments {
  development {
    grails {
      mongo {
        host         = "localhost"
        port         = 27017
        username     = ""
        password     = ""
        databaseName = "api"
      }

      if (System.env.REDISTOGO_URL != null) {
        redis {
          poolConfig {
              // jedis pool specific tweaks here, see jedis docs & src
              // ex: testWhileIdle = true
          }
          timeout = 2000 //default in milliseconds
          //password = "somepassword" //defaults to no password

          // requires either host & port combo, or a sentinels and masterName combo
          //def url = new URL("redis://redistogo:9d923fada60e0142372d5e7bf23f555f@mummichog.redistogo.com:9378/")
          def url = new URI(System.env.REDISTOGO_URL)

          // use a single redis server (use only if nore using sentinel cluster)

          port = url.port
          host = url.host

          // use redis-sentinel cluster as opposed to a single redis server (use only if not use host/port)
          //sentinels = [ "host1:6379", "host2:6379", "host3:6379" ] // list of sentinel instance host/ports
          //masterName = "mymaster" // the name of a master the sentinel cluster is configured to monitor
        }
      }


      /*
    redis {
        poolConfig {
            // jedis pool specific tweaks here, see jedis docs & src
            // ex: testWhileIdle = true
        }
        timeout = 2000 //default in milliseconds
        //password = "somepassword" //defaults to no password

        // requires either host & port combo, or a sentinels and masterName combo
        //def url = new URL("redis://redistogo:9d923fada60e0142372d5e7bf23f555f@mummichog.redistogo.com:9378/")
        def url = new URI("redis://localhost:6379/")

        // use a single redis server (use only if nore using sentinel cluster)

        port = url.port
        host = url.host

        // use redis-sentinel cluster as opposed to a single redis server (use only if not use host/port)
        //sentinels = [ "host1:6379", "host2:6379", "host3:6379" ] // list of sentinel instance host/ports
        //masterName = "mymaster" // the name of a master the sentinel cluster is configured to monitor
    }
    */

    } // grails
    /*
    dataSource {
      dbCreate = "create" // one of 'create', 'create-drop', 'update', 'validate', ''
      url      = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
    }*/
  } // development
  production {
    if (System.env.DATABASE_URL != null) {
      dataSource {
        pooled   = true
        dbCreate = 'update'
        url      = System.env.DATABASE_URL
      }
    }

    mongo {
      url    = System.env.MONGOHQ_URL
      pooled = true
      options {
        autoConnectRetry                             = true
        connectTimeout                               = 3000
        connectionsPerHost                           = 500
        socketTimeout                                = 60000
        threadsAllowedToBlockForConnectionMultiplier = 5
        maxAutoConnectRetryTime                      = 5
        maxWaitTime                                  = 120000
      }
    }

    if (System.env.REDISTOGO_URL != null) {
      redis {
        poolConfig {
            // jedis pool specific tweaks here, see jedis docs & src
            // ex: testWhileIdle = true
        }
        timeout = 2000 //default in milliseconds
        //password = "somepassword" //defaults to no password

        // requires either host & port combo, or a sentinels and masterName combo
        //def url = new URL("redis://redistogo:9d923fada60e0142372d5e7bf23f555f@mummichog.redistogo.com:9378/")
        def url = new URI(System.env.REDISTOGO_URL)

        // use a single redis server (use only if nore using sentinel cluster)

        port = url.port
        host = url.host

        // use redis-sentinel cluster as opposed to a single redis server (use only if not use host/port)
        //sentinels = [ "host1:6379", "host2:6379", "host3:6379" ] // list of sentinel instance host/ports
        //masterName = "mymaster" // the name of a master the sentinel cluster is configured to monitor
      }
    }
  } // production
} // environments
