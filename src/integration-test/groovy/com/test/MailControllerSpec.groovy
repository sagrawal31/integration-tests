package com.test

import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.http.MediaType
import spock.lang.Specification

@Integration
@Rollback
class MailControllerSpec extends Specification implements IntegrationTestHelper {

    def setup() {
    }

    def cleanup() {
    }

    void "test saving an email"() {
        given: "Basic user"
        basicSetup()
        assert user1.id

        when: "A request is posted"
        String requestBody = mapToQueryString([subject: "Test subject", body: "Hello", email: user1.email])

        new RestBuilder().post("http://localhost:${serverPort}/api/mail/callback", {
            contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            body(requestBody)
        })

        then: "Mail should be saved"
        Mail.count() == 1
        Mail mail = Mail.first()
        mail.user.id == user1.id
        mail.subject == "Test subject"
    }
}
