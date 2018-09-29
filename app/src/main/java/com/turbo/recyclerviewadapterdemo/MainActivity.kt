package com.turbo.recyclerviewadapterdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.turbo.recyclerviewadapterdemo.adavanced.SmartRecylerAdapter
import com.turbo.recyclerviewadapterdemo.adavanced.Test1ItemMiddleWare
import com.turbo.recyclerviewadapterdemo.adavanced.Test2ItemMiddleWare
import com.turbo.recyclerviewadapterdemo.test.Test1MiddleWare
import com.turbo.recyclerviewadapterdemo.test.Test2MiddleWare
import com.turbo.recyclerviewadapterdemo.test.TestAdvancedAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * https://www.jianshu.com/p/1975f88d5139 在看这篇文章的时候看到有说RecyclerView 到底如何适配多种布局？
 * 在用的时候确实也是在用多种ItemType然后根据不同的type设置不同的ViewHolder，那么有没有一种比较好的方式呢？
 * 先来回顾下现有的方案：
 *
 * 一般的做法是在服务器返回的Bean里加一个字段，用以区分Item类型，
 * 然后在请求服务器返回数据后根据具体数据手动设置不同的ItemType，然后在RecyclerView的adapter中onCreateViewHolder方法里
 * 根据不同的ItemType设置不同的ViewHolder，然后在onBindViewHolder再去根据不同的ItemType给不同布局的控件赋值
 *
 * 那这种情况下对于布局的复用和Bean的处理其实是不太好的，你需要去手动给每一个Bean添加属性（Bean类一般由gsonformat生产）
 * 而在Bean不断变化的情况下（比如项目开发初期，服务器的字段不确定，你就需要不断的手动添加这个itemType字段）；此外，当
 * 有其他类似页面需要相同Item布局而服务器返回数据格式不同的时候，比如说某些列表在前端展示是相同的，但是在不同的入口其实数
 * 据是完全不同的，那么这时候就需要去重新创建一个Adapter，重复利用的只有布局文件；再来个更狠的，这些页面都需要添加一个字段，
 * 布局需要更新！这时候你需要去两个Adapter中去寻找对应的代码，然后更新两个Adapter（其实是同样的代码）
 *
 * 记住计算机界有一个名言是：任何计算机界中的问题都可以通过引入一个中间层来解决
 *
 * 在Adapter和ViewHolder中间加一个中间层，将类型判断和类型设置的逻辑通过中间层来处理，Adapter仍然是最简单的RecycleViewAdapter，
 * ViewHolder还是最简单的ViewHolder，每一种Item专注于自己的逻辑，而不是让Adapter来做这个处理。
 *
 * 说下代码：TestAdapter是最开始用的方案
 * test中是加了中间层的方案,当使用多布局时，不断的创建RecyclerViewAdapterMiddleWare就好了
 *
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
        val adapter = TestAdvancedAdapter(testList as List<Any>?)
        adapter.addMiddleWare(Test1MiddleWare())
        adapter.addMiddleWare(Test2MiddleWare())
        mTestRecyclerView.adapter = adapter
    }
}
