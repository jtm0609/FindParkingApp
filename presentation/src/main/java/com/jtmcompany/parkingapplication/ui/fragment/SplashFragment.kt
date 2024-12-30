package com.jtmcompany.parkingapplication.ui.fragment

import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseFragment
import com.jtmcompany.parkingapplication.databinding.FragmentSplashBinding
import com.jtmcompany.parkingapplication.utils.Constants
import com.jtmcompany.parkingapplication.ui.viewmodel.ParkSearchViewModel
import com.jtmcompany.parkingapplication.ui.viewmodel.SplashViewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {

    override val viewModel : SplashViewModel by viewModels()

    companion object {
        private const val perRequestCode = 1000
    }

    override fun initObserver() {
    }

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            if(checkPermission()){
                val action = SplashFragmentDirections.actionSplashFragmentToParkLocationFragment()
                nextFragment(action)
            }else{
                requestPermission()
            }
        }, Constants.LOADING_DELAY)
    }

    override fun setBindingVariable(binding: FragmentSplashBinding) {
        with(binding){
            viewModel = this@SplashFragment.viewModel
        }
    }

    private fun requestPermission(){
        requestPermissions(
            arrayOf(
                android.Manifest.permission.INTERNET,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ), perRequestCode
        )
    }

    private fun checkPermission(): Boolean {
        val permission1 = checkSelfPermission(mContext, android.Manifest.permission.INTERNET)
        val permission2 = checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION)
        val permission3 = checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION)

        return !(permission1 == PackageManager.PERMISSION_DENIED ||
                permission2 == PackageManager.PERMISSION_DENIED ||
                permission3 == PackageManager.PERMISSION_DENIED)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == perRequestCode) {
            // 모든 권한이 허용되었는지 체크
            val isAllPermissionsGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }

            if(isAllPermissionsGranted) {
                val action = SplashFragmentDirections.actionSplashFragmentToParkLocationFragment()
                nextFragment(action)
            }else {
                showToast(getString(R.string.msg_alert_need_permission))
                activity?.finish()
            }
        }
    }
}