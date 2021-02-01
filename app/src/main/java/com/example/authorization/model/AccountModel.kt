package com.example.authorization.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserModel(
    @PrimaryKey
    var email: String? = "",
    var password: String = ""
) : RealmObject()

