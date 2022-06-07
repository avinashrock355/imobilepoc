package com.icici.moneytransfer.fundtransfer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.icici.moneytransfer.R
import com.icici.moneytransfer.databinding.ItemCompletedTransferBinding
import com.icici.moneytransfer.databinding.ItemScheduleTransferBinding
import com.icici.moneytransfer.domain.model.fundtransfer.CompletedFTResponseItem
import java.text.SimpleDateFormat

class CompletedFundRecycleAdapter(val list: List<CompletedFTResponseItem?>) :
    RecyclerView.Adapter<CompletedFundRecycleAdapter.FrequentRecycleAdapterVH>() {

    inner class FrequentRecycleAdapterVH(val binding: ItemCompletedTransferBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrequentRecycleAdapterVH {

        val binding: ItemCompletedTransferBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_completed_transfer,
            parent,
            false
        )
        // return BindableViewHolder(binding)
        return FrequentRecycleAdapterVH(binding)
    }

    override fun onBindViewHolder(holder: FrequentRecycleAdapterVH, position: Int) {
        holder.binding.accountNo.text = "NA"
        holder.binding.nameTV.text = list[position]!!.toAccountNickname!!.toString()
        holder.binding.amountTV.text = list[position]!!.totalTransactionAmount!!.toString()
        holder.binding.dateTv.text = list[position]!!.transactionDate!!.toString()

        try {
            val secondApiFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val dateT = secondApiFormat.parse(list[position]!!.transactionDate!!)
            val secondApiFormat1 = SimpleDateFormat("dd MMMM")
            val dateSrt = secondApiFormat1.format(dateT)
            holder.binding.dateTv.text = dateSrt
        } catch (e: Exception) {
        }

    }

    override fun getItemCount(): Int {


        return list.size


    }
}