package com.unionpay.numberpassword.RememberPoker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.mcxtzhang.commonadapter.rv.CommonAdapter
import com.mcxtzhang.commonadapter.rv.ViewHolder
import com.mcxtzhang.layoutmanager.swipecard.CardConfig
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback
import com.unionpay.numberpassword.R
import java.util.*
import kotlin.collections.ArrayList

class RememberPoker : AppCompatActivity() {
    internal var mRv: RecyclerView? = null
    internal var mAdapter: CommonAdapter<PokerBean>? = null
    internal var mDatas: ArrayList<PokerBean> = ArrayList()
    internal var mDatasBackup: ArrayList<PokerBean> = ArrayList()
    internal var button: Button? = null
    var time1: Date? = null
    var time2: Date? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_card)

        myToast("请点击开始计时", Toast.LENGTH_SHORT)

        button = findViewById(R.id.start_timing) as Button
        button!!.setOnClickListener {
            time1 = Date(System.currentTimeMillis());
            myToast("开始计时", Toast.LENGTH_SHORT)
        }
        initLoadData()
        initView()
    }

    fun initLoadData() {
        mDatas = PokerBean.initDatas(loadPokers())
//        mDatas.forEach { mDatasBackup.add(it) }
        mDatasBackup.addAll(mDatas)
    }

    fun initView() {
        mRv = findViewById(R.id.rv) as RecyclerView
        mRv!!.layoutManager = OverLayCardLayoutManager()
        mAdapter = object : CommonAdapter<PokerBean>(this, mDatas, R.layout.item_swipe_card) {
            val TAG = "zxt/Adapter"

            override fun convert(viewHolder: ViewHolder, swipeCardBean: PokerBean) {

                Log.d(TAG, "convert() called with: viewHolder = [$viewHolder], swipeCardBean = [$swipeCardBean]")
                viewHolder.setText(R.id.tvPrecent, swipeCardBean.position.toString())
                //                Picasso.with(RememberPoker.this).load(swipeCardBean.getUrl()).into((ImageView) viewHolder.getView(R.id.iv));

                val imageView = viewHolder.getView<ImageView>(R.id.iv)
                imageView.setImageResource(swipeCardBean.resourceId)
            }
        }
        mRv!!.setAdapter(mAdapter)

        //初始化配置
        CardConfig.initConfig(this)
//        CardConfig.MAX_SHOW_COUNT=54
//        CardConfig.SCALE_GAP = -0.005F
        val callback = object : RenRenCallback(mRv, mAdapter, mDatas) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mDatas.removeAt(viewHolder!!.layoutPosition)
                mAdapter.notifyDataSetChanged()
                if (mDatas.size == 0) {
                    time2 = Date(System.currentTimeMillis())
                    if (time1 == null) {
                        myToast("没有设置初始时间", Toast.LENGTH_SHORT)
                    } else {
                        var timeUsed = (time2!!.time - time1!!.time) / 1000
                        myToast("此次用时：" + timeUsed.toString() + "秒", Toast.LENGTH_LONG)
                    }
                    button!!.text = "开始验证"
                    button!!.setOnClickListener {
                        mDatas.clear()
                        //todo bug
                        mDatas.addAll(mDatasBackup)
                        mRv!!.setAdapter(mAdapter)
                        button!!.text = "重新开始验证"
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(mRv)
    }


    internal var toast: Toast? = null
    fun myToast(text: String, delay: Int = Toast.LENGTH_SHORT) {
        if (toast != null)
            (toast as Toast).cancel()
        toast = Toast.makeText(applicationContext, text, delay)
        toast!!.setGravity(Gravity.BOTTOM, 0, 400)
        toast!!.show()
    }

    internal fun loadPokers(): ArrayList<Int> {
        var pokers = ArrayList<Int>()

        for (k in listOf("d", "h", "c", "s")) {
            for (index in 1..13) {
                val id = getResources().getIdentifier(k + index.toString(),
                        "drawable", "com.unionpay.numberpassword");
                pokers.add(id)
            }
        }
        pokers.add(getResources().getIdentifier("jb", "drawable", "com.unionpay.numberpassword"));
        pokers.add(getResources().getIdentifier("jr", "drawable", "com.unionpay.numberpassword"));

        Collections.shuffle(pokers)
        return pokers
    }

}


