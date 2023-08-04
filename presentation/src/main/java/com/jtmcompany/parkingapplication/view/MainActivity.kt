package com.jtmcompany.parkingapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseActivity
import com.jtmcompany.parkingapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: ParkInfoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
        initObserver()
    }

    private fun initObserver() {
        viewModel.parkList.observe(this@MainActivity, Observer {

        })
    }

    private fun initViewModelCallback() {
        with(viewModel){
            //toastMsg가 변경 시 , 변경된 text로 toas를 띄워준다.
            toastMsg.observe(this@MainActivity, Observer{
                when(toastMsg.value){
                    ParkInfoViewModel.MessageSet.NO_RESULT -> showToast(getString(R.string.no_result_msg))
                    ParkInfoViewModel.MessageSet.NETWORK_NOT_CONNECTED -> showToast(getString(R.string.not_connectied_network))
                    ParkInfoViewModel.MessageSet.SUCCESS -> showToast(getString(R.string.api_success_msg))
                    ParkInfoViewModel.MessageSet.ERROR -> showToast(getString(R.string.api_error_msg))
                }
            })
        }
    }
}