package best.number.Numerology;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import best.number.Numerology.R;

import java.util.Hashtable;

public class YourNumbers extends AppCompatActivity implements View.OnClickListener {

    TextView fullName, birthDay, lifeP, exp, day, person, heart;
    int lifePath, expNum, personality, heartsDesire, dayNum, masterLife, masterExp, masterPerson, masterHeart;
    Hashtable<Integer, String> lifePathDesc;
    Button buttonLP, expButton, personButton, heartButton, dayButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_numbers);
        populateLife();

        fullName = findViewById(R.id.fullName);
        birthDay = findViewById(R.id.birthDay);
        lifeP = findViewById(R.id.lifeP);
        exp = findViewById(R.id.exp);
        day = findViewById(R.id.day);
        person = findViewById(R.id.person);
        heart = findViewById(R.id.heart);
        buttonLP = findViewById(R.id.buttonLP);
        expButton = findViewById(R.id.expButton);
        personButton = findViewById(R.id.personButton);
        heartButton = findViewById(R.id.heartButton);
        dayButton = findViewById(R.id.dayButton);

        Intent intent = getIntent();

        //Get the user's input
        String nameVal = intent.getStringExtra("usersName");
        String dobVal = intent.getStringExtra("dateOfBirth");
        lifePath = intent.getIntExtra("lifePathNumber",0);
        expNum = intent.getIntExtra("expressionNumber", 0);
        personality = intent.getIntExtra("personalityNumber", 0);
        heartsDesire = intent.getIntExtra("heartsDesireNumber", 0);
        dayNum = intent.getIntExtra("birthdayNumber", 0);
        masterLife = intent.getIntExtra("masterLifeNumber", 0);
        masterExp = intent.getIntExtra("masterExpNumber", 0);
        masterPerson = intent.getIntExtra("masterPersonNumber", 0);
        masterHeart = intent.getIntExtra("masterHeartNumber", 0);

        //Display the user's information
        fullName.setText(nameVal);
        birthDay.setText(dobVal);

        if (masterLife != 0){
            lifeP.setText("Your Life Path Number is: " + masterLife + "/" + lifePath);
        }
        else{
            lifeP.setText("Your Life Path Number is: " + lifePath);
        }

        if (masterExp != 0){
            exp.setText("Your Expression Number is: " + masterExp + "/" + expNum);
        }
        else{
            exp.setText("Your Expression Number is: " + expNum);
        }

        if (masterPerson != 0){
            person.setText("Your Personality Number is: " + masterPerson + "/" + personality);
        }
        else{
            person.setText("Your Personality Number is: " + personality);
        }

        if (masterHeart != 0){
            heart.setText("Your Heart's Desire Number is: " + masterHeart + "/" + heartsDesire);
        }
        else{
            heart.setText("Your Heart's Desire Number is: " + heartsDesire);
        }

        day.setText("Your Birthday Number is: " + dayNum);

        buttonLP.setOnClickListener(this);
        expButton.setOnClickListener(this);
        personButton.setOnClickListener(this);
        heartButton.setOnClickListener(this);
        dayButton.setOnClickListener(this);
    }

    public void populateLife(){
        lifePathDesc = new Hashtable<>();
        lifePathDesc.put(1, "The Leader");
        lifePathDesc.put(2, "The Peacemaker");
        lifePathDesc.put(3, "The Communicator");
        lifePathDesc.put(4, "The Teacher");
        lifePathDesc.put(5, "The Freedom Seeker");
        lifePathDesc.put(6, "The Care Giver");
        lifePathDesc.put(7, "The Knowledge Seeker");
        lifePathDesc.put(8, "The Ruler");
        lifePathDesc.put(9, "The Philanthropist");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLP:
                Intent inLife = new Intent(YourNumbers.this, LifePageLayout.class);
                String title = lifePathDesc.get(lifePath);
                inLife.putExtra("LifePathNum", lifePath);
                inLife.putExtra("pathTitle", title);
                startActivity(inLife);
                break;
            case R.id.expButton:
                Intent inExp = new Intent(YourNumbers.this, ExpressionPage.class);
                inExp.putExtra("expNum", expNum);
                startActivity(inExp);
                break;
            case R.id.personButton:
                Intent inPerson = new Intent(YourNumbers.this, PersonalityPage.class);
                inPerson.putExtra("personality", personality);
                startActivity(inPerson);
                break;
            case R.id.heartButton:
                Intent inHeart = new Intent(YourNumbers.this, HeartsDesirePage.class);
                inHeart.putExtra("heartsDesire", heartsDesire);
                startActivity(inHeart);
                break;
            case R.id.dayButton:
                Intent inBirthDay = new Intent(YourNumbers.this, BirthdayPage.class);
                inBirthDay.putExtra("birthDay", dayNum);
                startActivity(inBirthDay);
                break;
        }
    }

    /*@Override
    public void onClick(View v) {
        Intent inLife = new Intent(YourNumbers.this, LifePathPage.class);
        String title = "The Philanthropist";
        inLife.putExtra("LifePathNum", 9);
        inLife.putExtra("pathTitle", title);
        startActivity(inLife);
    }*/

    /*
     * Create 5 buttons and each of them will have their own method
     * might have to make a new hashtable for each
     */
    /*public void getButtonLP() {
        buttonLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inLife = new Intent(YourNumbers.this, LifePathPage.class);
                String title = lifePathDesc.get(lifePath);
                inLife.putExtra("LifePathNum", lifePath);
                inLife.putExtra("pathTitle", title);
                startActivity(inLife);
            }
        });
    }*/

    /*public void lifePathClick(View v){
        Intent inLife = new Intent(YourNumbers.this, LifePathPage.class);
        String title = lifePathDesc.get(lifePath);
        inLife.putExtra("LifePathNum", lifePath);
        inLife.putExtra("pathTitle", title);
        startActivity(inLife);
    }*/


}