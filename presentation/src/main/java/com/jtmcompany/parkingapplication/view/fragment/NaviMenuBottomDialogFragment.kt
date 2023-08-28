package com.jtmcompany.parkingapplication.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.databinding.FragmentNaviMenuBottomDialogBinding
import com.kakao.sdk.navi.Constants
import com.kakao.sdk.navi.NaviClient
import com.kakao.sdk.navi.model.CoordType
import com.kakao.sdk.navi.model.Location
import com.kakao.sdk.navi.model.NaviOption
import com.skt.Tmap.TMapTapi
import com.skt.Tmap.TMapTapi.OnAuthenticationListenerCallback


class NaviMenuBottomDialogFragment(private val parkInfo: ParkInfo) : BottomSheetDialogFragment(),
    View.OnClickListener {

    private lateinit var binding: FragmentNaviMenuBottomDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_navi_menu_bottom_dialog,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        binding.layoutTmap.setOnClickListener(this)
        binding.layoutKakaoNavi.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.layout_tmap -> {
                val tMap = TMapTapi(requireContext())
                tMap.setSKTMapAuthentication(getString(R.string.tmap_native_app_key))
                tMap.setOnAuthenticationListener(object : OnAuthenticationListenerCallback {
                    override fun SKTMapApikeySucceed() {
                        Log.d("tak","isInstalled?: "+tMap.isTmapApplicationInstalled)
                        executeTmap(tMap)
                    }

                    override fun SKTMapApikeyFailed(p0: String?) {
                        Log.d("tak","fail: "+p0.toString())
                    }

                })
                executeTmap(tMap)
                this.dismiss()
            }

            R.id.layout_kakao_navi -> {
                executeKakaoNavi()
                this.dismiss()
            }
        }
    }

    fun executeTmap(tMap: TMapTapi){
        if (tMap.isTmapApplicationInstalled) {
            tMap.invokeRoute(
                parkInfo.prkplceNm!!,
                parkInfo.longitude!!.toFloat(),
                parkInfo.latitude!!.toFloat()
            )
        }else{
            val packageName = "com.skt.tmap.ku"
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$packageName")
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }

    fun executeKakaoNavi(){
        if (NaviClient.instance.isKakaoNaviInstalled(requireContext())) {
            parkInfo.let {
                activity?.startActivity(
                    NaviClient.instance.navigateIntent(
                        Location(
                            parkInfo.prkplceNm!!,
                            parkInfo.longitude!!,
                            parkInfo.latitude!!
                        ),
                        NaviOption(coordType = CoordType.WGS84)
                    )
                )
            }
        } else { //카카오 네비 미 설치
            activity?.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(Constants.WEB_NAVI_INSTALL)
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }
}