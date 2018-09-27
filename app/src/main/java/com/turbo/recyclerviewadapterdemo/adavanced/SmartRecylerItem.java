package com.turbo.recyclerviewadapterdemo.adavanced;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tubro on 2018/4/2.
 */
public abstract class SmartRecylerItem<T> extends RecyclerView.ViewHolder {
    private T data;

    /**
     * 这个构造函数其实并不会直接调用
     * 是为了给另外一个真正使用的构造函数使用
     *
     * @param itemView
     */
    public SmartRecylerItem(View itemView) {
        super(itemView);
    }

    /**
     * 真正使用的构造函数是这个
     *
     * @param itemLayoutId
     * @param parent
     */
    public SmartRecylerItem(int itemLayoutId, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, null));
    }

    /**
     * 这个方法不多说了
     * @param id
     * @return
     */
    public View findViewById(int id) {
        return itemView.findViewById(id);
    }

    /**
     * adapter代码里 会直接调用这个bind方法
     *
     * @param position
     * @param data
     */
    public void bindData(int position, T data) {
        this.data = data;
        onSetData(position, data);
    }

    /**
     * 设置数据 这个方法交给子类来实现
     *
     * @param position 位置
     * @param data     数据
     */
    protected abstract void onSetData(int position, T data);

    /**
     * 这个方法就是在adapter里面的oncreateviewholder里面调用，交给子类实现。
     * 只执行一次，通常我们可以在这里面设置一下view的尺寸，以及复杂嵌套布局的一些属性等等
     *
     * @param context
     */
    protected abstract void configView(Context context);

    /**
     * 这个方法我们用来重写findviewbyid 之类的方法
     */
    protected abstract void findViews();
}
