package com.jtmcompany.parkingapplication.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.databinding.ItemParkingBinding
import com.jtmcompany.parkingapplication.listener.ItemClickListener
import com.jtmcompany.parkingapplication.utils.Constants
import com.jtmcompany.parkingapplication.utils.GeoDistanceManager

class ParkListAdapter(private val parkList: ArrayList<ParkInfo>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<ParkListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemParkingBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_parking, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return parkList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parkInfo = parkList[position]
        holder.txtName.text = parkInfo.prkplceNm //주차장 이름

        var address = parkInfo.rdnmadr //도로명
        if(TextUtils.isEmpty(address)) {
            address = parkInfo.lnmadr // 지번
        }
        if(!TextUtils.isEmpty(address)) {
            holder.txtAddress.text = address
        }

        //주차장 구분, 주차장 유형, 요금 정보
        val detail = parkInfo.prkplceSe + " " + parkInfo.prkplceType + " " + parkInfo.parkingchrgeInfo
        holder.txtDetail.text = detail

        //거리 표시
        if(parkInfo.distance == Constants.NONE_DISTANCE) { //거리 정보가 없으면
            holder.txtDistance.text = ""
        }else{
            holder.txtDistance.text = GeoDistanceManager.getDistanceStr(parkInfo.distance)
        }

        holder.layoutItem.setOnClickListener {
            itemClickListener.onItemClick(parkInfo)
        }
    }

    inner class ViewHolder(binding: ItemParkingBinding) : RecyclerView.ViewHolder(binding.root) {
        val layoutItem = binding.itemLayout
        val txtName = binding.txtName
        val txtAddress = binding.txtAddress
        val txtDetail = binding.txtDetail
        val txtDistance = binding.txtDistance
    }
}