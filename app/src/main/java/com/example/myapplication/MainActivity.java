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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnClickMe;
    private EditText userName;
    private ConstraintLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClickMe = findViewById(R.id.btnAccept);
        userName = findViewById(R.id.textUserName);
        mainContainer = findViewById(R.id.mainContainer);

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
            Snackbar.make(mainContainer,
                    R.string.errorNameLenght,
                    Snackbar.LENGTH_LONG)
                    .show();
        }else{
            //TODO GENERATE USER ALERRT
            generateUserAlert();
        }
    }

    private void generateUserAlert(){


        //GENERATE AN ALERTDIALOG
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Atención usuario")
                .setMessage("¿Estás seguro que quieres cambiar?")
                .setPositiveButton("Arre, va",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Intent newActivity = new Intent(MainActivity.this,
                               showUserName.class);

                       newActivity.putExtra("UserText",
                               userName.getText().toString());
                       startActivity(newActivity);
                       finish();


                    }
                })
                .setNegativeButton("Hijole no",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //GENERATE AN USER ALERT
                        Snackbar.make(mainContainer,
                                "Más te vale 🤭",
                                Snackbar.LENGTH_LONG)
                                .show();

                    }
                });

        builder.show();


    }

}
