package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.bitmap.BitmapHelper;

public class MuestraMarckisActivity extends AppCompatActivity {

    Button volver, continuarMecke;
    ImageView imageMa;
    RadioButton mar_rgb, marckis_nr;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_marckis);
        
        mar_rgb = findViewById(R.id.marckisRGB);
        marckis_nr = findViewById(R.id.mar_nr);

        imageMa = (ImageView) findViewById(R.id.imagen2);
        Bitmap bmp;

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            int image = bundle.getInt("image");
            imageMa.setImageBitmap(bmp);
        }

        volver = findViewById(R.id.btnMarVolver);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CamaraActivity.class);
                startActivity(intent);
            }
        });

        imageMa.setDrawingCacheEnabled(true);
        imageMa.buildDrawingCache(true);

        imageMa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN ||
                        event.getAction() == MotionEvent.ACTION_MOVE){
                    Bitmap bitmap = imageMa.getDrawingCache();
                    int pixel = bitmap.getPixel((int) event.getX(), (int) event.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);
                    
                    //textView.setBackgroundColor(Color.rgb(r,g,b));
                    mar_rgb.setText("RGB ("+r+","+g+","+b+")");

                    /*
                    String c = ColorHex(r,g,b);
                    hexView.setText(c);
                    color.setBackgroundColor(android.graphics.Color.rgb(r,g,b));
                    */

                }

                return true;
            }
        });

        continuarMecke = findViewById(R.id.btnMecke);

        continuarMecke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

    }

    private void validar() {
        String res = "";

        if(mar_rgb.isChecked()){
            res = (String) mar_rgb.getText();
        }
        if (marckis_nr.isChecked()){
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
