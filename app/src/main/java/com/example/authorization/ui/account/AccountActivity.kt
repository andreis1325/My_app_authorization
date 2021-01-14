package com.example.authorization.ui.account

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.authorization.MyApp
import com.example.authorization.R
import com.example.authorization.model.isLoggedIn
import com.example.authorization.ui.base.BaseMvpActivity
import com.example.authorization.ui.login.LoginPresenter
import kotlinx.android.synthetic.main.activity_account.*
import org.kodein.di.instance

class AccountActivity : BaseMvpActivity(), AccountView {

    @InjectPresenter
    lateinit var accountPresenter : AccountPresenter

    override fun getLayoutId(): Int = R.layout.activity_account

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        initOnClickListeners()
    }

    private fun initOnClickListeners(){
        /*vBLogOut.setOnClickListener(){
            accountPresenter.logOut()
        }*/
    }

    override fun closeActivity(){
        finish()
    }
}