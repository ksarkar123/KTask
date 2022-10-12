package com.extraaedge.rocketapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.extraaedge.rocketapp.R
import com.extraaedge.rocketapp.fragments.RocketListFragment
import com.extraaedge.rocketapp.utils.AppCommonUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCommonUtils.loadFragment(this, RocketListFragment())

    }
}