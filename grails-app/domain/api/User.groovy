package api

import org.bson.types.ObjectId

/**
 *
 */
class User {
	transient securityService
  transient springSecurityService

  ObjectId id
  String firstName = ''
  String lastName  = ''
  String username
  String password
  String email

	static transients = ['securityService', 'springSecurityService']

	static constraints = {
    email    blank: false, email   : true, unique: true
		username blank: false, unique  : true
		password blank: false, matches : '^((?=.*\\d).{6,})$'
	}

	static mapping = {
		password column: '`password`'
	}

  /**
   *
   */
  static User createUserWithEmail(String email, String password) {
    new User(email: email, username: email, password: password)
  }

  /**
   *
   */
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

  /**
   *
   */
	def beforeInsert() {
		encodePassword()
	}

  /**
   *
   */
	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

  /**
   *
   */
	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
