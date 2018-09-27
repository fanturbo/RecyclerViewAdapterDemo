package com.turbo.recyclerviewadapterdemo.adavanced;

/**
 * Created by tubro on 2018/9/27.
 */

import android.view.ViewGroup;

/**
 * 中间件，这个类的主要作用就是桥接adapter和viewholder使用
 */
public abstract class SmartRecylerItemMiddleware<T extends SmartRecylerItem> {

    /**
     * 这个值就是在多布局的情况下，返回的itemType的值，我们在中间件里储存一下这个值
     * 方便使用
     */
    private int itemType;

    /**
     * 匹配数据
     *
     * @param data 待匹配的数据，通常是使用instanceof关键字匹配类型
     * @return 如果返回true，Adapter将会使用此ItemFactory来处理当前这条数据
     */
    public abstract boolean isTarget(Object data);

    /**
     * 创建Item
     */
    public abstract T createSmartItem(ViewGroup parent);

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    protected T dispatchCreatesSmartItem(ViewGroup parent) {
        T item = createSmartItem(parent);
        item.findViews();
        item.configView(parent.getContext());
        return item;
    }
}
