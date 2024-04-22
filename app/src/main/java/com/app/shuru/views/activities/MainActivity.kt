package com.app.shuru.views.activities

import android.os.Bundle
import com.app.shuru.R
import com.app.shuru.views.fragments.LocationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(LocationFragment(), null, false, null)
    }
}