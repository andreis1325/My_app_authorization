package com.example.authorization.net.repo

import com.example.authorization.MyApp
import com.example.authorization.model.UserModel
import com.example.authorization.storage.UserStorage
import org.kodein.di.instance

class UserRepo {

    private val userStorage by MyApp.kodein.instance<UserStorage>()

    fun addUser(email: String, password: String) {
        userStorage.addUser(UserModel(email, password))
    }

    fun isRegistered(email: String): Boolean {
        return userStorage.isRegistered(email)
    }
}