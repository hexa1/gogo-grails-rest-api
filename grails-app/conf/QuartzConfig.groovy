// http://grails-plugins.github.io/grails-quartz/guide/configuration.html
quartz {
    autoStartup = true
    jdbcStore = false
    waitForJobsToCompleteOnShutdown = true
    exposeSchedulerInRepository = false

    props {
      scheduler.skipUpdateCheck = true
    }
}

environments {
    test {
        quartz {
            autoStartup = true
        }
    }
}
