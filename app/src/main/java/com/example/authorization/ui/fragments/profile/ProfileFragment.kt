package com.example.authorization.ui.fragments.profile

import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
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

    }

    private fun initOnClickListeners(){
        vBExit.setOnClickListener{
            profilePresenter.exit()
        }
    }

    override fun closeActivity() {
        activity?.finish()
    }
}