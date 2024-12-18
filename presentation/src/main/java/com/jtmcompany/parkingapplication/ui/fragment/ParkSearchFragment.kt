package com.jtmcompany.parkingapplication.ui.fragment

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.adapter.ParkListAdapter
import com.jtmcompany.parkingapplication.base.BaseFragment
import com.jtmcompany.parkingapplication.databinding.FragmentParkSearchBinding
import com.jtmcompany.parkingapplication.utils.Constants.KEY_USER_LATITUDE
import com.jtmcompany.parkingapplication.utils.Constants.KEY_USER_LOGITUDE
import com.jtmcompany.parkingapplication.ui.viewmodel.ParkInfoViewModel



class ParkSearchFragment :
    BaseFragment<FragmentParkSearchBinding, ParkInfoViewModel>(R.layout.fragment_park_search) {

    override val viewModel: ParkInfoViewModel by activityViewModels()
    private var userLatitude: Double = 0.0
    private var userLongitude: Double = 0.0

    private val parkListAdapter by lazy { ParkListAdapter(viewModel) }
    override fun setBindingVariable(binding: FragmentParkSearchBinding) {
        with(binding) {
            viewModel = this@ParkSearchFragment.viewModel
        }
    }

    override fun initView() {
        initRecyclerAdapter()
        initSpinnerAdapter()

        arguments?.let {
            userLatitude = it.getDouble(KEY_USER_LATITUDE)
            userLongitude = it.getDouble(KEY_USER_LOGITUDE)
        }
    }


    override fun initObserver() {
        viewModel.parkLocalList.observe(viewLifecycleOwner, Observer { parkInfoList ->
            if (binding.editKeword.text.toString().isEmpty()) {
                return@Observer;
            }
            val keyword = binding.editKeword.text.toString()
            val filteredParks = viewModel.filterParkList(
                parkInfoList,
                keyword,
                binding.spParkSection.selectedItem.toString(),
                binding.spParkType.selectedItem.toString(),
                binding.spParkCharge.selectedItem.toString(),
                userLatitude,
                userLongitude
            )

            onComplete(filteredParks)
        })

        viewModel.clickedParkInfo.observe(viewLifecycleOwner) { parkInfo ->
            val action = ParkSearchFragmentDirections.actionParkSearchFragmentToParkDetailFragment(
                parkInfo
            )
            nextFragment(action)
        }

        viewModel.clickedParkSearch.observe(viewLifecycleOwner) {
            //키보드 숨기기
            val editKeyword = binding.editKeword
            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            binding.layoutNoData.visibility = View.GONE
            imm.hideSoftInputFromWindow(editKeyword.windowToken, 0)
        }
    }


    private fun onComplete(searchResultList: List<ParkInfo>) {
        var sortedList = searchResultList
        if (searchResultList.isEmpty()) {
            binding.layoutNoData.visibility = View.VISIBLE
        } else {
            sortedList =searchResultList.sortedBy { it.distance }
        }

        parkListAdapter.submitList(sortedList)
    }

    private fun initRecyclerAdapter() {
        with(binding) {
            recyclerView.adapter = parkListAdapter
        }
    }

    private fun initSpinnerAdapter() {
        binding.spParkSection.adapter = getSpinnerAdapter(R.array.parking_lot_section)
        binding.spParkType.adapter = getSpinnerAdapter(R.array.parking_lot_type)
        binding.spParkCharge.adapter = getSpinnerAdapter(R.array.parking_charge_info)
    }

    private fun getSpinnerAdapter(resource: Int): ArrayAdapter<String> {
        val adapter = ArrayAdapter(
            mContext,
            R.layout.spinner_item,
            resources.getStringArray(resource)
        )
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        return adapter
    }
}