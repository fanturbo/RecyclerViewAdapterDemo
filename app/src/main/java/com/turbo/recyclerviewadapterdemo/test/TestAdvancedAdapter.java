package com.turbo.recyclerviewadapterdemo.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turbo.recyclerviewadapterdemo.R;

import java.util.List;

/**
 * Created by 61595 on 2018/9/27.
 */

public class TestAdvancedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> objectList;
    //key 是 itemType的值，value 就是我们的中间件。
    SparseArray<RecyclerViewAdapterMiddleWare> recyclerViewAdapterMiddleWareList;
    /**
     * 对于多种布局来说，itemType的值都是从0开始，布局越多 这个值越大 默认为0
     */
    private int itemTypeIndex;

    public TestAdvancedAdapter(List<Object> objectList) {
        this.objectList = objectList;
        this.recyclerViewAdapterMiddleWareList = new SparseArray<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //如果不使用中间件的话，这里熟悉的代码应该是根据不同的viewtype的值
        //来返回不同的layout布局
        //这里我们就直接根据viewtype的值 来取出我们对应的中间件
        RecyclerViewAdapterMiddleWare middleWare = recyclerViewAdapterMiddleWareList.get(viewType);
        return middleWare.getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).setData(position, objectList.get(position));
        }
    }

    public void addMiddleWare(RecyclerViewAdapterMiddleWare middleWare) {
        middleWare.setItemType(itemTypeIndex);
        recyclerViewAdapterMiddleWareList.put(itemTypeIndex++, middleWare);
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = 0;
        for (int i = 0; i < recyclerViewAdapterMiddleWareList.size(); i++) {
            RecyclerViewAdapterMiddleWare valueAt = recyclerViewAdapterMiddleWareList.valueAt(i);
            if (valueAt.instanceOf(objectList.get(position))) {
                itemType = valueAt.getItemViewType();
                break;
            }
        }
        return itemType;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
    /**
     * 中间件，这个类的主要作用就是桥接adapter和viewholder使用
     */
    public static abstract class RecyclerViewAdapterMiddleWare {

        /**
         * 这个值就是在多布局的情况下，返回的itemType的值，我们在中间件里储存一下这个值
         * 方便使用
         */
        private int itemType;

        public RecyclerViewAdapterMiddleWare() {

        }

        public abstract RecyclerView.ViewHolder getViewHolder(ViewGroup parent);

        public abstract boolean instanceOf(Object o);

        public int getItemViewType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }

    public static abstract class ViewHolder<T> extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            findViews();
        }

        protected abstract void setData(int position, T o);

        protected abstract void findViews();

        protected View findViewById(int id) {
            return itemView.findViewById(id);
        }
    }
}
