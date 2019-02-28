package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.bitmap.BitmapHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;

@SuppressWarnings("ALL")
public class CamaraActivity extends AppCompatActivity {

    ImageView imageView;
    Button cargar, test1, volverInicio;
    private final String CARPETA_RAIZ="colorimetria/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"images";
    private String path;
    private final int CODIGO_GALERIA = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        imageView = findViewById(R.id.imagen);
        test1 = findViewById(R.id.btnTest);

        cargar = findViewById(R.id.btnCargar);

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView.buildDrawingCache();
                Bitmap bitmap = imageView.getDrawingCache();

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bStream);
                byte[] byteArray = bStream.toByteArray();

                Intent anotherIntent = new Intent(getApplicationContext(), MuestraMarckisActivity.class);
                anotherIntent.putExtra("image", byteArray);
                startActivity(anotherIntent);
                finish();

            }
        });

        volverInicio = findViewById(R.id.btnVolver);

        volverInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent,"Seleccione la aplicación"),CODIGO_GALERIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){

            Uri path = data.getData();
            imageView.setImageURI(path);
        }
    }
}
