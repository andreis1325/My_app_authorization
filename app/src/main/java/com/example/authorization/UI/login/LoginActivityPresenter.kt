package com.example.authorization.UI.login

import android.content.Context

public class LoginActivityPresenter( var view: LoginActivityContract.View) : LoginActivityContract.Presenter{


    override fun doLogin(email: String, password: String) {
        if (email == "test@mail.ru" && password == "test") view.onSuccess("Successful")
        else view.onError("Wrong email or password")
    }


}