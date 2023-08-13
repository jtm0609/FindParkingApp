package com.jtmcompany.parkingapplication.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseFragment
import com.jtmcompany.parkingapplication.databinding.FragmentParkSearchBinding
import com.jtmcompany.parkingapplication.view.ParkInfoViewModel


class ParkSearchFragment : BaseFragment<FragmentParkSearchBinding>(R.layout.fragment_park_search) {

    private val viewModel : ParkInfoViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        initLayout()
    }

    fun initLayout(){
        val parkingSectionAdapter = ArrayAdapter<String>(mContext,
            R.layout.spinner_item,
            resources.getStringArray(R.array.parking_lot_section))
        parkingSectionAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        binding.spinnerParkSection.adapter = parkingSectionAdapter

        val parkingTypeAdapter = ArrayAdapter<String>(mContext,
            R.layout.spinner_item,
            resources.getStringArray(R.array.parking_lot_type))
        parkingTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        binding.spinnerParkType.adapter = parkingTypeAdapter

        val parkingChargeAdapter = ArrayAdapter<String>(mContext,
            R.layout.spinner_item,
            resources.getStringArray(R.array.parking_charge_info))
        parkingChargeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        binding.spinnerParkCharge.adapter = parkingChargeAdapter
    }

}