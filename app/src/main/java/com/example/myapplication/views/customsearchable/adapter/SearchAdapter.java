package com.example.myapplication.views.customsearchable.adapter;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.api.ApiRoutes;
import com.example.myapplication.domain.model.payee.BankBranchResponseItem;
import com.example.myapplication.views.customsearchable.CustomSearchableConstants;
import com.example.myapplication.views.customsearchable.model.CustomSearchableInfo;
import com.example.myapplication.views.customsearchable.model.ResultItem;

import java.util.List;



/**
 * Fills the result line with the passed cursor
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BankBranchResponseItem> dataSet;

    // Constructors ________________________________________________________________________________
    public SearchAdapter (List<BankBranchResponseItem> dataSet) {
        this.dataSet = dataSet;
    }

    // Callbacks ___________________________________________________________________________________
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_searchable_row_details, parent, false);

        ViewHolder holder = new ViewHolder(view);

        // UI configuration
        if (CustomSearchableInfo.getResultItemHeight() != CustomSearchableConstants.UNSET_RESOURCES) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = CustomSearchableInfo.getResultItemHeight();
            view.setLayoutParams(params);
        }

        if (CustomSearchableInfo.getTextPrimaryColor() != CustomSearchableConstants.UNSET_RESOURCES) {
            holder.header.setTextColor(CustomSearchableInfo.getTextPrimaryColor());
        }

        if (CustomSearchableInfo.getResultItemHeaderTextSize() != CustomSearchableConstants.UNSET_RESOURCES) {
            holder.header.setTextSize(TypedValue.COMPLEX_UNIT_PX, CustomSearchableInfo.getResultItemHeaderTextSize());
        }

        if (CustomSearchableInfo.getTextHintColor() != CustomSearchableConstants.UNSET_RESOURCES) {
            holder.header.setHintTextColor(CustomSearchableInfo.getTextHintColor());
        }

        if (CustomSearchableInfo.getTextPrimaryColor() != CustomSearchableConstants.UNSET_RESOURCES) {
            holder.subHeader.setTextColor(CustomSearchableInfo.getTextPrimaryColor());
        }

        if (CustomSearchableInfo.getResultItemSubheaderTextSize() != CustomSearchableConstants.UNSET_RESOURCES) {
            holder.subHeader.setTextSize(TypedValue.COMPLEX_UNIT_PX, CustomSearchableInfo.getResultItemSubheaderTextSize());
        }

        if (CustomSearchableInfo.getTextHintColor() != CustomSearchableConstants.UNSET_RESOURCES) {
            holder.subHeader.setHintTextColor(CustomSearchableInfo.getTextHintColor());
        }

        if (CustomSearchableInfo.getSimpleSuggestionsLeftIcon() != CustomSearchableConstants.UNSET_RESOURCES) {
            holder.leftIcon.setImageResource(CustomSearchableInfo.getSimpleSuggestionsLeftIcon());
        }

        if (CustomSearchableInfo.getSimpleSuggestionsRightIcon() != CustomSearchableConstants.UNSET_RESOURCES) {
            holder.rightIcon.setImageResource(CustomSearchableInfo.getSimpleSuggestionsRightIcon());
        }

        // Change layout based on the user option of one or two-lines mode
        /*if (!CustomSearchableInfo.getIsTwoLineExhibition()) {
            holder.subHeader.setVisibility(TextView.GONE);
            holder.header.setTypeface(Typeface.DEFAULT);
            view.invalidate();
        }
*/
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.header.setText(dataSet.get(position).getBranchName());
        viewHolder.subHeader.setText(dataSet.get(position).getRoutingNumberDetails().get(0).getRoutingNumber());
        try {

         //   names.setText(countryNames.get(i).getCounterPartyNickName().replace(countryNames.get(i).getAccountID(),"").replace("-",""));
            if(!dataSet.get(position).getRoutingNumberDetails().get(0).getRoutingNumber().isEmpty()){
                String bank= dataSet.get(position).getRoutingNumberDetails().get(0).getRoutingNumber().substring(0,4);
                Glide.with(viewHolder.leftIcon.getContext()).load(ApiRoutes.bankIConUrl+bank+".png").into(viewHolder.leftIcon);
            }else
                Glide.with(viewHolder.leftIcon.getContext()).load(ApiRoutes.bankIConUrl+"OTHER"+".png").into(viewHolder.leftIcon);
        } catch (Exception e) {
           // names.setText(countryNames.get(i).getCounterPartyNickName());
            Glide.with(viewHolder.leftIcon.getContext()).load(ApiRoutes.bankIConUrl+"OTHER"+".png").into(viewHolder.leftIcon);
            e.printStackTrace();
        }
        //viewHolder.leftIcon.setImageResource(dataSet.get(position).getLeftIcon());
      //  viewHolder.rightIcon.setImageResource(dataSet.get(position).getRightIcon());
    }

    // Getters and Setters _________________________________________________________________________
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public BankBranchResponseItem getItem (Integer position) {
        return dataSet.get(position);
    }

    // View Holder _________________________________________________________________________________
    private class ViewHolder extends RecyclerView.ViewHolder {

        public TextView header;
        public TextView subHeader;
        public ImageView leftIcon;
        public ImageView rightIcon;

        public ViewHolder (View v) {
            super (v);

            this.header = (TextView) v.findViewById(R.id.rd_header_text);
            this.subHeader = (TextView) v.findViewById(R.id.rd_sub_header_text);
            this.leftIcon = (ImageView) v.findViewById(R.id.rd_left_icon);
            this.rightIcon = (ImageView) v.findViewById(R.id.rd_right_icon);
        }
    }
}
