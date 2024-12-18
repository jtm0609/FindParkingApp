package com.jtmcompany.parkingapplication.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import java.util.jar.Manifest

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : AppCompatActivity() {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

}