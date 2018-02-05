package com.gongloroth.quizapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String USER_NAME = "name";
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Array for pictures
        final int []imageArray={R.drawable.blade_runner,R.drawable.wonder,R.drawable.shape_of};


        //Picture slideshow
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {

                ImageView imageView = (ImageView) findViewById(R.id.login_image);
                imageView.setImageResource(imageArray[i]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                i++;
                if(i>imageArray.length-1)
                {
                    i=0;
                }
                handler.postDelayed(this, 4000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 2000); //for initial delay..
    }

    //Start MainActivity with the typed name
    public void onLogin(View view){

        EditText nameText = (EditText) findViewById(R.id.name_text);

        if(!isEmpty(nameText)) {
            setName(nameText.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(USER_NAME, getName());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,getText(R.string.enter_name), Toast.LENGTH_SHORT).show();
        }
    }

    //Check if the EditText View is empty
    private boolean isEmpty(EditText eText) {
        if (eText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
