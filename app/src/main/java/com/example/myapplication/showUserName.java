package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class showUserName extends AppCompatActivity {
    //DECLARE VARIABLES
    private TextView labelWelcome;
    private ConstraintLayout mainContainer;
    private ImageView meme;
    private boolean whichMeme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_name);
        //GET ELEMENTS FROM THE VIEW
        labelWelcome = findViewById(R.id.labelWelcome);
        meme = findViewById(R.id.imageMeme);
        mainContainer = findViewById(R.id.mainContainer);

        //GET THE USER NAME FROM THE ACTIVITY
        Bundle bundle = getIntent().getExtras();
        String userName = bundle.getString("UserText");


        //OBTAIN THE SYSTEM HOUR
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);

        if (hour > 19) {
            //CHANGE THE LABEL VALUE
            labelWelcome.setText(getResources()
                    .getString(R.string.nightUser) + userName);
        } else {
            //CHANGE THE LABEL VALUE
            labelWelcome.setText(getResources()
                    .getString(R.string.morningUser) + userName);
        }

        whichMeme = false;
        //CHANGE THE MEME DEPENDING OF THE HOUR
        if(hour > 11){
            whichMeme = true;
            //OBTAINR ANOTHER IMAGE FROM PROJECT RESOURCES
            meme.setImageResource(R.drawable.tardes);
        }


        meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(whichMeme){
                    whichMeme = false;
                    meme.setImageResource(R.drawable.canelita);
                }else{
                    whichMeme = true;
                    meme.setImageResource(R.drawable.tardes);

                }
            }
        });







        /*
        meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(interepolateImage){
                    interepolateImage = false;
                    meme.setImageResource(R.drawable.canelita);
                }else{
                    interepolateImage = true;
                    meme.setImageResource(R.drawable.tardes);
                }
            }
        });

        meme.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
*/

    }


/*
    private void showOptions() {
        final CharSequence[] option = {"Tomar","Cancelar"};//SE HACE UN VECTOR CON LAS OPCIONES

        final AlertDialog.Builder builder = new AlertDialog.Builder(showUserName.this);//SE CREA UN DIALOGO
        builder.setTitle("Elige una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whict) {

                if (option[whict] == "Tomar") {//SI SE HA PRECIONADO LA OPCIÓN DE TOMAR LA FOTO

                    openCamera();//SE EJECUTA UN MÉTODO PARA ABRIR LA CAMARA
                    Log.e("Optn","Camera");

                }
                if(option[whict]=="Seleccionar foto"){//SI SE PRECIONA LA OPCIÓN DE SELECCIONAR

                    Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    gallery.setType("image/*");
                    startActivityForResult(gallery, SELECT_PICTURE);

                }


            }
        });
        builder.show();//SE EJECUTA EL DIALOGO
    }

    private void openCamera() {//METODO PARA TOMAR IMAGEN Y CARGARLA AL LIST VIEW
        File file = new File(Environment.getExternalStorageDirectory(), "APP/");
        boolean isDirectoryCreated = file.exists();

        //COMPROVAR SI EXISTE EL DIRECTORIO DONDE ALMACENAREMOS LAS IMAGENES, SI ES FALSO, LO CREAMOS
        if (!isDirectoryCreated) {
            isDirectoryCreated = file.mkdirs();

        }

        if (isDirectoryCreated) {
            Log.e("External","Geeting picture");
            //EL NUEVO NOMBRE DE LA IMAGEN SERA UN TIMESTAM ASI EL NOMBRE SIMPRE SERA UNICO
            Long timestamp = System.currentTimeMillis() / 1000;
            String imageName = timestamp.toString() + ".jpg";//LO CONVERTIMOS A ESTRING + EXTENCION DE LA IMAGEN
            //mPath CONTENDRA LA RUTA COMPLETA DONDE SE ENCUENTRA LA IMAGEN
            mPath = Environment.getExternalStorageDirectory() + imageName;
            File newFile = new File(mPath);
            //INTENT PARA GUARDAR LA NUEVA IMAGEN
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imgNombre = imageName;
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, PHOTO_CODE);

        }else{
            Log.e("Internal","Geeting picture");
            //EL NUEVO NOMBRE DE LA IMAGEN SERA UN TIMESTAM ASI EL NOMBRE SIMPRE SERA UNICO
            Long timestamp = System.currentTimeMillis() / 1000;
            String imageName = timestamp.toString() + ".jpg";//LO CONVERTIMOS A ESTRING + EXTENCION DE LA IMAGEN
            //mPath CONTENDRA LA RUTA COMPLETA DONDE SE ENCUENTRA LA IMAGEN
            mPath = Environment.getDataDirectory() + imageName;
            File newFile = new File(mPath);
            //INTENT PARA GUARDAR LA NUEVA IMAGEN
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imgNombre = imageName;
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, PHOTO_CODE);

        }
    }


    private boolean mayRequestStoragePermission() {//METODO PARA COMPROBAR LOS PERMISOS NECESARIOS PARA ANDROID 6 EN ADELANTE

        if ( (ContextCompat.checkSelfPermission(showUserName.this,CAMERA) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(showUserName.this,WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        ){

            return true;
        }else{
            ActivityCompat.requestPermissions(showUserName.this,new String[]{CAMERA,WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS );
        }

        return false;
    }


*/

    @Override
    public void onBackPressed() {

        android.support.v7.app.AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(showUserName.this);

        builder.setTitle("Atención usuario")
                .setMessage("¿Relamente quieres salir?")
                .setPositiveButton("Arre, va",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                finish();

                            }
                        })
                .setNegativeButton("Hijole no",null);

        builder.show();
    }
}
