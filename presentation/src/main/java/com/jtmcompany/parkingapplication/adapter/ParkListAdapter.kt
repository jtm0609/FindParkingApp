package com.jtmcompany.parkingapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jtmcompany.domain.model.ParkInfo
import com.jtmcompany.parkingapplication.R
import com.jtmcompany.parkingapplication.databinding.ItemParkingBinding
import com.jtmcompany.parkingapplication.ui.viewmodel.ParkSearchViewModel

class ParkListAdapter(private val viewModel: ParkSearchViewModel) :
    RecyclerView.Adapter<ParkListAdapter.ViewHolder>() {

    private var parkList: List<ParkInfo> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemParkingBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_parking,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return parkList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(parkList[position])
    }

    inner class ViewHolder(var binding: ItemParkingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ParkInfo) {
            with(binding) {
                data = item
                viewModel = this@ParkListAdapter.viewModel
            }
        }
    }

    fun submitList(list : List<ParkInfo>) {
        parkList = list
        notifyDataSetChanged()
    }
}