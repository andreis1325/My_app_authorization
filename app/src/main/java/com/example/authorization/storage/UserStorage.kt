package com.example.authorization.storage

import android.content.Context
import com.example.authorization.model.UserModel
import com.example.authorization.utils.extensions.execute
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlin.coroutines.coroutineContext


class UserStorage {

    fun addUser(userModel: UserModel){
        Realm.getDefaultInstance().execute { realm ->
            realm.insertOrUpdate(userModel)
        }
    }

    fun isRegistered(email: String):Boolean {
        val register: UserModel? = Realm.getDefaultInstance().where(UserModel::class.java)
            ?.equalTo("email", email)
            ?.findFirst()
        return register != null
    }
}