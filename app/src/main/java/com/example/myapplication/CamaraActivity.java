package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;


public class CamaraActivity extends AppCompatActivity {

    ImageView imageView;
    ImageButton imageButton, infoButton;
    Button test, volver1;
    String pathToFile, photoUrl;
    Size size;

    private Uri uri;
    private StorageReference mStorage;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        mProgress = new ProgressDialog(this);

        if (Build.VERSION.SDK_INT>23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }

        imageView = findViewById(R.id.imagen);
        imageButton = findViewById(R.id.cargar);
        infoButton = findViewById(R.id.info);

        mStorage = FirebaseStorage.getInstance().getReference();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }
                /*dispatchPictureTakerAction();*/
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


                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                imageView.setImageBitmap(photo);

                mProgress.setMessage("Uploading image...");
                mProgress.show();

                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                final Uri tempUri = getImageUri(getApplicationContext(), photo);

                //Show Uri path based on Image
                Toast.makeText(CamaraActivity.this,"Here "+ tempUri, Toast.LENGTH_LONG).show();
                Log.d("here", ""+tempUri);

                //Show Uri path based on Cursor Content Resolver
                Toast.makeText(this, "Real path for URI : "+getRealPathFromURI(tempUri), Toast.LENGTH_SHORT).show();
                Log.d("real", ""+getRealPathFromURI(tempUri));

                StorageReference filepath = mStorage.child("Photos").child(String.valueOf(getRealPathFromURI(tempUri)));
                filepath.putFile(tempUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(CamaraActivity.this, "Uploading Finished...", Toast.LENGTH_LONG).show();
                        Picasso.get()
                                .load(tempUri)
                                .resize(350, 350)
                                .into(imageView);
                        mProgress.dismiss();
                    }
                });


            }
            else
            {
                Toast.makeText(this, "Failed To Capture Image", Toast.LENGTH_SHORT).show();
                Log.d("failed", "Failed To Capture Image");
            }

                /*Uri uri = data.getData();

                assert uri != null;
                StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());
                filepath.putFile(Uri.parse(photoUrl)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(CamaraActivity.this, "Uploading Finished...", Toast.LENGTH_LONG).show();
                        mProgress.dismiss();
                    }
                });

                *//*Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                imageView.setImageBitmap(bitmap);*/
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

    String mCurrentPhotoPath;

    private File createPhotoFile() throws IOException {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timestamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchPictureTakerAction() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createPhotoFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null){
                Uri photoUri = FileProvider.getUriForFile(CamaraActivity.this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, 1);
            }

            /**//* String pathToFile = photoFile.getAbsolutePath(); *//*
            if (photoFile!=null){
                pathToFile = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(CamaraActivity.this, "com.example.android.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePic, 1);
            }*/
        }
    }

    private Uri getImageUri(Context applicationContext, Bitmap photo) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(CamaraActivity.this.getContentResolver(), photo, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
}