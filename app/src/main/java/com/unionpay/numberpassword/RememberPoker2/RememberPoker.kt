package com.unionpay.numberpassword.RememberPoker2

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.unionpay.numberpassword.R
import java.util.*
import java.util.Collections.shuffle
import kotlin.collections.ArrayList


class RememberPoker : AppCompatActivity(), SwipeAdapterView.onFlingListener, SwipeAdapterView.OnItemClickListener {

    var time1: Date? = null
    var time2: Date? = null

    var flag: Boolean = false
    var flag1: Boolean = true
    var listBackup = ArrayList<Int>()

    private var cardWidth: Int = 0
    private var cardHeight: Int = 0

    private var swipeView: SwipeAdapterView? = null
    private var adapter: InnerAdapter? = null
    var button:Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.poker_main)
        myToast("请点击开始计时", Toast.LENGTH_SHORT)
        initView()
        loadData()

        button = findViewById(R.id.start_timing) as Button
        button!!.setOnClickListener {
            time1 = Date(System.currentTimeMillis());
            myToast("开始计时", Toast.LENGTH_SHORT)
        }

    }

    internal var toast:Toast? = null
    fun myToast(text:String, delay:Int = Toast.LENGTH_SHORT){
        if (toast != null)
            (toast as Toast).cancel()
        toast = Toast.makeText(applicationContext, text, delay)
        toast!!.setGravity(Gravity.BOTTOM, 0, 400)
        toast!!.show()
    }

    private fun initView() {
        val dm = resources.displayMetrics
        val density = dm.density
        cardWidth = (dm.widthPixels - 2f * 18f * density).toInt()
        cardHeight = (dm.heightPixels - 338 * density).toInt()


        swipeView = findViewById(R.id.swipe_view) as SwipeAdapterView
        //        swipeView.setIsNeedSwipe(false);
        swipeView!!.setFlingListener(this)
        swipeView!!.setOnItemClickListener(this)

        adapter = InnerAdapter()
        swipeView!!.adapter = adapter
    }


    override fun onItemClicked(v: View, dataObject: Any) {
        Log.i("tag", "click 大图")
    }

    override fun removeFirstObjectInAdapter() {
        adapter!!.remove(0)
    }

    override fun onLeftCardExit(dataObject: Any) {
        Log.i("tag", "swipe left")
    }

    override fun onRightCardExit(dataObject: Any) {
        Log.i("tag", "swipe right")
    }

    override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
        if (itemsInAdapter > 0 && flag1) {
            myToast(String.format("还有%s张", itemsInAdapter), Toast.LENGTH_SHORT)

            flag = true
            flag1 = false
        }
        if (flag && itemsInAdapter == 0) {
            time2 = Date(System.currentTimeMillis())
            if (time1 == null) {
                myToast( "没有设置初始时间", Toast.LENGTH_SHORT)
            } else {
                var timeUsed = (time2!!.time - time1!!.time) / 1000
                myToast( "此次用时：" + timeUsed.toString() + "秒", Toast.LENGTH_LONG)
            }
            button!!.text = "开始验证"
            button!!.setOnClickListener {
                swipeView!!.removeAllViewsInLayout()
                initView()
                adapter!!.addAll(listBackup)
                flag = false
                button!!.text = "重新开始验证"
            }

        }
    }

    override fun onScroll(progress: Float, scrollXProgress: Float) {}

    private fun loadData() {
        object : AsyncTask<Void, Void, List<Int>>() {
            override fun doInBackground(vararg params: Void): List<Int> {
                val list = ArrayList<Int>()

                for (k in listOf("d", "h", "c", "s")) {
                    for (index in 1..13) {
                        val id = getResources().getIdentifier(k + index.toString(),
                                "drawable", "com.unionpay.numberpassword");
                        list.add(id)
                    }
                }
                list.add(getResources().getIdentifier("jb", "drawable", "com.unionpay.numberpassword"));
                list.add(getResources().getIdentifier("jr", "drawable", "com.unionpay.numberpassword"));

                shuffle(list)
                return list
            }

            override fun onPostExecute(list: List<Int>) {
                super.onPostExecute(list)
                list.forEach { it -> listBackup.add(it) }
                adapter!!.addAll(list)
            }
        }.execute()
    }


    private inner class InnerAdapter : BaseAdapter() {

        internal var objs: ArrayList<Int>? = null

        init {
            objs = ArrayList<Int>()
        }

        fun addAll(collection: Collection<Int>) {
            if (isEmpty) {
                objs!!.addAll(collection)
                notifyDataSetChanged()
            } else {
                objs!!.addAll(collection)
            }
        }

        fun clear() {
            objs!!.clear()
            notifyDataSetChanged()
        }

        override fun isEmpty(): Boolean {
            return objs!!.isEmpty()
        }

        fun remove(index: Int) {
            if (index > -1 && index < objs!!.size) {
                objs!!.removeAt(index)
                notifyDataSetChanged()
            }
        }


        override fun getCount(): Int {
            return objs!!.size
        }

        override fun getItem(position: Int): Int? {
            if (objs == null || objs!!.size == 0) return null
            return objs!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val holder: ViewHolder
            val pokerId = getItem(position)
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.context).inflate(R.layout.card_new_item, parent, false)
                holder = ViewHolder()
                convertView!!.tag = holder
                convertView.layoutParams.width = cardWidth
                holder.portraitView = convertView.findViewById(R.id.portrait) as ImageView
                holder.portraitView!!.layoutParams.height = cardHeight
            } else {
                holder = convertView.tag as ViewHolder
            }

            Glide.with(parent.context).load(pokerId)
                    .placeholder(R.drawable.default_card)
                    .into(holder.portraitView!!)


            return convertView
        }

    }

    private class ViewHolder {
        internal var portraitView: ImageView? = null
    }


}
