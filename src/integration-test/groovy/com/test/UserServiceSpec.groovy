package com.test

import grails.gorm.transactions.Rollback
import grails.gorm.transactions.Transactional
import grails.test.mixin.integration.Integration
import spock.lang.Specification

@Integration
@Rollback
@Transactional
class UserServiceSpec extends Specification implements IntegrationTestHelper {

    UserService userService

    def setup() {
    }

    def cleanup() {
    }

    void "test save when a user with same email already exists"() {
        given: "An existing user"
        basicSetup()
        assert user1.id
        assert user1.email == "john@example.com"

        when: "A new user is saved with the same email"
        User newUser = userService.save([email: "john@example.com", password: "test"])

        then: "The user should not be saved"
        !newUser.id
        newUser.hasErrors()
        newUser.errors.hasFieldErrors("email")
    }

    void "test save"() {
        given: "An existing user"
        basicSetup()
        assert user1.id
        assert user1.email == "john@example.com"

        when: "A new user is saved with a different email"
        User newUser = userService.save([email: "donald@example.com", password: "test"])

        then: "The user should be saved"
        newUser.id
        !newUser.hasErrors()
        newUser.email == "donald@example.com"
    }
}
