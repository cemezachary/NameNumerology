package best.number.Numerology;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import best.number.Numerology.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BirthdayPage extends AppCompatActivity {

    TextView dayView, explain;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_page);

        dayView = findViewById(R.id.dayView);
        explain = findViewById(R.id.explain);

        Intent intent = getIntent();
        int dayNum = intent.getIntExtra("birthDay", 0);

        dayView.setText("Birthday Number " + dayNum);

        explain.setMovementMethod(new ScrollingMovementMethod());
        String data = "";
        StringBuffer sbuffer = new StringBuffer();

        InputStream inStream;
        switch (dayNum){
            case 1:
                inStream = this.getResources().openRawResource(R.raw.birthday1);
                break;
            case 2:
                inStream = this.getResources().openRawResource(R.raw.birthday2);
                break;
            case 3:
                inStream = this.getResources().openRawResource(R.raw.birthday3);
                break;
            case 4:
                inStream = this.getResources().openRawResource(R.raw.birthday4);
                break;
            case 5:
                inStream = this.getResources().openRawResource(R.raw.birthday5);
                break;
            case 6:
                inStream = this.getResources().openRawResource(R.raw.birthday6);
                break;
            case 7:
                inStream = this.getResources().openRawResource(R.raw.birthday7);
                break;
            case 8:
                inStream = this.getResources().openRawResource(R.raw.birthday8);
                break;
            case 9:
                inStream = this.getResources().openRawResource(R.raw.birthday9);
                break;
            case 10:
                inStream = this.getResources().openRawResource(R.raw.birthday10);
                break;
            case 11:
                inStream = this.getResources().openRawResource(R.raw.birthday11);
                break;
            case 12:
                inStream = this.getResources().openRawResource(R.raw.birthday12);
                break;
            case 13:
                inStream = this.getResources().openRawResource(R.raw.birthday13);
                break;
            case 14:
                inStream = this.getResources().openRawResource(R.raw.birthday14);
                break;
            case 15:
                inStream = this.getResources().openRawResource(R.raw.birthday15);
                break;
            case 16:
                inStream = this.getResources().openRawResource(R.raw.birthday16);
                break;
            case 17:
                inStream = this.getResources().openRawResource(R.raw.birthday17);
                break;
            case 18:
                inStream = this.getResources().openRawResource(R.raw.birthday18);
                break;
            case 19:
                inStream = this.getResources().openRawResource(R.raw.birthday19);
                break;
            case 20:
                inStream = this.getResources().openRawResource(R.raw.birthday20);
                break;
            case 21:
                inStream = this.getResources().openRawResource(R.raw.birthday21);
                break;
            case 22:
                inStream = this.getResources().openRawResource(R.raw.birthday22);
                break;
            case 23:
                inStream = this.getResources().openRawResource(R.raw.birthday23);
                break;
            case 24:
                inStream = this.getResources().openRawResource(R.raw.birthday24);
                break;
            case 25:
                inStream = this.getResources().openRawResource(R.raw.birthday25);
                break;
            case 26:
                inStream = this.getResources().openRawResource(R.raw.birthday26);
                break;
            case 27:
                inStream = this.getResources().openRawResource(R.raw.birthday27);
                break;
            case 28:
                inStream = this.getResources().openRawResource(R.raw.birthday28);
                break;
            case 29:
                inStream = this.getResources().openRawResource(R.raw.birthday29);
                break;
            case 30:
                inStream = this.getResources().openRawResource(R.raw.birthday30);
                break;
            case 31:
                inStream = this.getResources().openRawResource(R.raw.birthday31);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dayNum);
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
