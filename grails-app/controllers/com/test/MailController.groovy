package com.test

import grails.gorm.transactions.Transactional
import org.springframework.http.HttpStatus

@Transactional
class MailController {

    static responseFormats = ["json"]

    MailService mailService

    def receive() {
        log.debug "Receive action $params"

        mailService.processIncoming(params)
        render status: HttpStatus.OK
    }
}
