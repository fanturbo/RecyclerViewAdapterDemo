package com.turbo.recyclerviewadapterdemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by tubro on 2018/9/27.
 */
class TestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    lateinit var mContext: Context
    lateinit var mDataList: List<Any>

    constructor(context: Context, dataList: List<Test2>) {
        mContext = context
        mDataList = dataList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 0) {
            var viewHolder1 = holder as ViewHolder1;
            viewHolder1.test1.setText((mDataList.get(holder.layoutPosition) as Test1).test1)
        } else {
            var viewHolder2 = holder as ViewHolder2;
            viewHolder2.test1.setText((mDataList.get(holder.layoutPosition) as Test2).test1)
            viewHolder2.test2.setText((mDataList.get(holder.layoutPosition) as Test2).test2)
            viewHolder2.test3.setText((mDataList.get(holder.layoutPosition) as Test2).test3)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val any = mDataList.get(position)
        if (any is Test1)
            return any.itemType
        return (any as Test2).itemType
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val view = LayoutInflater.from(mContext).inflate(R.layout.item_layout_test_1, null)
            return ViewHolder1(view)
        } else {
            val view = LayoutInflater.from(mContext).inflate(R.layout.item_layout_test_2, null)
            return ViewHolder2(view)
        }
    }

    class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val test1: TextView = itemView.findViewById(R.id.test1)
    }

    class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val test1: TextView = itemView.findViewById(R.id.test1)
        val test2: TextView = itemView.findViewById(R.id.test2)
        val test3: TextView = itemView.findViewById(R.id.test3)
    }

}