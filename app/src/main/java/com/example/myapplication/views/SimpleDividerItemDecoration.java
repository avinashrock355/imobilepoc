package com.example.myapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;


public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int leftMargin, rightMargin;

    public SimpleDividerItemDecoration(Context context, int leftMargin, int rightMargin) {
        mDivider = context.getResources().getDrawable(R.drawable.vertical_divider);
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = 0;
        int right = 0;
        if (leftMargin == 0) {
            left = parent.getPaddingLeft();
        } else {
            left = leftMargin;
        }
        if (rightMargin == 0) {
            right = parent.getWidth() - parent.getPaddingRight();
        } else {
            right = parent.getWidth() - rightMargin;
        }
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
