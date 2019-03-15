package com.example.myapplication;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class CamaraActivity extends AppCompatActivity {

    ImageView imageView;
    ImageButton imageButton, infoButton;
    Button test, volver1;
    String pathToFile, cameraId;
    CameraDevice cameraDevice;
    Size size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        if (Build.VERSION.SDK_INT>23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }

        imageView = findViewById(R.id.imagen);
        imageButton = findViewById(R.id.cargar);
        infoButton = findViewById(R.id.info);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakerAction();
            }
        });

        test = findViewById(R.id.btnTest);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.buildDrawingCache();
                Bitmap bitmap = imageView.getDrawingCache();

                if (imageView.getDrawable() == null){
                    Toast.makeText(CamaraActivity.this, "Debe tomar la imagen antes de continuar", Toast.LENGTH_SHORT).show();
                } else {
                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, bStream);
                    byte[] byteArray = bStream.toByteArray();

                    Intent anotherIntent = new Intent(getApplicationContext(), MuestraMarckisActivity.class);
                    anotherIntent.putExtra("image", byteArray);
                    startActivity(anotherIntent);
                }
            }
        });

        volver1 = findViewById(R.id.btnVolver);

        volver1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoDialogo();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private void infoDialogo(){

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(CamaraActivity.this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Pulse el ícono de cámara para tomar la foto de la muestra")
                .setTitle(android.R.string.dialog_alert_title);

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();

    }

    private void dispatchPictureTakerAction() {
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePic.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            photoFile = createPhotoFile();
//            String pathToFile = photoFile.getAbsolutePath();
            if (photoFile!=null){
                pathToFile = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(CamaraActivity.this, "com.example.android.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePic, 1);
            }
        }
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyymmdd_HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile( name,".jpg", storageDir);
        } catch (IOException e){
            Log.d("mylog","Excep : "+e.toString());
        }
        return image;
    }

}


