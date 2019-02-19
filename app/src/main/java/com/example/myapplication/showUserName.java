package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class showUserName extends AppCompatActivity {
    //DECLARE VAIABLES
    private TextView labelWelcome;
    private ImageView meme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_name);
        //GET ELEMENTS FROM THE VIEW
        labelWelcome = findViewById(R.id.labelWelcome);
        meme = findViewById(R.id.imageMeme);

        //GET THE USER NAME FROM THE ACTIVITY
        Bundle bundle = getIntent().getExtras();
        String userName = bundle.getString("userName");

        //OBTAIN THE SYSTEM HOUR
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);

        if( hour > 20){
            //CHANGE THE LABEL VALUE
            labelWelcome.setText(getResources().getString(R.string.nightUser) + userName);
        }else{
            //CHANGE THE LABEL VALUE
            labelWelcome.setText(getResources().getString(R.string.morningUser) + userName);
        }

        //CHANGE THE MEME DEPENDING OF THE HOUR
        if(hour > 11){
            //OBTAINR ANOTHER IMAGE FROM PROJECT RESOURCES
            meme.setImageResource(R.drawable.tardes);
        }
    }
}
