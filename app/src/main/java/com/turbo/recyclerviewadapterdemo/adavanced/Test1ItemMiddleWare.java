package com.turbo.recyclerviewadapterdemo.adavanced;

/**
 * Created by tubro on 2018/9/27.
 */


import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turbo.recyclerviewadapterdemo.R;
import com.turbo.recyclerviewadapterdemo.Test1;

/**
 * Created by tubro on 2018/3/23.
 */

public class Test1ItemMiddleWare extends SmartRecylerItemMiddleware<Test1ItemMiddleWare.Test1Item> {

    @Override
    public boolean isTarget(Object data) {
        return data instanceof Test1;
    }

    @Override
    public Test1Item createSmartItem(ViewGroup parent) {
        return new Test1Item(R.layout.item_layout_test_1, parent);
    }

    public class Test1Item extends SmartRecylerItem<Test1> {

        TextView test1;

        public Test1Item(int itemLayoutId, ViewGroup parent) {
            super(itemLayoutId, parent);
        }


        @Override
        protected void onSetData(int position, Test1 t) {
            test1.setText(t.getTest1());
        }

        @Override
        protected void configView(Context context) {

        }

        @Override
        protected void findViews() {
            test1 = (TextView) findViewById(R.id.test1);
        }
    }
}