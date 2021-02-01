package com.example.authorization.ui.fragments.profile

import android.content.Intent
import android.net.Uri
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
import com.example.authorization.ui.login.LoginActivity
import com.example.authorization.utils.extensions.setImage
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment: BaseMvpFragment(), ProfileView{

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter

    companion object {

        fun newInstance(): ProfileFragment {

            return ProfileFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_profile

    override fun onViewCreated(view: View) {
        initOnClickListeners()
        profilePresenter.onCreate()
    }

    private fun initOnClickListeners(){
        vBExit.setOnClickListener{
            switchToLoginActivity()
            profilePresenter.exit()
        }
    }

    override fun switchToLoginActivity() {
        startActivity(Intent(context, LoginActivity::class.java))
        activity?.finish()
    }

    override fun setGoogleData(gEmail: String?, gName: String?, gId: String?, gImage: Uri?) {
        vTvGoogleEmail.text = gEmail
        vTvGoogleName.text = gName
        vTvGoogleId.text = gId
        vTvGooglePhoto.setImage(gImage)
    }

}