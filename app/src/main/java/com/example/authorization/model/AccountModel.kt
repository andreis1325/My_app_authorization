package com.example.authorization.model

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class UserModel(
   
    var email: String? = "",
    var password: String = ""
) : RealmObject()

