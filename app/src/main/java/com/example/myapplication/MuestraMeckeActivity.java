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

public class MuestraMeckeActivity extends AppCompatActivity {

    Button volverInicio, continuarMandelin;
    ImageView imageMe;
    RadioButton me_rgb, mecke_nr;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_marckis);

        me_rgb = findViewById(R.id.meckeRGB);
        mecke_nr = findViewById(R.id.me_nr);

        imageMe = (ImageView) findViewById(R.id.imagen4);
        Bitmap bmp2;

        byte[] byteArray2 = getIntent().getByteArrayExtra("image2");
        bmp2 = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);

        Bundle bundle2 = getIntent().getExtras();

        if (bundle2!=null){
            int image = bundle2.getInt("image2");
            imageMe.setImageBitmap(bmp2);
        }

        volverInicio = findViewById(R.id.btnManVolver);

        volverInicio.setOnClickListener(new View.OnClickListener() {
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
                    Bitmap bitmap2 = imageMe.getDrawingCache();
                    int pixel2 = bitmap2.getPixel((int) event.getX(), (int) event.getY());

                    int r2 = Color.red(pixel2);
                    int g2 = Color.green(pixel2);
                    int b2 = Color.blue(pixel2);

                    //textView.setBackgroundColor(Color.rgb(r,g,b));
                    me_rgb.setText("RGB ("+r2+","+g2+","+b2+")");

                    /*
                    String c = ColorHex(r,g,b);
                    hexView.setText(c);
                    color.setBackgroundColor(android.graphics.Color.rgb(r,g,b));
                    */

                }

                return true;
            }
        });

        continuarMandelin = findViewById(R.id.btnResultados);

        continuarMandelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

    }

    private void validar() {
        String res = "";

        if(me_rgb.isChecked()){
            res = (String) me_rgb.getText();
        }
        if (mecke_nr.isChecked()){
            res = "NO REACTION";
        }

        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();

    }


    /*public String ColorHex(int r,int g,int b){
        String color = "";
        color = "#";
        color+=Integer.toHexString(r);
        color+=Integer.toHexString(g);
        color+=Integer.toHexString(b);
        return color;
    }*/
}
