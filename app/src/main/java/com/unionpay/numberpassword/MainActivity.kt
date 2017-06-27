package com.unionpay.numberpassword

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.unionpay.numberpassword.NumberPassword.NumberPassword
import com.unionpay.numberpassword.RememberNumbers.RememberNumbers_main
import com.unionpay.numberpassword.RememberPoker.RememberPoker

/**
 * Created by hzqb_luke on 30/04/2017.
 */

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val button1 = findViewById(R.id.button_nbpass) as Button
        button1.setOnClickListener { startActivity(Intent(this@MainActivity, NumberPassword::class.java)) }

        var button2 = findViewById(R.id.button_remnumber) as Button
        button2.setOnClickListener { startActivity(Intent(this@MainActivity, RememberNumbers_main::class.java)) }

        var button3 = findViewById(R.id.button_rmPoker) as Button
        button3.setOnClickListener { startActivity(Intent(this@MainActivity, RememberPoker::class.java)) }
    }
}
