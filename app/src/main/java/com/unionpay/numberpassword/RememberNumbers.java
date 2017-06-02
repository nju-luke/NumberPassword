package com.unionpay.numberpassword;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hzqb_luke on 01/05/2017.
 */

public class RememberNumbers extends AppCompatActivity {

    int min,max,quantity;
    ArrayList<Integer> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_numbers);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        min = bundle.getInt("min");
        max = bundle.getInt("max");
        quantity = bundle.getInt("quantity");

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.show_number);
        getNumbers();

        for(int i=0;i<quantity;i++){
            TextView textView = getTextView();
            textView.setText(numbers.get(i).toString());
//            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.addView(textView);
        }
    }

     void getNumbers() {

        Random random = new Random();
        int s;
        while (numbers.size()<quantity) {
            s=random.nextInt(max) % (max - min + 1) + min;
            if(numbers.contains(s))
                continue;;
            numbers.add(s);
        }

    }

     TextView getTextView() {
        TextView textView = new TextView(this);
        textView.setTextSize(textView.getTextSize()+3);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        return textView;
    }


}
