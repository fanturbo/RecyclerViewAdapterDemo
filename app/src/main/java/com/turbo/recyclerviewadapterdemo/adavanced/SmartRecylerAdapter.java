package com.turbo.recyclerviewadapterdemo.adavanced;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tubro on 2018/4/2.
 */

public class SmartRecylerAdapter extends RecyclerView.Adapter {

    public SmartRecylerAdapter(List<Object> mDataList) {
        this.mDataList = mDataList;
    }

    //存放所有数据的list
    private List<Object> mDataList;

    /**
     * 对于多种布局来说，itemType的值都是从0开始，布局越多 这个值越大 默认为0
     */
    private int itemTypeIndex = 0;

    /**
     * 这个值就是用来保存中间件的，你要适配那种布局 就增加一个中间件到这个list中。
     */
    private ArrayList<SmartRecylerItemMiddleware> smartRecylerItemMiddlewareArrayList;

    /**
     * 实际上是hashmap，key 是 itemType的值，value 就是我们的中间件。
     * 用SparseArray是为了性能考虑，key 为int值的话 SparseArray 比hashmap效率要高
     */
    private SparseArray<Object> smartMiddleHashMap;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果不使用中间件的话，这里熟悉的代码应该是根据不同的viewtype的值
        //来返回不同的layout布局
        //这里我们就直接根据viewtype的值 来取出我们对应的中间件
        Object item = smartMiddleHashMap.get(viewType);

        // Item
        if (item instanceof SmartRecylerItemMiddleware) {
            SmartRecylerItemMiddleware itemFactory = (SmartRecylerItemMiddleware) item;
            return itemFactory.dispatchCreatesSmartItem(parent);
        }

        throw new IllegalStateException("这边异常随便你自己怎么写吧。");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SmartRecylerItem) {
            ((SmartRecylerItem) holder).bindData(position, getItem(position));
        }
    }

    public void addMiddleWare(SmartRecylerItemMiddleware smartRecylerItemMiddleware) {

        smartRecylerItemMiddleware.setItemType(itemTypeIndex++);

        if (smartMiddleHashMap == null) {
            smartMiddleHashMap = new SparseArray<Object>();
        }
        smartMiddleHashMap.put(smartRecylerItemMiddleware.getItemType(), smartRecylerItemMiddleware);

        if (smartRecylerItemMiddlewareArrayList == null) {
            smartRecylerItemMiddlewareArrayList = new ArrayList<SmartRecylerItemMiddleware>(5);
        }
        smartRecylerItemMiddlewareArrayList.add(smartRecylerItemMiddleware);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {

        //遍历一下中间件 看看是否有对应的中间件可以使用，如果没有就抛出异常
        SmartRecylerItemMiddleware smartRecylerItemMiddleware;
        for (int w = 0, size = smartRecylerItemMiddlewareArrayList.size(); w < size; w++) {
            smartRecylerItemMiddleware = smartRecylerItemMiddlewareArrayList.get(w);
            if (smartRecylerItemMiddleware.isTarget(mDataList.get(position))) {
                return smartRecylerItemMiddleware.getItemType();
            }
        }
        //你当然可以在异常信息里写出更详细的信息。。。。
        throw new IllegalStateException("Didn't find suitable Middleware.");
    }


    /**
     * 返回list中的bean
     *
     * @param position
     * @return
     */
    public Object getItem(int position) {
        // 数据
        return mDataList.get(position);
    }
}
