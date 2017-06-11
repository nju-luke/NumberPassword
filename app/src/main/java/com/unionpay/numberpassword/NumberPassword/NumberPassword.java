package com.unionpay.numberpassword.NumberPassword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.unionpay.numberpassword.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NumberPassword extends AppCompatActivity {

    Map<String, ArrayList<String>> number2obj = new HashMap<>();
    ArrayList<String> keys;
    String key;

    Button button;
    TextView textView1;
    TextView textView2;
    String string1, string2;
    String[] pair;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_password);

        InputStream inputStream = getResources().openRawResource(R.raw.numberpassword);
        getString(inputStream);
        pair = getPair();

        string1 = pair[0];
        string2 = pair[1];
        textView1 = (TextView) findViewById(R.id.TextView1);
        textView1.setText(string1);

        textView2 = (TextView) findViewById(R.id.TextView2);

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string2 = pair[1];
                textView2.setText(string2);
            }
        });

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pair = getPair();
                string1 = pair[0];
                textView1.setText(string1);
                textView2.setText(null);
            }
        });


    }

    String[] getPair() {
        Random random = new Random();

        key = keys.get(random.nextInt(keys.size()));
        ArrayList<String> values = number2obj.get(key);
        String value = values.get(random.nextInt(values.size()));


        if (random.nextInt(2) == 0)
            return new String[]{key, value};
        else
            return new String[]{value, key};

    }


    String getString(String filepath) {
        File file = new File(filepath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return getString(fileInputStream);
    }

    String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
                addToMap(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        keys = new ArrayList<>(number2obj.keySet());
        return sb.toString();
    }

    void addToMap(String line) {
        String[] items = line.split("、");
        ArrayList<String> objs = new ArrayList<>();
        if (items.length < 2)
            return;
        for (int i = 1; i < items.length; i++) {
            objs.add(items[i]);
        }
        number2obj.put(items[0], objs);
    }

}
