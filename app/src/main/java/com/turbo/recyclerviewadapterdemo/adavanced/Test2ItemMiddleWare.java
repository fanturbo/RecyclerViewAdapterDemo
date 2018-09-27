package com.turbo.recyclerviewadapterdemo.adavanced;

/**
 * Created by tubro on 2018/9/27.
 */

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turbo.recyclerviewadapterdemo.R;
import com.turbo.recyclerviewadapterdemo.Test2;

public class Test2ItemMiddleWare extends SmartRecylerItemMiddleware<Test2ItemMiddleWare.Test2Item> {

    @Override
    public boolean isTarget(Object data) {
        return data instanceof Test2;
    }

    @Override
    public Test2Item createSmartItem(ViewGroup parent) {
        return new Test2Item(R.layout.item_layout_test_2, parent);
    }

    public class Test2Item extends SmartRecylerItem<Test2> {
        TextView test1;
        TextView test2;
        TextView test3;

        public Test2Item(int itemLayoutId, ViewGroup parent) {
            super(itemLayoutId, parent);
        }



        @Override
        protected void onSetData(int position, Test2 student) {
            test1.setText(student.getTest1());
            test2.setText(student.getTest2());
            test3.setText(student.getTest3());
        }

        @Override
        protected void configView(Context context) {

        }

        @Override
        protected void findViews() {
            test1 = (TextView) findViewById(R.id.test1);
            test2 = (TextView) findViewById(R.id.test2);
            test3 = (TextView) findViewById(R.id.test3);
        }
    }
}