package com.icici.moneytransfer.fundtransfer;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.icici.moneytransfer.fundtransfer.CompletedFundFragment;
import com.icici.moneytransfer.fundtransfer.FrequentTransfersFragment;
import com.icici.moneytransfer.fundtransfer.ScheduledFragment;

public class FundFragmentsAdapter  extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    public FundFragmentsAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }
    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FrequentTransfersFragment homeFragment = new FrequentTransfersFragment();
                return homeFragment;
            case 1:
                ScheduledFragment scheduledFragment = new ScheduledFragment();
                return scheduledFragment;
            case 2:
                CompletedFundFragment completedFundFragment = new CompletedFundFragment();
                return completedFundFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}