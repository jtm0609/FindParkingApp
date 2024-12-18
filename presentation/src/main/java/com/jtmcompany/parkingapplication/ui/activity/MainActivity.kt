package com.jtmcompany.parkingapplication.ui.activity

import android.os.Bundle
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseActivity
import com.jtmcompany.parkingapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}