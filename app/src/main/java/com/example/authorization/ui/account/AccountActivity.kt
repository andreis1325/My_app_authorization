package com.example.authorization.ui.account

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
import com.example.authorization.ui.base.BaseMvpActivity
import com.example.authorization.ui.fragments.BookmarksFragment
import com.example.authorization.ui.fragments.HistoryFragment
import com.example.authorization.ui.home.HomeFragment
import com.example.authorization.ui.fragments.ProfileFragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import kotlinx.android.synthetic.main.activity_account.*


class AccountActivity : BaseMvpActivity(), AccountView {

    private val fragNavController = FragNavController(supportFragmentManager, R.id.vFlContainer)

    private  val fragments = listOf(
        HomeFragment.newInstance(),
        BookmarksFragment.newInstance(),
        HistoryFragment.newInstance(),
        ProfileFragment.newInstance()
    )

    @InjectPresenter
    lateinit var accountPresenter: AccountPresenter

    override fun getLayoutId(): Int = R.layout.activity_account

    override fun onCreateActivity(savedInstanceState: Bundle?) {

        setupNavigation(savedInstanceState)
        setupNavigationListeners()

    }

    private fun setupNavigation(savedInstanceState: Bundle?) {
        fragNavController.fragmentHideStrategy = FragNavController.HIDE
        fragNavController.rootFragments = fragments as? List<BaseMvpFragment>
        fragNavController.defaultTransactionOptions = FragNavTransactionOptions.newBuilder()
            .transition(FragmentTransaction.TRANSIT_NONE)
            .build()

        fragNavController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    private fun setupNavigationListeners() {
        vBnvBottomBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homePage -> {
                    fragNavController.switchTab(FragNavController.TAB1)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.historyPage -> {
                    fragNavController.switchTab(FragNavController.TAB2)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bookMarksPage -> {
                    fragNavController.switchTab(FragNavController.TAB3)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profilePage -> {
                    fragNavController.switchTab(FragNavController.TAB4)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }
}