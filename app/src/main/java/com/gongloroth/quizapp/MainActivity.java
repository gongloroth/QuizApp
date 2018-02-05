package com.gongloroth.quizapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

    private int finalResult = 0;
    private String message;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUserName(getIntent().getExtras().getString("name"));
    }

    //Create final result message based on the calculation and
    //make Submit button invisible and Send Result button visible
    public void onSubmit(View view){

        calculateResult();

        Context context = getApplicationContext();
        StringBuilder sb = new StringBuilder();
        Formatter fmt = new Formatter(sb);
        fmt.format("Well done, %s!\nYour final score is: %d points.",getUserName(),getFinalResult());
        setMessage(sb.toString());
        CharSequence text = getMessage();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Button submitButton = (Button)view;
        submitButton.setVisibility(View.GONE);
        submitButton.setClickable(false);

        Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setVisibility(View.VISIBLE);
        sendButton.setFocusable(true);
        sendButton.setFocusableInTouchMode(true);
        sendButton.requestFocus();

    }

    //Calculate the final result and make the Views red or green based on the answer
    //Green = right answer
    //Red = wrong answer
    public void calculateResult(){

        checkRadioView(R.id.radio_q1_c,R.id.card_radiogroup1);
        checkRadioView(R.id.radio_q2_b,R.id.card_radiogroup2);
        checkRadioView(R.id.radio_q3_a,R.id.card_radiogroup3);
        checkRadioView(R.id.radio_q4_c,R.id.card_radiogroup4);
        checkRadioView(R.id.radio_q5_b,R.id.card_radiogroup5);
        checkRadioView(R.id.radio_q7_c,R.id.card_radiogroup7);
        checkRadioView(R.id.radio_q8_b,R.id.card_radiogroup8);
        checkRadioView(R.id.radio_q9_c,R.id.card_radiogroup9);
        checkCheckView(R.id.q6_checkbox_1,R.id.q6_checkbox_3,R.id.card_radiogroup6);
        checkEditView(R.id.edit_text_q10,R.id.card_edit_text10);
    }

    //Check if Radiogroup's answer is correct and change background color based on answer
    public void checkRadioView(int radioButtonID, int answerCardID){
        RadioButton button = (RadioButton) findViewById(radioButtonID);
        if (button.isChecked()){
            finalResult++;
            CardView answer = (CardView) findViewById(answerCardID);
            answer.setCardBackgroundColor(getResources().getColor(R.color.colorRight));
        }
        else{
            CardView answer = (CardView) findViewById(answerCardID);
            answer.setCardBackgroundColor(getResources().getColor(R.color.colorWrong));
        }
    }

    //Check if Checkbox's answer is correct and change background color based on answer
    public void checkCheckView(int checkbuttonID1,int checkButtonID2, int answerCardID){

        CheckBox answer1Question6 = (CheckBox) findViewById(checkbuttonID1);
        CheckBox answer2Question6 = (CheckBox) findViewById(checkButtonID2);

        if (answer1Question6.isChecked() && answer2Question6.isChecked()){
            finalResult++;
            CardView answer = (CardView) findViewById(answerCardID);
            answer.setCardBackgroundColor(getResources().getColor(R.color.colorRight));
        }
        else{
            CardView answer = (CardView) findViewById(answerCardID);
            answer.setCardBackgroundColor(getResources().getColor(R.color.colorWrong));
        }
    }

    //Check if EditText's answer is correct and change background color based on answer
    public void checkEditView(int editTextID, int answerCardID){

        EditText editText = (EditText) findViewById(editTextID);
        String userInput = editText.getText().toString().toLowerCase();
        String correctAnswer = getText(R.string.q10_text).toString().toLowerCase();

        if (userInput.equals(correctAnswer)){
            finalResult++;
            CardView answer = (CardView) findViewById(answerCardID);
            answer.setCardBackgroundColor(getResources().getColor(R.color.colorRight));
        }
        else{
            CardView answer = (CardView) findViewById(answerCardID);
            answer.setCardBackgroundColor(getResources().getColor(R.color.colorWrong));
        }
    }


    //Start e-mail app with the composed message
    public void composeEmail(String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //Send button method
    public void onSend(View view){

        composeEmail("Quiz Result", getMessage());

    }


    public int getFinalResult() {
        return finalResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
