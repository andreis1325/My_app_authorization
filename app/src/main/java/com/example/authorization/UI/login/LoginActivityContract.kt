package com.example.authorization.UI.login


public interface LoginActivityContract{

    interface View{
        fun onSuccess(message : String)
        fun onError(message : String)
    }

    interface Presenter{
        fun doLogIn(email: String, password : String)
        fun doSignUp(email: String, pass1: String, pass2: String)

    }

}