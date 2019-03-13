package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import java.io.ByteArrayOutputStream;

public class MuestraMarckisActivity extends AppCompatActivity {

    Button vol_mar_inicio, continuarMecke, enviarMar;
    ImageView imageMa;
    RadioButton mar_rgb, marckis_nr;
    String red1, green1, blue1;
    int r1,g1,b1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_marckis);
        
        mar_rgb = findViewById(R.id.marckisRGB);
        marckis_nr = findViewById(R.id.mar_nr);

        imageMa = (ImageView) findViewById(R.id.imagen_marckis);
        Bitmap bmp;

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            int image = bundle.getInt("image");
            imageMa.setImageBitmap(bmp);
        }

        vol_mar_inicio = findViewById(R.id.btnMarVolver);

        vol_mar_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MuestraMarckisActivity.this, CamaraActivity.class);
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
                    
                    mar_rgb.setBackgroundColor(Color.rgb(r,g,b));
                    mar_rgb.setText("Pinche en la imagen");

                    red1 = String.valueOf(r);
                    green1 = String.valueOf(g);
                    blue1 = String.valueOf(b);

                    r1 = r;
                    g1 = g;
                    b1 = b;
                    Log.d("marckis"," "+red1+" "+green1+" "+blue1);


                    //String c = ColorHex(r,g,b);
                    //hexView.setText(c);
                    //olor.setBackgroundColor(android.graphics.Color.rgb(r,g,b));

                }

                return true;
            }
        });

        continuarMecke = findViewById(R.id.btnMecke);

        continuarMecke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();

                imageMa.buildDrawingCache();
                Bitmap bitmap = imageMa.getDrawingCache();

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();

                Intent anotherIntent = new Intent(MuestraMarckisActivity.this, MuestraMeckeActivity.class);

                anotherIntent.putExtra("image2", byteArray);
                anotherIntent.putExtra("r1",r1);
                anotherIntent.putExtra("g1",g1);
                anotherIntent.putExtra("b1",b1);
                startActivity(anotherIntent);
                finish();
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
            r1 = 256;
            g1 = 256;
            b1 = 256;
        }

        //Toast.makeText(getApplicationContext(), "Muestra Marckis Registrada", Toast.LENGTH_SHORT).show();

        /*Intent intent = new Intent(getApplicationContext(), ResultadosActivity.class);

        intent.putExtra("mar_red", r1);
        intent.putExtra("mar_gre", g1);
        intent.putExtra("mar_blu", b1);
        startActivity(intent);*/
    }



        public String ColorHex(int r,int g,int b){
            String color = "";
            color = "#";
            color+=Integer.toHexString(r);
            color+=Integer.toHexString(g);
            color+=Integer.toHexString(b);
            return color;
        }
}
