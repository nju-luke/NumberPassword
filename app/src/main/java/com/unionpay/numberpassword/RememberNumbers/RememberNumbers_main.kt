package com.unionpay.numberpassword.RememberNumbers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.unionpay.numberpassword.R

/**
 * Created by hzqb_luke on 01/05/2017.
 */

class RememberNumbers_main : AppCompatActivity() {
    internal val TOAST_INFO = "最小值大于最大值\n或数据总量超过数据范围"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remember_numbers)

        val editText1: EditText
        val editText2: EditText
        val editText3: EditText
        editText1 = findViewById(R.id.number_min) as EditText
        editText2 = findViewById(R.id.number_max) as EditText
        editText3 = findViewById(R.id.quantity) as EditText

        val button = findViewById(R.id.conform1) as Button
        button.setOnClickListener {
            val bundle = Bundle()

            val min = Integer.valueOf(editText1.text.toString())!!
            val max = Integer.valueOf(editText2.text.toString())!!
            val quantity = Integer.valueOf(editText3.text.toString())!!

            if ((min > max) or (max - min + 1 < quantity)) {
                val toast = Toast.makeText(applicationContext, TOAST_INFO, Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            } else {
                bundle.putInt("min", min)
                bundle.putInt("max", max)
                bundle.putInt("quantity", quantity)

                val intent = Intent(this@RememberNumbers_main, RememberNumbers1::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}