package com.example.myapplication.presentation.fundtransfer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.api.ApiRoutes
import com.example.myapplication.databinding.ItemFrequentTransferBinding
import com.example.myapplication.domain.model.fundtransfer.RecentTransactionsResponseItem

class FrequentRecycleAdapter(val recentTransactionslist: List<RecentTransactionsResponseItem?>) :
    RecyclerView.Adapter<FrequentRecycleAdapter.FrequentRecycleAdapterVH>() {
    //val recentTransactionsResponse: List<RecentTransactionsResponseItem?>? = null


    inner class FrequentRecycleAdapterVH(var binding: ItemFrequentTransferBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrequentRecycleAdapterVH {

        val binding: ItemFrequentTransferBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_frequent_transfer,
            parent,
            false)
       // return BindableViewHolder(binding)
        return FrequentRecycleAdapterVH(binding)
    }

    override fun onBindViewHolder(holder: FrequentRecycleAdapterVH, position: Int) {
        holder .binding.nameTV.text=recentTransactionslist[position]!!.payeeNicName
        holder .binding.typeTv.text=recentTransactionslist[position]!!.tranType
        holder .binding.amountTV.text="₹ ${recentTransactionslist[position]!!.tranAmount!!.amount.toString()}"
        holder .binding.dateTv.text=""

     val img=   when(recentTransactionslist[position]!!.bankIdentifier){

            "ICIC"->R.drawable.icici_i
         "SBIN"-> R.drawable.sbi
         "PNB"-> R.drawable.pnb
         else->R.drawable.bob
        }
       // Glide.with(holder.binding.root.context).load(img).into(holder.binding.img)


        if (recentTransactionslist[position]!!.bankIdentifier!!.isNotEmpty()) {
            //val bank: String = selectedPayee.ifscCode!!.substring(0, 4)
            Glide.with(holder.binding.root.context!!).load(ApiRoutes.bankIConUrl + recentTransactionslist[position]!!.bankIdentifier + ".png").into(holder.binding.img)
        }else
            Glide.with(holder.binding.root.context!!).load(ApiRoutes.bankIConUrl + "OTHER" + ".png").into(holder.binding.img)
    }

    override fun getItemCount(): Int {


        return recentTransactionslist.size


    }
}