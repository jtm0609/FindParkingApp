package com.jtmcompany.parkingapplication.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseActivity
import com.jtmcompany.parkingapplication.databinding.ActivityMainBinding
import com.jtmcompany.parkingapplication.utils.PrefManager
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: ParkInfoViewModel by viewModels()
    private lateinit var mapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
        initObserver()
        initMapView()

        //주차장 정보를 가져온다.
        viewModel.requestParkInfo(1)
    }

    private fun initObserver() {
        viewModel.totalCntCheck.observe(this@MainActivity, Observer {
            val apiTotalCnt = it
            val localTotalCnt = PrefManager.getInt(this, "park_total_cnt")

            //주차장 totalCnt(서버 정보)가 변경 되었을 때 or 앱 최초 진입시 전체 주차장 정보 서버로부터 불러오기
            if (apiTotalCnt != localTotalCnt) {
                PrefManager.setInt(this, "park_total_cnt", apiTotalCnt)
                viewModel.requestParkInfo(apiTotalCnt)
            }
            //변경 안되었다면 DB로 부터 불러오기
            else {
                viewModel.requestLocalPark()
            }
        })

        //서버로부터 주차장 정보 가져오기
        viewModel.parkList.observe(this@MainActivity, Observer {
            //DB저장
            viewModel.insertLocalPark(it)
        })

        //DB로부터 주차장 정보 가져오기
        viewModel.parkLocalList.observe(this@MainActivity, Observer {
            //추후 작성
        })
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            //toastMsg가 변경 시 , 변경된 text로 toas를 띄워준다.
            toastMsg.observe(this@MainActivity, Observer {
                when (toastMsg.value) {
                    ParkInfoViewModel.MessageSet.NO_RESULT -> showToast(getString(R.string.no_result_msg))
                    ParkInfoViewModel.MessageSet.NETWORK_NOT_CONNECTED -> showToast(getString(R.string.not_connectied_network))
                    ParkInfoViewModel.MessageSet.REMOTE_SUCCESS -> showToast(getString(R.string.api_success_msg))
                    ParkInfoViewModel.MessageSet.REMOTE_CHECK_SUCCESS -> showToast(getString(R.string.api_check_success_msg))
                    ParkInfoViewModel.MessageSet.LOCAL_SUCCESS -> showToast(getString(R.string.db_success_msg))
                    ParkInfoViewModel.MessageSet.ERROR -> showToast(getString(R.string.api_error_msg))
                }
            })
        }
    }

    private fun initMapView() {
        var mapView = MapView(this)
        binding.mapView.addView(mapView)

        //현재 위치 표시
        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
    }
}