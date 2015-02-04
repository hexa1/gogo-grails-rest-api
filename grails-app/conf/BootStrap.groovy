import grails.util.Environment
import org.springframework.web.context.support.WebApplicationContextUtils

/**
 *
 */
class BootStrap {
  /**
   *
   */
  def init = { servletContext ->
    def springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext)
    println "Initializing..."
    if (Environment.current == Environment.DEVELOPMENT) {
    } else if (Environment.current == Environment.TEST) {
    } else if (Environment.current == Environment.PRODUCTION) {
      def notif = springContext.getBean("notificationService")
      notif.sendAdminNotification("[UP] App just started in production")
    }
  } // init

  /**
   * Note: this is not guaranteed to be called by the servlet container
   */
  def destroy = {
    printn "De-initializing..."
    def springContext = WebApplicationContextUtils.getWebApplicationContext(
      servletContext)
    if (Environment.current == Environment.DEVELOPMENT) {
    } else if (Environment.current == Environment.TEST) {
    } else if (Environment.current == Environment.PRODUCTION) {
      def notif = springContext.getBean("notificationService")
      notif.sendAdminNotification("[DOWN] App just went down in production")
    }
  } // destroy
} // Bootstrap
