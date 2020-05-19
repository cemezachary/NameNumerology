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

public class PersonalityPage extends AppCompatActivity {

    TextView personView, explain;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_page);

        personView = findViewById(R.id.personView);
        explain = findViewById(R.id.explain);

        Intent intent = getIntent();
        int personNum = intent.getIntExtra("personality", 0);

        personView.setText("Personality Number " + personNum);

        explain.setMovementMethod(new ScrollingMovementMethod());
        String data = "";
        StringBuffer sbuffer = new StringBuffer();

        InputStream inStream;
        switch (personNum){
            case 1:
                inStream = this.getResources().openRawResource(R.raw.personalityone);
                break;
            case 2:
                inStream = this.getResources().openRawResource(R.raw.personalitytwo);
                break;
            case 3:
                inStream = this.getResources().openRawResource(R.raw.personalitythree);
                break;
            case 4:
                inStream = this.getResources().openRawResource(R.raw.personalityfour);
                break;
            case 5:
                inStream = this.getResources().openRawResource(R.raw.personalityfive);
                break;
            case 6:
                inStream = this.getResources().openRawResource(R.raw.personalitysix);
                break;
            case 7:
                inStream = this.getResources().openRawResource(R.raw.personalityseven);
                break;
            case 8:
                inStream = this.getResources().openRawResource(R.raw.personalityeight);
                break;
            case 9:
                inStream = this.getResources().openRawResource(R.raw.personalitynine);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + personNum);
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
