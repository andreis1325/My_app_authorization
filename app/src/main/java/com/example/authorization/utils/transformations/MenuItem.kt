package com.example.authorization.utils.transformations

import java.io.Serializable

sealed class MenuItem: Serializable{
    object Article : MenuItem()
    object Blog : MenuItem()
    object Report : MenuItem()
}