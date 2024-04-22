package com.app.shuru.views.fragments

import android.view.View
import androidx.fragment.app.Fragment
import com.app.shuru.views.activities.BaseActivity

open class BaseFragment<T> : Fragment() {

    // Function to perform setup for views in the fragment
    open fun setupViews(view: View) {
        // Override this method in child fragments to perform view setup
    }
    protected fun addFragment(
        fragment: Fragment,
        tag: String?,
        addToBackStack: Boolean,
        backStackName: String?
    ) {
        if (null != activity && activity is BaseActivity) {
            (activity as BaseActivity).addFragment(
                fragment,
                tag,
                addToBackStack,
                backStackName
            )
        }
    }

    @Suppress("SameParameterValue")
    protected fun replaceFragment(
        fragment: Fragment,
        tag: String?,
        addToBackStack: Boolean,
        backStackName: String?
    ) {
        if (null != activity && activity is BaseActivity) {
            (activity as BaseActivity).replaceFragment(
                fragment,
                tag,
                addToBackStack,
                backStackName
            )
        }
    }

    protected fun showToast(message: String) {
        if (null != activity && activity is BaseActivity) {
            (activity as BaseActivity).showToast(message)
        }
    }
    open fun onBackPressed(): Boolean = false

    protected fun popBackStack() {
        if (null != activity && activity is BaseActivity) {
            (activity as BaseActivity).popBackStack()
        }
    }

    protected fun clearBackStack() {
        if (null != activity && activity is BaseActivity) {
            (activity as BaseActivity).clearBackStack()
        }
    }

    open fun onFragmentResumedFromBackStack() = Unit

    protected fun finishActivity() {
        if (null != activity && activity is BaseActivity) {
            (activity as BaseActivity).finish()
        }
    }

}