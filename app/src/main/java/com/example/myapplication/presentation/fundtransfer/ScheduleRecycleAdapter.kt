package com.example.myapplication.presentation.fundtransfer

import android.annotation.SuppressLint
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemFrequentTransferBinding
import com.example.myapplication.databinding.ItemScheduleTransferBinding
import com.example.myapplication.domain.model.fundtransfer.ScheduleTLResponseItem
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class ScheduleRecycleAdapter(val list: List<ScheduleTLResponseItem?>) :
    RecyclerView.Adapter<ScheduleRecycleAdapter.FrequentRecycleAdapterVH>() {

    inner class FrequentRecycleAdapterVH(val binding: ItemScheduleTransferBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrequentRecycleAdapterVH {


        val binding: ItemScheduleTransferBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_schedule_transfer,
            parent,
            false
        )
        // return BindableViewHolder(binding)
        return FrequentRecycleAdapterVH(binding)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: FrequentRecycleAdapterVH, position: Int) {
        holder.binding.amountTV.text = "₹ ${list[position]!!.totalTransactionAmount ?: ""}"
        holder.binding.nameTV.text = list[position]!!.accountNickname ?: ""
        holder.binding.accountNo.text = list[position]!!.counterpartyID ?: "NA"

        if(list[position]!!.counterpartyID!=null&&list[position]!!.counterpartyID!!.length>23){
            if(list[position]!!.counterpartyID!!.contains(" ")){
             val  intSpace=   list[position]!!.counterpartyID!!.indexOfFirst { c: Char ->
                 Log.e("-------inff", "onBindViewHolder: "+c )
                 c == ' '
             }

                holder.binding.accountNo.text = list[position]!!.counterpartyID ?.substring(13,intSpace)
            }else
                holder.binding.accountNo.text = list[position]!!.counterpartyID ?.substring(13)
        }
        holder.binding.dateTv.text = list[position]!!.transactionDate ?: "NA"
        try {
            val secondApiFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val dateT=  secondApiFormat.parse(list[position]!!.transactionDate!!)
            val secondApiFormat1 = SimpleDateFormat("dd MMMM yyyy")
            val dateSrt=   secondApiFormat1.format(dateT)
            holder.binding.dateTv.text = dateSrt
        } catch (e: Exception) {
        }



    }
    //000000000019|
    //000000000254

    override fun getItemCount(): Int {


        return list.size


    }
}