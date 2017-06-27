package com.unionpay.numberpassword.RememberNumbers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.unionpay.numberpassword.R
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by hzqb_luke on 01/05/2017.
 */

class RememberNumbers1 : AppCompatActivity() {

    internal var min: Int = 0
    internal var max: Int = 0
    internal var quantity: Int = 0
    internal var numbers = ArrayList<Int>()
    internal var index: Int = 0
    internal var flag: Boolean = true
    internal var toast:Toast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_numbers1)

        val intent = intent
        val bundle = intent.extras
        min = bundle.getInt("min")
        max = bundle.getInt("max")
        quantity = bundle.getInt("quantity")

        getNumbers()

        index = 0
        var textView = findViewById(R.id.show_number1) as TextView
        textView.text = numbers[index].toString()
        val button = findViewById(R.id.button_show_next) as Button
        val button1 = findViewById(R.id.button_show_pre) as Button
        button.setOnClickListener {
            if (index < quantity - 1) {
                index++
                textView.text = numbers[index].toString()
            } else {
                mytoast("记忆结束")
                flag = false
                button1.text = "返回第一个"
            }
        }
        button1.setOnClickListener {
            if (flag) {
                if (index > 0) {
                    index--
                    textView.text = numbers[index].toString()
                } else {
                    mytoast("已经到第一个了")
                }
            }else{
                index=0
                mytoast("已返回至第一个")
                textView.text = numbers[index].toString()
            }
        }


    }

    fun mytoast(text:String){
        if (toast != null)
            (toast as Toast).cancel()

        toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
        toast!!.setGravity(Gravity.CENTER, 0, 0)
        toast!!.show()
    }

    private fun getNumbers() {

        val random = Random()
        var s: Int
        while (numbers.size < quantity) {
            s = random.nextInt(max) % (max - min + 1) + min
            if (numbers.contains(s))
                continue
            numbers.add(s)
        }

    }

}
