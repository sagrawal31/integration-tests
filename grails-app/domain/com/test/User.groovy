package com.test

class User {

    static constraints = {
        email(unique: true)
    }

    String email
    String password
}
