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

public class MuestraMeckeActivity extends AppCompatActivity {

    Button vol_Mecke_inicio, continuarMandelin, enviarMecke;
    ImageView imageMe;
    RadioButton mec_rgb, mecke_nr;
    String red2, green2, blue2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_mecke);

        mec_rgb = findViewById(R.id.meckeRGB);
        mecke_nr = findViewById(R.id.mecke_nr);

        imageMe = (ImageView) findViewById(R.id.imagen_mecke);
        Bitmap bmp2;

        byte[] byteArray = getIntent().getByteArrayExtra("image2");
        bmp2 = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            int image = bundle.getInt("image2");
            imageMe.setImageBitmap(bmp2);
        }

        vol_Mecke_inicio = findViewById(R.id.btnMecVolver);

        vol_Mecke_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CamaraActivity.class);
                startActivity(intent);
            }
        });

        imageMe.setDrawingCacheEnabled(true);
        imageMe.buildDrawingCache(true);

        imageMe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN ||
                        event.getAction() == MotionEvent.ACTION_MOVE){
                    Bitmap bitmap = imageMe.getDrawingCache();
                    int pixel = bitmap.getPixel((int) event.getX(), (int) event.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    //textView.setBackgroundColor(Color.rgb(r,g,b));
                    mec_rgb.setText("RGB ("+r+","+g+","+b+")");

                    red2 = String.valueOf(r);
                    green2 = String.valueOf(g);
                    blue2 = String.valueOf(b);

                    /*
                    String c = ColorHex(r,g,b);
                    hexView.setText(c);
                    color.setBackgroundColor(android.graphics.Color.rgb(r,g,b));
                    */

                }

                return true;
            }
        });

        continuarMandelin = findViewById(R.id.btnMandelin);

        continuarMandelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();

                imageMe.buildDrawingCache();
                Bitmap bitmap = imageMe.getDrawingCache();

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bStream);
                byte[] byteArray = bStream.toByteArray();

                Intent anotherIntent = new Intent(MuestraMeckeActivity.this, MuestraMandelinActivity.class);
                anotherIntent.putExtra("image3", byteArray);
                startActivity(anotherIntent);
                finish();
            }
        });

    }

    private void validar() {
        String res = "";

        if(mec_rgb.isChecked()){
            res = (String) mec_rgb.getText();
        }
        if (mecke_nr.isChecked()){
            res = "NO REACTION";
        }

        Toast.makeText(getApplicationContext(), "Muestra Registrada", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), ResultadosActivity.class);

        intent.putExtra("mec_red", red2);
        intent.putExtra("mec_gre", green2);
        intent.putExtra("mec_blu", blue2);
        startActivity(intent);
    }


    //
//public String ColorHex(int r,int g,int b){
//String color = "";
//color = "#";
//color+=Integer.toHexString(r);
//color+=Integer.toHexString(g);
//color+=Integer.toHexString(b);
//return color;
//}
}
