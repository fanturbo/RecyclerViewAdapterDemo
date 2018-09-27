package com.turbo.recyclerviewadapterdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.turbo.recyclerviewadapterdemo.adavanced.SmartRecylerAdapter
import com.turbo.recyclerviewadapterdemo.adavanced.Test1ItemMiddleWare
import com.turbo.recyclerviewadapterdemo.adavanced.Test2ItemMiddleWare
import kotlinx.android.synthetic.main.activity_main.*

/**
 * https://www.jianshu.com/p/1975f88d5139 在看这篇文章的时候看到有说RecyclerView 到底如何适配多种布局？
 * 在用的时候确实也是在用多种ItemType然后根据不同的type设置不同的ViewHolder
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTestRecyclerView.layoutManager = LinearLayoutManager(this)
        var testList = ArrayList<Any>()
        testList.add(Test2("张良", "鲁班", "虞姬", 1))
        testList.add(Test2("孙悟空", "阿珂", "芈月", 1))
        testList.add(Test1("迪丽热巴", 0))
        testList.add(Test2("娜可露露", "李元芳", "百里守约", 1))
        testList.add(Test1("邓超", 0))
        testList.add(Test2("嬴政", "后羿", "狄仁杰", 1))
        testList.add(Test1("黄渤", 0))
        val adapter = SmartRecylerAdapter(testList as List<Any>?)
        adapter.addMiddleWare(Test1ItemMiddleWare())
        adapter.addMiddleWare(Test2ItemMiddleWare())
        mTestRecyclerView.adapter = adapter
    }
}
