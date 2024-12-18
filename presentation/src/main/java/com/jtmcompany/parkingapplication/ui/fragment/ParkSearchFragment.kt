package com.jtmcompany.parkingapplication.ui.fragment

import android.content.Context
import android.text.TextUtils
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
import com.jtmcompany.parkingapplication.listener.ItemClickListener
import com.jtmcompany.parkingapplication.utils.Constants.KEY_SELECT_PARK_INFO
import com.jtmcompany.parkingapplication.utils.Constants.KEY_USER_LATITUDE
import com.jtmcompany.parkingapplication.utils.Constants.KEY_USER_LOGITUDE
import com.jtmcompany.parkingapplication.utils.Constants.NONE_DISTANCE
import com.jtmcompany.parkingapplication.utils.GeoDistanceManager
import com.jtmcompany.parkingapplication.utils.TypeCheckManager
import com.jtmcompany.parkingapplication.ui.viewmodel.ParkInfoViewModel
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


class ParkSearchFragment : BaseFragment<FragmentParkSearchBinding, ParkInfoViewModel>(R.layout.fragment_park_search),
    View.OnClickListener {

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


        binding.btSearch.setOnClickListener(this)

        arguments?.let {
            userLatitude = it.getDouble(KEY_USER_LATITUDE)
            userLongitude = it.getDouble(KEY_USER_LOGITUDE)

            Log.d("tak", "userLiatutude: $userLatitude")
            Log.d("tak", "userLongitude: $userLongitude")
        }
    }


    override fun initObserver() {
        viewModel.parkLocalList.observe(viewLifecycleOwner, Observer { parkInfoList ->
            if(binding.editKeword.text.toString().isEmpty()){
                return@Observer;
            }

            Log.d("tak","parkInfoList size: "+parkInfoList.size)
            val searchResultList = ArrayList<ParkInfo>()
            val keyword = binding.editKeword.text.toString()
            for (parkInfo in parkInfoList) {
                var isPassCondition = true //조건 부합 여부
                var isHasResult = true //검색 결과가 있는지

                //주차장 구분
                if (binding.spParkSection.selectedItemPosition > 0) {
                    parkInfo.prkplceSe?.let {
                        isPassCondition = it == binding.spParkSection.selectedItem.toString()
                    }
                }

                //주차장 유형
                if (binding.spParkType.selectedItemPosition > 0) {
                    parkInfo.prkplceType.let {
                        isPassCondition = it == binding.spParkType.selectedItem.toString()
                    }
                }

                //요금 정보
                if (binding.spParkCharge.selectedItemPosition > 0) {
                    parkInfo.parkingchrgeInfo.let {
                        isPassCondition = it == binding.spParkCharge.selectedItem.toString()
                    }
                }

                if (isPassCondition) {
                    isPassCondition = false
                    isHasResult = false
                    parkInfo.prkplceNm?.let {//주차장 이름 체크
                        if (it.contains(keyword)) {
                            isHasResult = true
                        } else { //도로명 주소 또는 지번 주소 체크
                            if (!TextUtils.isEmpty(parkInfo.rdnmadr) //도로명 주소
                                && parkInfo.rdnmadr!!.contains(keyword)
                            ) {
                                isHasResult = true
                            } else if (!TextUtils.isEmpty(parkInfo.lnmadr) //지번 주소
                                && parkInfo.lnmadr!!.contains(keyword)
                            ) {
                                isHasResult = true
                            }
                        }
                    }
                }

                //결과 item list에 넣기
                if (isHasResult) {
                    //나와의 거리 계산
                    if (TypeCheckManager.isNumeric(parkInfo.latitude)
                        && TypeCheckManager.isNumeric(parkInfo.longitude)
                    ) {
                        parkInfo.distance = GeoDistanceManager.getDistance(
                            userLatitude,
                            userLongitude,
                            parkInfo.latitude!!.toDouble(),
                            parkInfo.longitude!!.toDouble()
                        )
                    } else {
                        //거리 계산 안됨
                        parkInfo.distance = NONE_DISTANCE.toDouble()
                    }
                    searchResultList.add(parkInfo)
                }
            }

            onComplete(searchResultList)
        })

        viewModel.clickedParkInfo.observe(viewLifecycleOwner) {
            Log.d("tak","ItemClick: "+parkInfo.prkplceNm)
            val bundle = bundleOf(KEY_SELECT_PARK_INFO to parkInfo)
            findNavController().navigate(R.id.action_parkSearchFragment_to_parkDetailFragment, bundle)
        }
    }


    private fun onComplete(searchResultList: ArrayList<ParkInfo>) {
        if(searchResultList.size == 0){
            binding.layoutNoData.visibility = View.VISIBLE
        }else{
            //거리 순으로 정렬
            Collections.sort(searchResultList, getComparator())
        }

        parkListAdapter.submitList(searchResultList)
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

    //오름차순 정렬(거리)
    private fun getComparator() : Comparator<ParkInfo> {
        val comparator = Comparator<ParkInfo> { item1, item2 ->
            item1.distance.compareTo(item2.distance)
        }
        return comparator
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_search -> {
                val editKeyword = binding.editKeword
                val keyword = editKeyword.text.toString()
                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                //주차장 입력 체크
                if (TextUtils.isEmpty(keyword)) {
                    showToast(getString(R.string.msg_check_empty_park_))
                    editKeyword.requestFocus()
                    return
                }

                //주차장 자리 체크
                if (keyword.length < 2) {
                    showToast(getString(R.string.msg_check_word_count))
                    return
                }
                binding.layoutNoData.visibility = View.GONE
                //키보드 숨기기
                imm.hideSoftInputFromWindow(editKeyword.windowToken, 0)
                viewModel.requestLocalPark()
            }
        }
    }

}