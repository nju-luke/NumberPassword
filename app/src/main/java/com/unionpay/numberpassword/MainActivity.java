package com.unionpay.numberpassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.unionpay.numberpassword.NumberPassword.NumberPassword;
import com.unionpay.numberpassword.RememberNumbers.RememberNumbers_main;

import java.util.List;

/**
 * Created by hzqb_luke on 30/04/2017.
 */

public class MainActivity extends AppCompatActivity {
    Button button1,button2;

    private RecyclerView mRecyclerView;
    private List<Integer> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        button1 = (Button) findViewById(R.id.button_nbpass);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NumberPassword.class));
            }
        });

        button2 = (Button) findViewById(R.id.button_remnumber);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RememberNumbers_main.class));
            }
        });
    }
}
