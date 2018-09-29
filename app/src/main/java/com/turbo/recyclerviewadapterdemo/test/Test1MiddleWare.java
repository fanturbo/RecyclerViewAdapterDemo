package com.turbo.recyclerviewadapterdemo.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turbo.recyclerviewadapterdemo.R;
import com.turbo.recyclerviewadapterdemo.Test1;

/**
 * Created by tubro on 2018/9/28.
 */

public class Test1MiddleWare extends TestAdvancedAdapter.RecyclerViewAdapterMiddleWare {

    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup parent) {
        return new TestAdvancedAdapter.ViewHolder<Test1>(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_test_1, null)) {

            TextView test1;

            @Override
            public void setData(int position, Test1 t) {
                test1.setText(t.getTest1());
            }

            @Override
            protected void findViews() {
                test1 = (TextView) findViewById(R.id.test1);
            }
        };
    }

    @Override
    public boolean instanceOf(Object o) {
        return o instanceof Test1;
    }
}
