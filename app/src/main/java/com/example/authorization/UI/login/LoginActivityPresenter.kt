package com.example.authorization.UI.login

class LoginActivityPresenter( var view: LoginActivityContract.View) : LoginActivityContract.Presenter{

    override fun doLogIn(email: String, password: String) {
        val matchResult = Regex(""".+@(mail.ru|gmail.com)""").find(email)

        if(matchResult == null) view.onError("Wrong email")

        if (email == "test@mail.ru" && password == "test") view.onSuccess("Successful")
        else view.onError("Wrong email or password")
    }

    override fun doSignUp(email: String, pass1: String, pass2: String) {
        val matchResult = Regex(""".+@(mail.ru|gmail.com)""").find(email)

        if(matchResult == null) view.onError("Wrong email")
        if (pass1 != pass2) view.onError("Passwords aren't equal")
        else  view.onSuccess("Successful")

    }
}