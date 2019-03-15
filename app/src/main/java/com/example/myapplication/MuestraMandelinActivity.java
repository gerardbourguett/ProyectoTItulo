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

    Button vol_mandelin_inicio, continuarSimon;
    ImageView imageMan;
    RadioButton man_rgb, mandelin_nr;
    int red3, green3, blue3;
    private int rojo1,rojo2,rojo3,verde1,verde2,verde3,azul1,azul2,azul3;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_mandelin);

        man_rgb = findViewById(R.id.mandelinRGB);
        mandelin_nr = findViewById(R.id.man_nr);

        imageMan = (ImageView) findViewById(R.id.imagen_mandelin);
        Bitmap bmp3;

        byte[] byteArray3 = getIntent().getByteArrayExtra("image3");
        bmp3 = BitmapFactory.decodeByteArray(byteArray3, 0, byteArray3.length);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            int image = bundle.getInt("image3");
            imageMan.setImageBitmap(bmp3);

            rojo1 = bundle.getInt("r1");
            verde1 = bundle.getInt("g1");
            azul1 = bundle.getInt("b1");

            rojo2 = bundle.getInt("r2");
            verde2 = bundle.getInt("g2");
            azul2 = bundle.getInt("b2");
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

                    man_rgb.setBackgroundColor(Color.rgb(r,g,b));

                    red3 = r;
                    green3 = g;
                    blue3 = b;

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
                validar();

                imageMan.buildDrawingCache();
                Bitmap bitmap = imageMan.getDrawingCache();

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();

                Intent anotherIntent = new Intent(MuestraMandelinActivity.this, MuestraSimonActivity.class);
                anotherIntent.putExtra("image4", byteArray);

                anotherIntent.putExtra("r1",rojo1);
                anotherIntent.putExtra("g1",verde1);
                anotherIntent.putExtra("b1",azul1);

                anotherIntent.putExtra("r2",rojo2);
                anotherIntent.putExtra("g2",verde2);
                anotherIntent.putExtra("b2",azul2);

                anotherIntent.putExtra("r3",red3);
                anotherIntent.putExtra("g3",green3);
                anotherIntent.putExtra("b3",blue3);



                startActivity(anotherIntent);
                finish();
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
            red3 = 256;
            green3 = 256;
            blue3 = 256;
        }
        /*Toast.makeText(getApplicationContext(), "Muestra Registrada", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), ResultadosActivity.class);

        intent.putExtra("man_red", red3);
        intent.putExtra("man_gre", green3);
        intent.putExtra("man_blu", blue3);
        startActivity(intent);*/
    }
}
