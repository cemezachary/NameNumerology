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

public class ExpressionPage extends AppCompatActivity {

    TextView expView, explain;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expression_page);

        expView = findViewById(R.id.expView);
        explain = findViewById(R.id.explain);

        Intent intent = getIntent();
        int expNum = intent.getIntExtra("expNum", 0);

        expView.setText("Expression Number " + expNum);

        explain.setMovementMethod(new ScrollingMovementMethod());
        String data = "";
        StringBuffer sbuffer = new StringBuffer();

        InputStream inStream;
        switch (expNum){
            case 1:
                inStream = this.getResources().openRawResource(R.raw.expressionone);
                break;
            case 2:
                inStream = this.getResources().openRawResource(R.raw.expressiontwo);
                break;
            case 3:
                inStream = this.getResources().openRawResource(R.raw.expressionthree);
                break;
            case 4:
                inStream = this.getResources().openRawResource(R.raw.expressionfour);
                break;
            case 5:
                inStream = this.getResources().openRawResource(R.raw.expressionfive);
                break;
            case 6:
                inStream = this.getResources().openRawResource(R.raw.expressionsix);
                break;
            case 7:
                inStream = this.getResources().openRawResource(R.raw.expressionseven);
                break;
            case 8:
                inStream = this.getResources().openRawResource(R.raw.expressioneight);
                break;
            case 9:
                inStream = this.getResources().openRawResource(R.raw.expressionnine);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + expNum);
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
