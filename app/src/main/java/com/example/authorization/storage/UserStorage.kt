package com.example.authorization.storage

import android.content.Context
import com.example.authorization.model.UserModel
import com.example.authorization.utils.extensions.execute
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlin.coroutines.coroutineContext


class UserStorage {

    private fun getUserStorage(): Realm{
        return Realm.getDefaultInstance()
    }

    fun addUser(userModel: UserModel){
        getUserStorage().execute { realm ->
            realm.insertOrUpdate(userModel)
        }
    }

    fun isRegistered(email: String, password: String):Boolean {
        val register: UserModel? = getUserStorage().where(UserModel::class.java)
            ?.equalTo("email", email)
            ?.equalTo("password", password)
            ?.findFirst()

        return register != null
    }

}