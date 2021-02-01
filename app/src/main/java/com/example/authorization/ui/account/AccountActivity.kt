package com.example.authorization.ui.account

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
import com.example.authorization.ui.base.BaseMvpActivity
import com.example.authorization.ui.fragments.bookmarks.BookmarksFragment
import com.example.authorization.ui.fragments.extendednews.ExtendedNewsFragment
import com.example.authorization.ui.fragments.history.HistoryFragment
import com.example.authorization.ui.fragments.home.HomeFragment
import com.example.authorization.ui.fragments.profile.ProfileFragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import kotlinx.android.synthetic.main.activity_account.*


class AccountActivity : BaseMvpActivity(), AccountView {

    private val fragNavController = FragNavController(supportFragmentManager, R.id.vFlContainer)
    private var backPressed: Long = 0
    private var isClickBlocked = false

    companion object {
        private const val TIME: Int = 2000
    }

    private val fragments: List<BaseMvpFragment> = listOf(
        HomeFragment.newInstance(),
        BookmarksFragment.newInstance(),
        HistoryFragment.newInstance(),
        ProfileFragment.newInstance()
    )

    override fun onBackPressed() {
        isClickBlocked = false
        if (fragNavController.isRootFragment) {
            exitOrShowMessage()
        } else
            fragNavController.popFragment()
    }

    @InjectPresenter
    lateinit var accountPresenter: AccountPresenter

    override fun getLayoutId(): Int = R.layout.activity_account

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        setupNavigation(savedInstanceState)
        setupNavigationListeners()

    }

    private fun setupNavigation(savedInstanceState: Bundle?) {
        fragNavController.fragmentHideStrategy = FragNavController.HIDE
        fragNavController.rootFragments = fragments
        fragNavController.defaultTransactionOptions = FragNavTransactionOptions
            .newBuilder()
            .transition(FragmentTransaction.TRANSIT_NONE)
            .build()

        fragNavController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    fun goToItemNews(fragment: ExtendedNewsFragment) {

        if (!isClickBlocked)
            fragNavController.pushFragment(fragment)
        isClickBlocked = true
    }

    // MARK: Assistant functions
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

    private fun exitOrShowMessage() {
        if (backPressed + TIME > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, getString(R.string.exit), Toast.LENGTH_SHORT).show()
        }
        backPressed = System.currentTimeMillis()
    }
}