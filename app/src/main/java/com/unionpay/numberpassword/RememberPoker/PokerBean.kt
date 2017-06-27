package com.unionpay.numberpassword.RememberPoker

import java.util.*

/**
 * 介绍：
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 16/12/18.
 */

class PokerBean(var position: Int, val resourceId: Int) {



    companion object{
        fun initDatas(pokers: List<Int>): ArrayList<PokerBean> {

            val datas = ArrayList<PokerBean>()
            for (i in pokers.indices) {
                datas.add(PokerBean(i+1, pokers[i]))
            }

            return datas
        }

    }

}


