package com.example.authorization.UI.login


public interface LoginActivityContract{

    interface View{
        fun onSuccess(message : String)
        fun onError(message : String)
    }

    interface Presenter{
        fun doLogin(email: String, password : String)

    }

}