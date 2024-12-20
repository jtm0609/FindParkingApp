package com.jtmcompany.parkingapplication.ui.fragment

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.fragment.app.viewModels
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseFragment
import com.jtmcompany.parkingapplication.databinding.FragmentParkLocationBinding
import com.jtmcompany.parkingapplication.ui.viewmodel.ParkLocationViewModel
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class ParkLocationFragment :
    BaseFragment<FragmentParkLocationBinding, ParkLocationViewModel>(R.layout.fragment_park_location) {

    override val viewModel: ParkLocationViewModel by viewModels()
    private val fusedLocationProviderClient by lazy {LocationServices.getFusedLocationProviderClient(mContext)}
    private lateinit var locationCallback : LocationCallback
    private lateinit var kakaoMap : KakaoMap

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

    private fun initMapView() {
        binding.mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                Log.d("taek", "onMapDestroy")
            }

            override fun onMapError(exception: Exception?) {
                Log.d("taek", "onMapError: $exception")
            }

        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {
                Log.d("taek", "onMapReady")
                this@ParkLocationFragment.kakaoMap = kakaoMap
                setLocationListener()
            }
        })
    }

    override fun initObserver() {
        viewModel.clickedSearch.observe(viewLifecycleOwner) {
            val action =
                ParkLocationFragmentDirections.actionParkLocationFragmentToParkSearchFragment(
                    viewModel.userLatitude.toFloat(),
                    viewModel.userLongitude.toFloat()
                )
            nextFragment(action)
        }
    }

    @SuppressLint("MissingPermission")
    private fun setLocationListener() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {location ->
            updateLocation(location)
        }

        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(location: LocationResult) {
                super.onLocationResult(location)
                updateLocation(location.lastLocation)
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun updateLocation(location: Location?) {
        val latitude = location?.latitude ?: 37.0
        val longitude = location?.longitude ?: 127.0
        val label = kakaoCreateLabel(latitude, longitude)

        val trackingManager = kakaoMap.trackingManager
        trackingManager?.startTracking(label)
        trackingManager?.setTrackingRotation(true)
        kakaoMap.compass?.show()

        viewModel.setUserLocation(latitude, longitude)
    }

    private fun kakaoCreateLabel(latitude: Double, longitude: Double) : Label? {
        val style =
            kakaoMap.labelManager?.addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.marker)))
        val options = LabelOptions.from(
            LatLng.from(
                latitude,
                longitude
            )
        ).setStyles(style)
        val layer = kakaoMap.labelManager?.layer
        layer?.removeAll()
        return layer?.addLabel(options)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }
}