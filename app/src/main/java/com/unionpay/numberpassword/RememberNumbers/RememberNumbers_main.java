package com.unionpay.numberpassword.RememberNumbers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unionpay.numberpassword.R;

/**
 * Created by hzqb_luke on 01/05/2017.
 */

public class RememberNumbers_main extends AppCompatActivity {
    final String TOAST_INFO = "最小值大于最大值\n或数据总量超过数据范围";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remember_numbers);

        final EditText editText1, editText2, editText3;
        editText1 = (EditText) findViewById(R.id.number_min);
        editText2 = (EditText) findViewById(R.id.number_max);
        editText3 = (EditText) findViewById(R.id.quantity);

        Button button = (Button) findViewById(R.id.conform1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                int min = Integer.valueOf(editText1.getText().toString());
                int max = Integer.valueOf(editText2.getText().toString());
                int quantity = Integer.valueOf(editText3.getText().toString());

                if(min>max|(max-min+1)<quantity){
                    Toast toast = Toast.makeText(getApplicationContext(),TOAST_INFO, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else {
                    bundle.putInt("min", min);
                    bundle.putInt("max", max);
                    bundle.putInt("quantity", quantity);

                    Intent intent = new Intent(RememberNumbers_main.this, RememberNumbers1.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }


        });
    }
}