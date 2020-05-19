package com.example.namenumerology;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HeartsDesirePage extends AppCompatActivity {

    TextView heartView, explain;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hearts_desire_page);

        heartView = findViewById(R.id.heartView);
        explain = findViewById(R.id.explain);

        Intent intent = getIntent();
        int heartNum = intent.getIntExtra("heartsDesire", 0);

        heartView.setText("Heart's Desire Number " + heartNum);

        explain.setMovementMethod(new ScrollingMovementMethod());
        String data = "";
        StringBuffer sbuffer = new StringBuffer();

        InputStream inStream;
        switch (heartNum){
            case 1:
                inStream = this.getResources().openRawResource(R.raw.heartone);
                break;
            case 2:
                inStream = this.getResources().openRawResource(R.raw.hearttwo);
                break;
            case 3:
                inStream = this.getResources().openRawResource(R.raw.heartthree);
                break;
            case 4:
                inStream = this.getResources().openRawResource(R.raw.heartfour);
                break;
            case 5:
                inStream = this.getResources().openRawResource(R.raw.heartfive);
                break;
            case 6:
                inStream = this.getResources().openRawResource(R.raw.heartsix);
                break;
            case 7:
                inStream = this.getResources().openRawResource(R.raw.heartseven);
                break;
            case 8:
                inStream = this.getResources().openRawResource(R.raw.hearteight);
                break;
            case 9:
                inStream = this.getResources().openRawResource(R.raw.heartnine);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + heartNum);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

        if (inStream != null){
            try{
                while((data = reader.readLine()) != null){
                    sbuffer.append(data + '\n');
                }
                explain.setText(sbuffer);
                inStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
