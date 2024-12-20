package com.jtmcompany.parkingapplication.ui.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.base.BaseFragment
import com.jtmcompany.parkingapplication.databinding.FragmentParkDetailBinding
import com.jtmcompany.parkingapplication.ui.viewmodel.ParkDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


class ParkDetailFragment :
    BaseFragment<FragmentParkDetailBinding, ParkDetailViewModel>(R.layout.fragment_park_detail) {
    private val args: ParkDetailFragmentArgs by navArgs()
    override val viewModel: ParkDetailViewModel by viewModels()

    override fun initObserver() {
        viewModel.clickedGetDirection.observe(viewLifecycleOwner) {
            val bottomDialogFragment = NaviMenuBottomDialogFragment(args.parkInfo)
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