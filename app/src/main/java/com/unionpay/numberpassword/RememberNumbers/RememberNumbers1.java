package com.unionpay.numberpassword.RememberNumbers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.unionpay.numberpassword.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hzqb_luke on 01/05/2017.
 */

public class RememberNumbers1 extends AppCompatActivity {

    int min, max, quantity;
    ArrayList<Integer> numbers = new ArrayList<>();
    int index;
    TextView textView;
    final String TOAST_INFO1 = "记忆结束";
    final String TOAST_INFO2 = "已经到第一个数了";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_numbers1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        min = bundle.getInt("min");
        max = bundle.getInt("max");
        quantity = bundle.getInt("quantity");

        getNumbers();

        index = 0;
        textView = (TextView) findViewById(R.id.show_number1);
        textView.setText(numbers.get(index).toString());
        Button button = (Button) findViewById(R.id.button_show_next);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
                                      public void onClick(View v) {
                                          if (index < quantity-1) {
                                              index++;
                                              textView.setText(numbers.get(index).toString());
                                          } else {
                                              Toast toast = Toast.makeText(getApplicationContext(), TOAST_INFO1, Toast.LENGTH_LONG);
                                              toast.setGravity(Gravity.CENTER, 0, 0);
                                              toast.show();
                                          }
                                      }

                                      ;
                                  }
        );

        Button button1 = (Button) findViewById(R.id.button_show_pre);
        button1.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View v) {
                                           if (index > 0) {
                                               index--;
                                               textView.setText(numbers.get(index).toString());
                                           } else {
                                               Toast toast = Toast.makeText(getApplicationContext(), TOAST_INFO2, Toast.LENGTH_LONG);
                                               toast.setGravity(Gravity.CENTER, 0, 0);
                                               toast.show();
                                           }
                                       }
                                   }
            );



    }

    private void getNumbers() {

        Random random = new Random();
        int s;
        while (numbers.size() < quantity) {
            s = random.nextInt(max) % (max - min + 1) + min;
            if (numbers.contains(s))
                continue;
            ;
            numbers.add(s);
        }

    }

}
