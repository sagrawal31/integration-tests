package com.test

import grails.transaction.Transactional

@Transactional
class MailService {

    Mail processIncoming(Map params) {
        log.debug "Process incoming request with $params"

        User user = User.findByEmail(params.email)
        if (!user) {
            log.debug("user not found with $params.email")
            return
        }

        Mail mail = new Mail(params)
        mail.user = user
        mail.save(flush: true)

        mail
    }
}
