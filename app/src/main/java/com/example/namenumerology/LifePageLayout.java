package com.example.namenumerology;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LifePageLayout extends AppCompatActivity {

    TextView pathNum, title, explain;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_page_layout);

        pathNum = findViewById(R.id.pathNum);
        title = findViewById(R.id.title);
        explain = findViewById(R.id.explain);

        Intent intent = getIntent();
        int lpNum = intent.getIntExtra("LifePathNum", 0);
        String pathTitle = intent.getStringExtra("pathTitle");

        pathNum.setText("Life Path " + lpNum);
        title.setText(pathTitle);

        //code below will help me access the data in the number_explanations file
        explain.setMovementMethod(new ScrollingMovementMethod());
        String data = "";
        StringBuffer sbuffer = new StringBuffer();

        InputStream inStream;
        switch (lpNum){
            case 1:
                inStream = this.getResources().openRawResource(R.raw.lifepathone);
                break;
            case 2:
                inStream = this.getResources().openRawResource(R.raw.lifepathtwo);
                break;
            case 3:
                inStream = this.getResources().openRawResource(R.raw.lifepaththree);
                break;
            case 4:
                inStream = this.getResources().openRawResource(R.raw.lifepathfour);
                break;
            case 5:
                inStream = this.getResources().openRawResource(R.raw.lifepathfive);
                break;
            case 6:
                inStream = this.getResources().openRawResource(R.raw.lifepathsix);
                break;
            case 7:
                inStream = this.getResources().openRawResource(R.raw.lifepathseven);
                break;
            case 8:
                inStream = this.getResources().openRawResource(R.raw.lifepatheight);
                break;
            case 9:
                inStream = this.getResources().openRawResource(R.raw.lifepathnine);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + lpNum);
        }
        //InputStream inStream = this.getResources().openRawResource(R.raw.yo);

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
