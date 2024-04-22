package com.app.shuru.views.activities

import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.shuru.R
import com.app.shuru.views.fragments.BaseFragment

open class BaseActivity : AppCompatActivity() {

    private var container: Int = R.id.container
    protected val fragmentManager = supportFragmentManager


    // Function to show a toast message
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    internal fun addFragment(
        fragment: Fragment,
        tag: String?,
        addToBackStack: Boolean,
        backStackName: String?
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(container, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(backStackName)
        }
        fragmentTransaction.commit()
    }

    internal fun replaceFragment(
        fragment: Fragment,
        tag: String?,
        addToBackStack: Boolean,
        backStackName: String?
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(container, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(backStackName)
        }
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragment = fragmentManager.findFragmentById(container)
        if (null != fragment && fragment is BaseFragment<*>) {
            if (!(fragment).onBackPressed()) {
                if (fragmentManager.backStackEntryCount <= 0) {
                    finish()
                } else {
                   /* if (1 == fragmentManager.backStackEntryCount) {
                        enableToolbarUpButton(false)
                    }*/
                    fragmentManager.popBackStack()
                }
            }
        } else {
            if (fragmentManager.backStackEntryCount <= 0) {
                finish()
            } else {
              /*  if (1 == fragmentManager.backStackEntryCount) {
                    enableToolbarUpButton(false)
                }*/
                fragmentManager.popBackStack()
            }
        }
    }

    internal fun popBackStack() {
        if (fragmentManager.backStackEntryCount <= 0) {
            finish()
        } else {
            fragmentManager.popBackStack()
        }
    }

    internal fun clearBackStack() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private inner class BackStackChangedListener : FragmentManager.OnBackStackChangedListener {
        override fun onBackStackChanged() {
            val fragment = fragmentManager.findFragmentById(container)
            if (fragment is BaseFragment<*>) {
                fragment.onFragmentResumedFromBackStack()
            }
        }
    }

    override fun finish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask()
        } else {
            super.finish()
        }
    }

    protected fun restartActivity() {
        finish()
        startActivity(intent)
    }
}