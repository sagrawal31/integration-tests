package com.test

abstract class ControllerIntegrationTestHelper extends IntegrationTestHelper {

    @Override
    void basicSetup() {
        User.withNewTransaction {
            super.basicSetup()
        }
    }

    def cleanup() {
        User.withNewTransaction {
            Mail.list()*.delete()
            User.list()*.delete(flush: true)
        }
    }
}
