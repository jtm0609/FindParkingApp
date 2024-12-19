package com.jtmcompany.parkingapplication.ui.fragment

import androidx.fragment.app.viewModels
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseFragment
import com.jtmcompany.parkingapplication.databinding.FragmentParkLocationBinding
import com.jtmcompany.parkingapplication.utils.PrefManager
import com.jtmcompany.parkingapplication.ui.viewmodel.ParkLocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class ParkLocationFragment :
    BaseFragment<FragmentParkLocationBinding, ParkLocationViewModel>(R.layout.fragment_park_location),
    MapView.CurrentLocationEventListener {

    override val viewModel: ParkLocationViewModel by viewModels()
    private val mapView: MapView by lazy { MapView(mContext) }

    //DB 업데이트가 필요한 경우
    private var isNeedUpdateDB = false
    override fun setBindingVariable(binding: FragmentParkLocationBinding) {
        with(binding) {
            viewModel = this@ParkLocationFragment.viewModel
        }
    }

    override fun initView() {
        initMapView()
        //주차장 정보를 가져온다.
        viewModel.requestParkInfo(1)
    }

    override fun initObserver() {
        viewModel.totalCnt.observe(viewLifecycleOwner) {
            val apiTotalCnt = it
            val localTotalCnt = PrefManager.getInt(mContext, "park_total_cnt")

            //주차장 totalCnt(서버 정보)가 변경 되었을 때 or 앱 최초 진입시 전체 주차장 정보 서버로부터 불러오기
            if (apiTotalCnt != localTotalCnt) {
                PrefManager.setInt(mContext, "park_total_cnt", apiTotalCnt)
                isNeedUpdateDB = true
                viewModel.requestParkInfo(apiTotalCnt)
            }
        }

        //서버로부터 주차장 정보 가져오기
        viewModel.parkList.observe(viewLifecycleOwner) {
            //DB저장
            if (isNeedUpdateDB) {
                viewModel.insertLocalPark(it)
                isNeedUpdateDB = false
            }
        }

        viewModel.clickedSearch.observe(viewLifecycleOwner) {
            val action = ParkLocationFragmentDirections.actionParkLocationFragmentToParkSearchFragment(
                viewModel.userLatitude.toFloat(),
                viewModel.userLongitude.toFloat()
            )
            nextFragment(action)
        }
    }

    private fun initMapView() {
        binding.mapView.addView(mapView)
        mapView.setCurrentLocationEventListener(this)

        //현재 위치 표시
        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
    }

    //현 위치 파악 (카카오 SDK)
    override fun onCurrentLocationUpdate(view: MapView?, currentLocation: MapPoint?, p2: Float) {
        currentLocation?.let { mapPoint ->
            viewModel.setUserLocation(
                mapPoint.mapPointGeoCoord.latitude,
                mapPoint.mapPointGeoCoord.longitude
            )
        }
    }

    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {
    }

    override fun onCurrentLocationUpdateFailed(p0: MapView?) {
    }

    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {
    }

}