package com.jtmcompany.parkingapplication.ui.fragment

import android.view.View
import androidx.fragment.app.viewModels
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseFragment
import com.jtmcompany.parkingapplication.databinding.FragmentParkDetailBinding
import com.jtmcompany.parkingapplication.ui.viewmodel.ParkInfoViewModel
import com.jtmcompany.parkingapplication.utils.Constants


class ParkDetailFragment : BaseFragment<FragmentParkDetailBinding, ParkInfoViewModel>(R.layout.fragment_park_detail),
    View.OnClickListener {
    private lateinit var parkInfo: ParkInfo

    override val viewModel: ParkInfoViewModel by viewModels()

    override fun initObserver() {
        TODO("Not yet implemented")
    }


    override fun setBindingVariable(binding: FragmentParkDetailBinding) {
        with(binding){
            viewModel = this@ParkDetailFragment.viewModel
        }
    }

    override fun initView() {
        arguments?.let {
            parkInfo = it.getSerializable(Constants.KEY_SELECT_PARK_INFO) as ParkInfo
            binding.txtParkingLotName.text = parkInfo.prkplceNm
            binding.txtDistance.text = viewModel.getDistanceStr(parkInfo.distance)
            binding.txtPrkplceSe.text = parkInfo.prkplceSe
            binding.txtPrkplceType.text = parkInfo.prkplceType
            binding.txtRdnmadr.text = parkInfo.rdnmadr
            binding.txtLnmadr.text = parkInfo.lnmadr
            binding.txtOperDay.text = parkInfo.operDay
            binding.txtWeekdayOperOpenHhmm.text = parkInfo.weekdayOperOpenHhmm
            binding.txtWeekdayOperColseHhmm.text = parkInfo.weekdayOperColseHhmm
            binding.txtSatOperOperOpenHhmm.text = parkInfo.satOperOperOpenHhmm
            binding.txtSatOperCloseHhmm.text = parkInfo.satOperCloseHhmm
            binding.txtHolidayOperOpenHhmm.text = parkInfo.holidayOperOpenHhmm
            binding.txtHolidayCloseOpenHhmm.text = parkInfo.holidayCloseOpenHhmm
            binding.txtParkingchrgeInfo.text = parkInfo.parkingchrgeInfo
            binding.txtBasicTime.text = parkInfo.basicTime
            binding.txtBasicCharge.text = parkInfo.basicCharge
            binding.txtAddUnitTime.text = parkInfo.addUnitTime
            binding.txtAddUnitCharge.text = parkInfo.addUnitCharge
            binding.txtDayCmmtktAdjTime.text = parkInfo.dayCmmtktAdjTime
            binding.txtDayCmmtkt.text = parkInfo.dayCmmtkt
            binding.txtMonthCmmtkt.text = parkInfo.monthCmmtkt
            binding.txtMetpay.text = parkInfo.metpay
            binding.txtSpcmnt.text = parkInfo.spcmnt
            binding.txtInstitutionNm.text = parkInfo.institutionNm
            binding.txtPhoneNumber.text = parkInfo.phoneNumber
        }

        binding.btnGetDirections.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnGetDirections -> {
                val bottomDialogFragment =NaviMenuBottomDialogFragment(parkInfo)
                activity?.let { bottomDialogFragment.show(it.supportFragmentManager,"") }
            }
        }
    }
}