package com.test

import grails.transaction.Transactional

@Transactional
class UserService {

    User save(Map params) {
        User user = new User(params)
        user.save(flush: true)

        user
    }
}
