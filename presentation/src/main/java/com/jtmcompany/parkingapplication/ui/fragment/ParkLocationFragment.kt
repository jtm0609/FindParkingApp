package com.jtmcompany.parkingapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseFragment
import com.jtmcompany.parkingapplication.databinding.FragmentParkLocationBinding
import com.jtmcompany.parkingapplication.utils.Constants.KEY_USER_LATITUDE
import com.jtmcompany.parkingapplication.utils.Constants.KEY_USER_LOGITUDE
import com.jtmcompany.parkingapplication.utils.PrefManager
import com.jtmcompany.parkingapplication.ui.viewmodel.ParkInfoViewModel
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class ParkLocationFragment :
    BaseFragment<FragmentParkLocationBinding, ParkInfoViewModel>(R.layout.fragment_park_location),
    View.OnClickListener, MapView.CurrentLocationEventListener {

    override val viewModel: ParkInfoViewModel by activityViewModels()
    private lateinit var mapView: MapView

    //현재 사용자의 위치(위도)
    private var curLatitude = 0.0;

    //현재 사용자의 위치(위도)
    private var curLongitude = 0.0;

    //DB 업데이트가 필요한 경우
    private var isNeedUpdateDB = false
    override fun setBindingVariable(binding: FragmentParkLocationBinding) {
        with(binding){
            viewModel = this@ParkLocationFragment.viewModel
        }
    }


    override fun initView() {
        binding.layoutSearch.setOnClickListener(this)
        initMapView()
        //주차장 정보를 가져온다.
        viewModel.requestParkInfo(1)
    }

    override fun initObserver() {
        viewModel.totalCntCheck.observe(viewLifecycleOwner, Observer {
            val apiTotalCnt = it
            val localTotalCnt = PrefManager.getInt(mContext, "park_total_cnt")

            //주차장 totalCnt(서버 정보)가 변경 되었을 때 or 앱 최초 진입시 전체 주차장 정보 서버로부터 불러오기
            if (apiTotalCnt != localTotalCnt) {
                PrefManager.setInt(mContext, "park_total_cnt", apiTotalCnt)
                isNeedUpdateDB = true
                viewModel.requestParkInfo(apiTotalCnt)
            }
        })

        //서버로부터 주차장 정보 가져오기
        viewModel.parkList.observe(viewLifecycleOwner, Observer {
            //DB저장
            if(isNeedUpdateDB) {
                viewModel.insertLocalPark(it)
                isNeedUpdateDB = false
            }
        })
    }

    private fun initMapView() {
        mapView = MapView(mContext)
        binding.mapView.addView(mapView)
        mapView.setCurrentLocationEventListener(this)

        //현재 위치 표시
        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.layout_search -> {
                val bundle = bundleOf(
                    KEY_USER_LATITUDE to curLatitude,
                    KEY_USER_LOGITUDE to curLongitude
                )
                findNavController().navigate(R.id.action_parkLocationFragment_to_parkSearchFragment, bundle)
            }
        }
    }

    //현 위치 파악
    override fun onCurrentLocationUpdate(view: MapView?, currentLocation: MapPoint?, p2: Float) {
        currentLocation?.let {
            curLatitude = it.mapPointGeoCoord.latitude
            curLongitude = it.mapPointGeoCoord.longitude
        }
    }

    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {
    }

    override fun onCurrentLocationUpdateFailed(p0: MapView?) {
    }

    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {
    }

}