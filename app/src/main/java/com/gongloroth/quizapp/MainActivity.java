package com.gongloroth.quizapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

    private int finalResult;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSubmit(View view){

        int finalResult = 0;

        RadioButton answerQuestion1 = (RadioButton) findViewById(R.id.radio_q1_c);

        RadioButton answerQuestion2 = (RadioButton) findViewById(R.id.radio_q2_b);

        RadioButton answerQuestion3 = (RadioButton) findViewById(R.id.radio_q3_a);

        RadioButton answerQuestion4 = (RadioButton) findViewById(R.id.radio_q4_c);

        RadioButton answerQuestion5 = (RadioButton) findViewById(R.id.radio_q5_b);

        CheckBox answer1Question6 = (CheckBox) findViewById(R.id.q6_checkbox_1);
        CheckBox answer2Question6 = (CheckBox) findViewById(R.id.q6_checkbox_3);

        RadioButton answerQuestion7 = (RadioButton) findViewById(R.id.radio_q7_c);

        RadioButton answerQuestion8 = (RadioButton) findViewById(R.id.radio_q8_b);

        RadioButton answerQuestion9 = (RadioButton) findViewById(R.id.radio_q9_c);

        EditText answerQuestion10 = (EditText) findViewById(R.id.edit_text_q10);
        String userInput = answerQuestion10.getText().toString().toLowerCase();
        String correctAnswer = getText(R.string.q10_text).toString().toLowerCase();

        if(answerQuestion1.isChecked()){
            finalResult++;
        }

        if(answerQuestion2.isChecked()){
            finalResult++;
        }

        if(answerQuestion3.isChecked()){
            finalResult++;
        }

        if(answerQuestion4.isChecked()){
            finalResult++;
        }

        if(answerQuestion5.isChecked()){
            finalResult++;
        }

        if(answer1Question6.isChecked() && answer2Question6.isChecked()){
            finalResult++;
        }

        if(answerQuestion7.isChecked()){
            finalResult++;
        }

        if(answerQuestion8.isChecked()){
            finalResult++;
        }

        if(answerQuestion9.isChecked()){
            finalResult++;
        }

        if(userInput.equals(correctAnswer)){
            finalResult++;
        }

        setFinalResult(finalResult);

        Context context = getApplicationContext();
        StringBuilder sb = new StringBuilder();
        Formatter fmt = new Formatter(sb);
        fmt.format("Your final score is: %d points.",getFinalResult());
        setMessage(sb.toString());
        CharSequence text = getMessage();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setVisibility(View.VISIBLE);

    }


    public void composeEmail(String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onSend(View view){

        composeEmail("Quiz Result", getMessage());

    }


    public int getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(int finalResult) {
        this.finalResult = finalResult;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
