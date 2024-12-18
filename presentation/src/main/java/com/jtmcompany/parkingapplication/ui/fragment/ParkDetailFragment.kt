package com.jtmcompany.parkingapplication.ui.fragment

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseFragment
import com.jtmcompany.parkingapplication.databinding.FragmentParkDetailBinding
import com.jtmcompany.parkingapplication.ui.viewmodel.ParkInfoViewModel
import com.jtmcompany.parkingapplication.utils.Constants


class ParkDetailFragment :
    BaseFragment<FragmentParkDetailBinding, ParkInfoViewModel>(R.layout.fragment_park_detail) {
    private lateinit var parkInfo: ParkInfo
    private val args: ParkDetailFragmentArgs by navArgs()
    override val viewModel: ParkInfoViewModel by activityViewModels()

    override fun initObserver() {
        viewModel.clickedGetDirection.observe(viewLifecycleOwner) {
            val bottomDialogFragment = NaviMenuBottomDialogFragment(parkInfo)
            activity?.let { bottomDialogFragment.show(it.supportFragmentManager, "") }
        }
    }


    override fun setBindingVariable(binding: FragmentParkDetailBinding) {
        with(binding) {
            viewModel = this@ParkDetailFragment.viewModel
            data = args.parkInfo
        }
    }

    override fun initView() {

    }
}