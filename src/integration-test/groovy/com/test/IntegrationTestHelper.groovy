package com.test

trait IntegrationTestHelper {

    User user1
    void basicSetup() {
        User.withNewTransaction {
            user1 = new User([email: "john@example.com", password: "test"])
            user1.save(flush: true)
        }
    }

    String mapToQueryString(Map params) {
        params.collect { k,v -> "$k=" + URLEncoder.encode(v, "UTF-8") }.join("&")
    }
}
