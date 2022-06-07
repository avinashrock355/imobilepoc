package com.icici.moneytransfer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icici.moneytransfer.data.api.ApiRoutes;
import com.icici.moneytransfer.domain.model.fundtransfer.PayeeListResponseItem;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
   // int flags[];
    List<PayeeListResponseItem> countryNames;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext,
                         List<PayeeListResponseItem>  countryNames) {
        this.context = applicationContext;
      //  this.flags = flags;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryNames.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        TextView textViewIfsc = (TextView) view.findViewById(R.id.textViewIfsc);
        if(countryNames.get(i).getCounterPartyNickName().equals("Select A Payee Account")){
            textViewIfsc.setVisibility(View.GONE);
            //Select A Payee Account
        }else{
            textViewIfsc.setVisibility(View.VISIBLE);
        }
        //icon.setImageResource(flags[i]);

       // Glide.with(context).load(R.drawable.sbi).into(icon);
        try {

            names.setText(countryNames.get(i).getCounterPartyNickName().replace(countryNames.get(i).getAccountID(),"").replace("-",""));
           if(!countryNames.get(i).getIfscCode().isEmpty()){
              String bank= countryNames.get(i).getIfscCode().substring(0,4);
               Glide.with(context).load(ApiRoutes.bankIConUrl+bank+".png").into(icon);
           }else
               Glide.with(context).load(ApiRoutes.bankIConUrl+"OTHER"+".png").into(icon);
        } catch (Exception e) {
            names.setText(countryNames.get(i).getCounterPartyNickName());
            Glide.with(context).load(ApiRoutes.bankIConUrl+"OTHER"+".png").into(icon);
            e.printStackTrace();
        }
        textViewIfsc.setText(countryNames.get(i).getAccountID());
        return view;
    }
}
