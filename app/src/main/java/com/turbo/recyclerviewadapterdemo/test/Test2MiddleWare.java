package com.turbo.recyclerviewadapterdemo.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turbo.recyclerviewadapterdemo.R;
import com.turbo.recyclerviewadapterdemo.Test1;
import com.turbo.recyclerviewadapterdemo.Test2;

/**
 * Created by tubro on 2018/9/28.
 */

public class Test2MiddleWare extends TestAdvancedAdapter.RecyclerViewAdapterMiddleWare {

    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup parent) {
        return new TestAdvancedAdapter.ViewHolder<Test2>(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_test_2, null)) {
            TextView test1;
            TextView test2;
            TextView test3;
            @Override
            public void setData(int position, Test2 student) {
                test1.setText(student.getTest1());
                test2.setText(student.getTest2());
                test3.setText(student.getTest3());
            }

            @Override
            protected void findViews() {
                test1 = (TextView) findViewById(R.id.test1);
                test2 = (TextView) findViewById(R.id.test2);
                test3 = (TextView) findViewById(R.id.test3);
            }
        };
    }

    @Override
    public boolean instanceOf(Object o) {
        return o instanceof Test2;
    }
}
