package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.constantes.Marckis;
import com.example.myapplication.objeto.ObjectMarckis;

public class ResultadosActivity extends AppCompatActivity implements Marckis {

    TextView cMarckis, cMecke, cMandelin, cSimon;
    int rojo1,verde1,azul1;
    int rojo2,verde2,azul2;
    int rojo3,verde3,azul3;
    int rojo4,verde4,azul4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        cMarckis = findViewById(R.id.colorMarckis);
        cMecke = findViewById(R.id.colorMecke);
        cMandelin = findViewById(R.id.colorMandelin);
        cSimon = findViewById(R.id.colorSimon);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){

            rojo1 = bundle.getInt("mar_red");
            verde1 = bundle.getInt("mar_gre");
            azul1 = bundle.getInt("mar_blu");

            rojo2 = bundle.getInt("mec_red");
            verde2 = bundle.getInt("mec_gre");
            azul2 = bundle.getInt("mec_blu");

            rojo3 = bundle.getInt("man_red");
            verde3 = bundle.getInt("man_gre");
            azul3 = bundle.getInt("man_blu");

            rojo3 = bundle.getInt("sim_red");
            verde3 = bundle.getInt("sim_gre");
            azul3 = bundle.getInt("sim_blu");

            cMarckis.setBackgroundColor(Color.rgb(rojo1,verde1,azul1));

            cMecke.setBackgroundColor(Color.rgb(rojo2,verde2,azul2));

            cMandelin.setBackgroundColor(Color.rgb(rojo3,verde3,azul3));

            cSimon.setBackgroundColor(Color.rgb(rojo4,verde4,azul4));

            /*ObjectMarckis objectMarckis = bundle.getParcelable("objUno");

            r1.setText(String.format("s%\n%s\n%s\n",
                    objectMarckis.getrMarckis(), objectMarckis.getgMarckis(), objectMarckis.getbMarckis()));*/
        }

    }
}
