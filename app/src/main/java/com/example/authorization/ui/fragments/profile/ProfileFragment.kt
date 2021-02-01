package com.example.authorization.ui.fragments.profile

import android.content.Intent
import android.net.Uri
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
import com.example.authorization.ui.account.AccountActivity
import com.example.authorization.ui.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment: BaseMvpFragment(), ProfileView{

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter
    lateinit var mGoogleSignInClient: GoogleSignInClient

    companion object {

        fun newInstance(): ProfileFragment {

            return ProfileFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_profile

    override fun onViewCreated(view: View) {
        initOnClickListeners()
        profilePresenter.onCreate(context)
        setGoogleAuthData()
    }

    private fun setGoogleAuthData() {
        val acct = GoogleSignIn.getLastSignedInAccount(context)

           vTvGoogleEmail.text = acct?.email
            vTvGoogleName.text = acct?.displayName
            vTvGoogleId.text = acct?.id

            Glide.with(this)
                .load(acct?.photoUrl)
                .placeholder(R.drawable.fox)
                .into(vTvGooglePhoto)
    }

    private fun initOnClickListeners(){
        vBExit.setOnClickListener{
            switchToLoginActivity()
            profilePresenter.exit()
        }
    }

    //MARK: View implementation
    override fun setGoogleData(gEmail: String?, gName: String?, gId: String?, gImage: Uri?) {
        vTvGoogleEmail.text = gEmail
        vTvGoogleName.text = gName
        vTvGoogleId.text = gId

        Glide.with(this)
            .load(gImage)
            .placeholder(R.drawable.fox)
            .into(vTvGooglePhoto)
    }

    override fun switchToLoginActivity() {
        (activity as? AccountActivity)?.signOut()
        startActivity(Intent(context, LoginActivity::class.java))
        activity?.finish()
    }
}