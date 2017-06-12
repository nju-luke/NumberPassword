package com.unionpay.numberpassword.NumberPassword

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.unionpay.numberpassword.R
import java.util.*

class NumberPassword : AppCompatActivity() {

    internal var number2obj: MutableMap<String, List<String>> = HashMap()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.number_password)

        getString()
        var pair = getPair()
//
        var textView1 = findViewById(R.id.TextView1) as TextView
        textView1.text = pair[0]
//
        var textView2 = findViewById(R.id.TextView2) as TextView

        var button = findViewById(R.id.button1) as Button
        button.setOnClickListener {
            textView2.text = pair[1]
        }
//
        button = findViewById(R.id.button2) as Button
        button.setOnClickListener {
            pair = getPair()
            textView1.text = pair[0]
            textView2.text = null
        }


    }

    fun getString() {

        var stream = resources.openRawResource(R.raw.numberpassword)
        var items = stream.reader().readLines()

        items.forEach { it ->
            var values = it.split("、")
            var key = values[0]
            var value = values.filter { values.indexOf(it) > 0 }
            number2obj[key] = value
        }
        println("Done!")
    }

    internal fun getPair(): List<String?> {
        val random = Random()

        val key = random.nextInt(number2obj.size).toString()
        val values = number2obj[key]
        val value = values?.get(random.nextInt(values.size))


        if (random.nextInt(2) == 0)
            return listOf(key, value)
        else
            return listOf(key, value)
    }

    internal fun addToMap(line: String) {
        val items = line.split("、".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val objs = ArrayList<String>()
        if (items.size < 2)
            return
        for (i in 1..items.size - 1) {
            objs.add(items[i])
        }
        number2obj.put(items[0], objs)
    }

}
