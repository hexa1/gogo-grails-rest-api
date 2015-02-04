package api

import grails.converters.JSON
import grails.transaction.Transactional
import grails.plugins.rest.client.RestBuilder

/**
 *
 */
@Transactional
class NotificationService {
  public static final String POSTMARK_TOKEN      = ''
  public static final String DEFAULT_FROM        = 'no-reply@app.com'
  public static final String SYSTEM_ADMIN_EMAILS = [
    'admin1@app.com', 'admin2@app.com']

  /**
   *
   */
  def sendEmail(Map options) {
    RestBuilder rest = new RestBuilder()
    def resp = rest.post('https://api.postmarkapp.com/email') {
      header('X-Postmark-Server-Token', POSTMARK_TOKEN)
      accept('application/json')
      contentType('application/json')
      def responseBody = [
        'From'     : options.from ?: DEFAULT_FROM,
        'To'       : options.to,
        'Subject'  : options.subject ?: 'Message from App',
        'HtmlBody' : options.body ?: '',
        'Cc'       : options.cc
      ]
      json responseBody as JSON
    }
    resp.json
  } // sendEmail

  /**
   * TODO: Move out credentials and configurable items
   */
  def sendEmailResetPassword(String email, String password) {
    log.debug "Sending new password (${password}) for ${email}"
    sendEmail(
      to      : email,
      subject : '[API] Your New Password',
      body    : "<html><body><strong>Hello,</strong> your new password: ${password}</body></html>"
    )
  } // sendEmail

  /**
   *
   */
  def sendAdminNotification(subject, body = null) {
    body = body ?: ''
    sendEmail(
      to      : SYSTEM_ADMIN_EMAILS[0],
      cc      : SYSTEM_ADMIN_EMAILS[1] ?: '',
      subject : subject,
      body    : "<html><body><strong>${body}</body></html>"
    )
  } // sendAdminNotification
} // NotificationService
