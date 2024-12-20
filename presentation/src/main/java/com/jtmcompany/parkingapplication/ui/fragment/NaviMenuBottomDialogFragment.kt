package com.jtmcompany.parkingapplication.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseBottomDialogFragment
import com.jtmcompany.parkingapplication.databinding.FragmentNaviMenuBottomDialogBinding
import com.jtmcompany.parkingapplication.ui.viewmodel.NaviMenuDialogViewModel
import com.kakao.sdk.navi.Constants
import com.kakao.sdk.navi.NaviClient
import com.kakao.sdk.navi.model.CoordType
import com.kakao.sdk.navi.model.Location
import com.kakao.sdk.navi.model.NaviOption
import com.skt.Tmap.MapUtils
import com.skt.Tmap.TMapTapi
import com.skt.Tmap.TMapTapi.OnAuthenticationListenerCallback


class NaviMenuBottomDialogFragment(
    private val parkInfo: ParkInfo,
) : BaseBottomDialogFragment<FragmentNaviMenuBottomDialogBinding, NaviMenuDialogViewModel>(R.layout.fragment_navi_menu_bottom_dialog) {

    override val viewModel: NaviMenuDialogViewModel by viewModels()

    companion object {
        private const val tmapMarketUrl = "market://details?id=com.skt.tmap.ku"
        private const val kakaoMarketUrl = "market://details?id=com.locnall.KimGiSa"
    }

    override fun initView() {

    }

    override fun initObserver() {
        viewModel.clickedTmapNavi.observe(viewLifecycleOwner) {
            val tMap = TMapTapi(mContext)
            executeTmap(tMap)
            this.dismiss()
        }

        viewModel.clickedKakaoNavi.observe(viewLifecycleOwner) {
            executeKakaoNavi()
            this.dismiss()
        }
    }

    override fun setBindingVariable(binding: FragmentNaviMenuBottomDialogBinding) {
        with(binding) {
            binding.viewModel = this@NaviMenuBottomDialogFragment.viewModel
        }
    }

    private fun executeTmap(tMap: TMapTapi) {
        if (tMap.isTmapApplicationInstalled) {
            tMap.invokeRoute(
                parkInfo.prkplceNm!!, parkInfo.longitude.toFloat(), parkInfo.latitude.toFloat()
            )
        } else {
            goToAppStore(Uri.parse(tmapMarketUrl))
        }
    }

    private fun executeKakaoNavi() {
        if (NaviClient.instance.isKakaoNaviInstalled(mContext)) {
            parkInfo.let {
                activity?.startActivity(
                    NaviClient.instance.navigateIntent(
                        Location(
                            parkInfo.prkplceNm!!,
                            parkInfo.longitude,
                            parkInfo.latitude
                        ),
                        NaviOption(coordType = CoordType.WGS84)
                    )
                )
            }
        } else { //카카오 네비 미 설치
            goToAppStore(Uri.parse(kakaoMarketUrl))
        }
    }

    private fun goToAppStore(url: Uri) {
        activity?.startActivity(
            Intent(
                Intent.ACTION_VIEW, url
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }
}