package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MuestraMandelinActivity extends AppCompatActivity {

    Button vol_mandelin_inicio, continuarSimon, enviarSimon;
    ImageView imageMan;
    RadioButton man_rgb, mandelin_nr;
    String red3, green3, blue3;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_mandelin);

        man_rgb = findViewById(R.id.mandelinRGB);
        mandelin_nr = findViewById(R.id.man_nr);
        enviarSimon = findViewById(R.id.btn_enviar_man);

        imageMan = (ImageView) findViewById(R.id.imagen_mandelin);
        Bitmap bmp;

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            int image = bundle.getInt("image");
            imageMan.setImageBitmap(bmp);
        }

        vol_mandelin_inicio = findViewById(R.id.btnManVolver);

        vol_mandelin_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CamaraActivity.class);
                startActivity(intent);
            }
        });

        imageMan.setDrawingCacheEnabled(true);
        imageMan.buildDrawingCache(true);

        imageMan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN ||
                        event.getAction() == MotionEvent.ACTION_MOVE){
                    Bitmap bitmap = imageMan.getDrawingCache();
                    int pixel = bitmap.getPixel((int) event.getX(), (int) event.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    //textView.setBackgroundColor(Color.rgb(r,g,b));
                    man_rgb.setText("RGB ("+r+","+g+","+b+")");

                    red3 = String.valueOf(r);
                    green3 = String.valueOf(g);
                    blue3 = String.valueOf(b);

                    /*
                    String c = ColorHex(r,g,b);
                    hexView.setText(c);
                    color.setBackgroundColor(android.graphics.Color.rgb(r,g,b));
                    */

                }

                return true;
            }
        });

        continuarSimon = findViewById(R.id.btnSimon);

        continuarSimon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageMan.buildDrawingCache();
                Bitmap bitmap = imageMan.getDrawingCache();

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();

                Intent anotherIntent = new Intent(MuestraMandelinActivity.this, MuestraSimonActivity.class);
                anotherIntent.putExtra("image2", byteArray);
                startActivity(anotherIntent);
                finish();
            }
        });

        enviarSimon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

    }

    private void validar() {
        String res = "";

        if(man_rgb.isChecked()){
            res = (String) man_rgb.getText();
        }
        if (mandelin_nr.isChecked()){
            res = "NO REACTION";
        }

        Toast.makeText(getApplicationContext(), "Muestra Registrada", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), ResultadosActivity.class);

        intent.putExtra("man_red", red3);
        intent.putExtra("man_gre", green3);
        intent.putExtra("man_blu", blue3);
        //startActivity(intent);
    }
}
