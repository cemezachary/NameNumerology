package best.number.Numerology;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import best.number.Numerology.R;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity{

    EditText name, dob;
    TextView exp, lifeP, day, person, heart;
    Button click;
    Hashtable<Character, Integer> set;
    int[] fullList, personList, heartList;
    int expNum, lifePath, personality, heartsDesire, birthDay, masterNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        exp = findViewById(R.id.exp);
        click = findViewById(R.id.click);
        dob = findViewById(R.id.dob);
        lifeP = findViewById(R.id.lifeP);
        day = findViewById(R.id.day);
        person = findViewById(R.id.person);
        heart = findViewById(R.id.heart);

        populate();
    }

    //Chaldean numerology system
    public void populate() {
        set = new Hashtable<>();
        set.put('a', 1);
        set.put('i', 1);
        set.put('j', 1);
        set.put('q', 1);
        set.put('y', 1);
        set.put('b', 2);
        set.put('k', 2);
        set.put('r', 2);
        set.put('c', 3);
        set.put('g', 3);
        set.put('l', 3);
        set.put('s', 3);
        set.put('d', 4);
        set.put('m', 4);
        set.put('t', 4);
        set.put('e', 5);
        set.put('h', 5);
        set.put('n', 5);
        set.put('x', 5);
        set.put('u', 6);
        set.put('v', 6);
        set.put('w', 6);
        set.put('o', 7);
        set.put('z', 7);
        set.put('f', 8);
        set.put('p', 8);
    }

    @SuppressLint("SetTextI18n")
    public void convert(View v) {
        String input = name.getText().toString();
        if (input.length() == 0){
            Toast.makeText(this, "Must input full name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hasLetter(input)){
            Toast.makeText(this, "Name must have letter(s)", Toast.LENGTH_SHORT).show();
            return;
        }
        fullList = new int[input.length()];
        personList = new int[input.length()];
        heartList = new int[input.length()];
        String bdayInput = dob.getText().toString();
        if (bdayInput.length() == 0){
            Toast.makeText(this, "Must input date", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] bday = bdayInput.split("/");
        if (!bdayInput.contains("/") || bday.length != 3){
            Toast.makeText(this, "Must input date in mm/dd/yyyy format", Toast.LENGTH_SHORT).show();
            return;
        }
        int month = Integer.parseInt(bday[0]);
        int days, year;
        if (month < 1 || month > 12){
            Toast.makeText(this, "Not a valid month number (must be between 1 and 12)", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            days = Integer.parseInt(bday[1]);
            if (days < 1 || days > 31){
                Toast.makeText(this, "Not a valid day number (must be between 1 and 31)", Toast.LENGTH_SHORT).show();
                return;
            }
            if (days == 31 && (month == 2 || month == 4 || month == 5 || month == 6 || month == 9 || month == 11)){
                Toast.makeText(this, "Month " + month + " does not have " + days + " days", Toast.LENGTH_SHORT).show();
                return;
            }
            if (days == 30 && month == 2){
                Toast.makeText(this, "Month 2 does not have " + days + " days", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        year = Integer.parseInt(bday[2]);
        if (countDigits(year) != 4) {
            Toast.makeText(this, "year must have 4 digits", Toast.LENGTH_SHORT).show();
            return;
        }
        if (month == 2 && days == 29 && !leapYearTest(year)){
            Toast.makeText(this, "Not a leap year (no February 29th)", Toast.LENGTH_SHORT).show();
            return;
        }
        int dateSum = sumDate(bday);
        lifePath = naturalAdd(dateSum);
        fullList = breakdown(fullList, input, false, false);
        personList = breakdown(personList, input, true, false);
        heartList = breakdown(heartList, input, true, true);
        int totalSum = sumName(fullList);
        int personSum = sumName(personList);
        int heartSum = sumName(heartList);
        expNum = naturalAdd(totalSum);
        personality = naturalAdd(personSum);
        heartsDesire = naturalAdd(heartSum);

        /*lifeP.setText("Your Life Path Number is: " + lifePath);
        exp.setText("Your Expression Number is: " + expNum);
        person.setText("Your Personality Number is: " + personality);
        heart.setText("Your Heart's Desire Number is: " + heartsDesire);
        day.setText("Your Birthday Number is: " + birthDay);*/

        Intent intent = new Intent(MainActivity.this,YourNumbers.class);
        intent.putExtra("usersName", input);
        intent.putExtra("dateOfBirth", bdayInput);
        intent.putExtra("lifePathNumber", lifePath);
        intent.putExtra("expressionNumber", expNum);
        intent.putExtra("personalityNumber", personality);
        intent.putExtra("heartsDesireNumber", heartsDesire);
        intent.putExtra("birthdayNumber", birthDay);
        //intent.putExtra("masterNumber", masterNum);
        startActivity(intent);
    }

    public int sumDate(String[] dob){
        int val = 0;
        for (int i = 0; i < dob.length; i++){
            int res = Integer.parseInt(dob[i]);
            if (i == 1){
                birthDay = res;
            }
            val += res;
        }
        return val;
    }

    public int naturalAdd(int num){
        /*if (num == 11 || num == 22 || num == 33){
            masterNum = num;
        }*/
        int val;
        int n = num % 10;
        if (n == num){
            return num;
        }
        else{
            val = n + num/10;
            return naturalAdd(val);
        }
    }

    public int sumName(int[] list){
        int total = 0;
        for (int i = 0; i < list.length; i++){
            total += list[i];
        }
        return total;
    }

    //full breakdown of user's name into numerical values
    public int[] breakdown(int[] list, String s, boolean vowelCheck, boolean vowelHeart){
        for (int i = 0; i < list.length; i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) c = Character.toLowerCase(c);
            if (!Character.isLetter(c)) continue;
            if (!vowelCheck) {
                int letVal = set.get(c);
                list[i] = letVal;
            }
            else{
                if (!vowelLookup(c) && !vowelHeart){
                    int letVal = set.get(c);
                    list[i] = letVal;
                }
                if (vowelLookup(c) && vowelHeart){
                    int letVal = set.get(c);
                    list[i] = letVal;
                }
            }
        }
        return list;
    }

    public boolean vowelLookup(char letter){
        if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'){
            return true;
        }
        return false;
    }

    //validity check for year input
    public int countDigits(int num){
        if (num == 0){
            return num;
        }
        else{
            return 1 + countDigits(num/10);
        }
    }

    public boolean hasLetter(String name){
        boolean letterPresent;
        for (int i = 0; i < name.length(); i++){
            char check = name.charAt(i);
            letterPresent = Character.isLetter(check);
            if (letterPresent) return true;
        }
        return false;
    }

    public boolean leapYearTest(int year){
        if (year % 4 == 0 || (year % 100 == 0 && year % 400 == 0)){
            return true;
        }
        return false;
    }
}
