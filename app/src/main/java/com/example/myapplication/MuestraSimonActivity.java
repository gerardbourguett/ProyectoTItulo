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

public class MuestraSimonActivity extends AppCompatActivity {

    Button vol_simon_inicio, continuarResultado, enviarMecke;
    ImageView imageSim;
    RadioButton sim_rgb, simon_nr;
    int red4, green4, blue4;
    private int rojo1,rojo2,rojo3,verde1,verde2,verde3,azul1,azul2,azul3;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_simon);

        sim_rgb = findViewById(R.id.simonRGB);
        simon_nr = findViewById(R.id.sim_nr);

        imageSim = (ImageView) findViewById(R.id.imagen_simon);
        Bitmap bmp4;

        byte[] byteArray4 = getIntent().getByteArrayExtra("image4");
        bmp4 = BitmapFactory.decodeByteArray(byteArray4, 0, byteArray4.length);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            int image = bundle.getInt("image4");
            imageSim.setImageBitmap(bmp4);

            rojo1 = bundle.getInt("r1");
            verde1 = bundle.getInt("g1");
            azul1 = bundle.getInt("b1");

            rojo2 = bundle.getInt("r2");
            verde2 = bundle.getInt("g2");
            azul2 = bundle.getInt("b2");

            rojo3 = bundle.getInt("r3");
            verde3 = bundle.getInt("g3");
            azul3 = bundle.getInt("b3");
        }

        vol_simon_inicio = findViewById(R.id.btnSimVolver);

        vol_simon_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CamaraActivity.class);
                startActivity(intent);
            }
        });

        imageSim.setDrawingCacheEnabled(true);
        imageSim.buildDrawingCache(true);

        imageSim.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN ||
                        event.getAction() == MotionEvent.ACTION_MOVE){
                    Bitmap bitmap = imageSim.getDrawingCache();
                    int pixel = bitmap.getPixel((int) event.getX(), (int) event.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    sim_rgb.setBackgroundColor(Color.rgb(r,g,b));

                    red4 = r;
                    green4 = g;
                    blue4 = b;

                    /*
                    String c = ColorHex(r,g,b);
                    hexView.setText(c);
                    color.setBackgroundColor(android.graphics.Color.rgb(r,g,b));
                    */

                }

                return true;
            }
        });

        continuarResultado = findViewById(R.id.btnResul);

        continuarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();

                /*imageMe.buildDrawingCache();
                Bitmap bitmap = imageMe.getDrawingCache();

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();*/

                Intent anotherIntent = new Intent(getApplicationContext(), ResultadosActivity.class);

                anotherIntent.putExtra("r1",rojo1);
                anotherIntent.putExtra("g1",verde1);
                anotherIntent.putExtra("b1",azul1);

                anotherIntent.putExtra("r2",rojo2);
                anotherIntent.putExtra("g2",verde2);
                anotherIntent.putExtra("b2",azul2);

                anotherIntent.putExtra("r3",rojo3);
                anotherIntent.putExtra("g3",verde3);
                anotherIntent.putExtra("b3",azul3);

                anotherIntent.putExtra("r4",red4);
                anotherIntent.putExtra("g4",green4);
                anotherIntent.putExtra("b4",blue4);


                startActivity(anotherIntent);
                finish();
            }
        });

    }

    private void validar() {
        String res = "";

        if(sim_rgb.isChecked()){
            res = (String) sim_rgb.getText();
        }
        if (simon_nr.isChecked()){
            res = "NO REACTION";
            red4 = 256;
            green4 = 256;
            blue4 = 256;
        }

        /*Toast.makeText(getApplicationContext(), "Muestra Simon Registrada", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), ResultadosActivity.class);

        intent.putExtra("sim_red", red4);
        intent.putExtra("sim_gre", green4);
        intent.putExtra("sim_blu", blue4);
        startActivity(intent);*/
    }
}
