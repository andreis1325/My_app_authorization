package com.example.authorization.repo

import com.example.authorization.model.UserModel
import com.example.authorization.storage.UserStorage
import io.realm.Realm
import io.realm.kotlin.createObject

class UserRepo{

        private val userStorage  = UserStorage()

        fun addUser(email: String, password: String){
            userStorage.addUser(UserModel(email,password))
        }

        fun isRegistered(email: String, password: String): Boolean{
            return userStorage.isRegistered(email, password)
        }

}