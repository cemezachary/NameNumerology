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

import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity{

    EditText name, dob;
    TextView exp, lifeP, day, person, heart;
    Button click;
    Hashtable<Character, Integer> chaldeanValues;
    int[] fullList, personList, heartList;
    int expNum, lifePath, personality, heartsDesire, birthDay = 0;
    int masterLife, masterExp, masterPerson, masterHeart = 0;
    ArrayList<Integer> calculateNums = new ArrayList<>();
    ArrayList<ArrayList<Integer>> totalNums = new ArrayList<>();
    boolean nameCheck, dateCheck = false;

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
        chaldeanValues = new Hashtable<>();
        chaldeanValues.put('a', 1);
        chaldeanValues.put('i', 1);
        chaldeanValues.put('j', 1);
        chaldeanValues.put('q', 1);
        chaldeanValues.put('y', 1);
        chaldeanValues.put('b', 2);
        chaldeanValues.put('k', 2);
        chaldeanValues.put('r', 2);
        chaldeanValues.put('c', 3);
        chaldeanValues.put('g', 3);
        chaldeanValues.put('l', 3);
        chaldeanValues.put('s', 3);
        chaldeanValues.put('d', 4);
        chaldeanValues.put('m', 4);
        chaldeanValues.put('t', 4);
        chaldeanValues.put('e', 5);
        chaldeanValues.put('h', 5);
        chaldeanValues.put('n', 5);
        chaldeanValues.put('x', 5);
        chaldeanValues.put('u', 6);
        chaldeanValues.put('v', 6);
        chaldeanValues.put('w', 6);
        chaldeanValues.put('o', 7);
        chaldeanValues.put('z', 7);
        chaldeanValues.put('f', 8);
        chaldeanValues.put('p', 8);
    }

    @SuppressLint("SetTextI18n")
    public void convert(View v) {
        String input = name.getText().toString();
        validateName(input);
        if (!nameCheck) return;

        fullList = new int[input.length()];
        personList = new int[input.length()];
        heartList = new int[input.length()];

        String bdayInput = dob.getText().toString();
        valiDate(bdayInput);
        if (!dateCheck) return;

        String[] bday = bdayInput.split("/");
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
        masterNumCheck(totalNums);

        Intent intent = new Intent(MainActivity.this,YourNumbers.class);
        intent.putExtra("usersName", input);
        intent.putExtra("dateOfBirth", bdayInput);
        intent.putExtra("lifePathNumber", lifePath);
        intent.putExtra("expressionNumber", expNum);
        intent.putExtra("personalityNumber", personality);
        intent.putExtra("heartsDesireNumber", heartsDesire);
        intent.putExtra("birthdayNumber", birthDay);
        intent.putExtra("masterLifeNumber", masterLife);
        intent.putExtra("masterExpNumber", masterExp);
        intent.putExtra("masterPersonNumber", masterPerson);
        intent.putExtra("masterHeartNumber", masterHeart);
        System.out.println(totalNums);
        startActivity(intent);
    }

    //region Validations
    private void validateName(String input){
        if (input.length() == 0){
            Toast.makeText(this, "Must input full name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hasLetter(input)){
            Toast.makeText(this, "Name must have letter(s)", Toast.LENGTH_SHORT).show();
            return;
        }
        nameCheck = true;
    }

    private void valiDate(String date){
        if (date.length() == 0){
            Toast.makeText(this, "Must input date", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] bday = date.split("/");
        if (!date.contains("/") || bday.length != 3){
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
        dateCheck = true;
    }
    //endregion

    private void populateTotalNums(int num){
        ArrayList<Integer> test;
        calculateNums.add(num);

        if (countDigits(num) == 1){
            calculateNums.add(num);
            test = (ArrayList<Integer>) calculateNums.clone();
            totalNums.add(test);
            calculateNums.clear();
        }
    }


    private void masterNumCheck(ArrayList<ArrayList<Integer>> list){
        for (int i = 0; i < list.size(); i++){
            for (int j = 0; j < list.get(i).size(); j++){
                int value = list.get(i).get(j);
                if (countDigits(value) == 2){
                    String number = String.valueOf(value);
                    char[] digits = number.toCharArray();
                    if (digits[0] == digits[1]){
                        if (i == 0) masterLife = value;
                        if (i == 1) masterExp = value;
                        if (i == 2) masterPerson = value;
                        if (i == 3) masterHeart = value;
                    }
                }
            }
        }
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
        populateTotalNums(num);
        int val;
        //System.out.println(masterNum);
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
                int letVal = chaldeanValues.get(c);
                list[i] = letVal;
            }
            else{
                if (!vowelLookup(c) && !vowelHeart){
                    int letVal = chaldeanValues.get(c);
                    list[i] = letVal;
                }
                if (vowelLookup(c) && vowelHeart){
                    int letVal = chaldeanValues.get(c);
                    list[i] = letVal;
                }
            }
        }
        return list;
    }

    public boolean vowelLookup(char letter){
        return letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u';
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
        return year % 4 == 0 || (year % 100 == 0 && year % 400 == 0);
    }
}
