package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnClickMe;
    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClickMe = findViewById(R.id.btnAccept);
        userName = findViewById(R.id.textUserName);

        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUserName();
            }
        });
    }

    private void verifyUserName(){
        //.getText RETURN AN EDITABLE OBJECT. THATS WHY WE NEED TO CAST
        String name = userName.getText().toString();
        //VERIFY THE INPUT TEXT
        if(name.length() <1){
            //GENERATE AN USER ALERT
            Toast.makeText(MainActivity.this, R.string.errorNameLenght, Toast.LENGTH_LONG).show();
        }else{
            generateUserAlert();
        }
    }

    private void generateUserAlert(){
        //GENERATE AN ALERTDIALOG
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.alertTitle)
                .setMessage(R.string.alertMessage)
                .setPositiveButton(R.string.alertPositive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //OPEN ANOTHER ACTIVITY
                        Intent newActivity = new Intent(MainActivity.this, showUserName.class);
                        newActivity.putExtra("userName",userName.getText().toString());
                        startActivity(newActivity);
                        finish();
                    }
                })
                .setNegativeButton(R.string.alertNegative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //GENERATE AN USER ALERT
                        Toast.makeText(MainActivity.this, R.string.messageNegative, Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

}
