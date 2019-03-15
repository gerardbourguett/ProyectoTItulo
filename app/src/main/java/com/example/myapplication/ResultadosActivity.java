package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.constantes.Mandelin;
import com.example.myapplication.constantes.Marckis;
import com.example.myapplication.constantes.Mecke;
import com.example.myapplication.constantes.Simon;

import java.util.ArrayList;

public class ResultadosActivity extends AppCompatActivity implements Marckis, Mecke, Mandelin, Simon {

    TextView cMarckis, cMecke, cMandelin, cSimon;
    int rojo1,verde1,azul1;
    int rojo2,verde2,azul2;
    int rojo3,verde3,azul3;
    int rojo4,verde4,azul4;
    ListView resSimon, resMarckis, resMecke, resMandeli;
    Button retomar, enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        enviar = findViewById(R.id.btnEnviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ResultadosActivity.this, "Resultados Enviados", Toast.LENGTH_SHORT).show();
            }
        });

        retomar = findViewById(R.id.btnRetomar);

        retomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CamaraActivity.class);
                startActivity(intent);
            }
        });

        cMarckis = findViewById(R.id.colorMarckis);
        cMecke = findViewById(R.id.colorMecke);
        cMandelin = findViewById(R.id.colorMandelin);
        cSimon = findViewById(R.id.colorSimon);
        
        resSimon = findViewById(R.id.resCoiSim);
        resMarckis = findViewById(R.id.resCoiMar);
        resMecke = findViewById(R.id.resCoiMec);
        resMandeli = findViewById(R.id.resCoiMan);

        resMarckis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Quimicos: "
                        +parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        resMecke.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Quimicos: "
                                                    +parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        resMandeli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Quimicos: "
                                                    +parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        resSimon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Quimicos: "
                                                    +parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Bundle bundle = getIntent().getExtras();


        if (bundle!=null){

            rojo1 = bundle.getInt("r1");
            verde1 = bundle.getInt("g1");
            azul1 = bundle.getInt("b1");

            rojo2 = bundle.getInt("r2");
            verde2 = bundle.getInt("g2");
            azul2 = bundle.getInt("b2");

            rojo3 = bundle.getInt("r3");
            verde3 = bundle.getInt("g3");
            azul3 = bundle.getInt("b3");

            rojo4 = bundle.getInt("r4");
            verde4 = bundle.getInt("g4");
            azul4 = bundle.getInt("g4");

            //cMarckis.setText("Rango: "+rojo1+" "+verde1+" "+azul1);

            cMecke.setBackgroundColor(Color.rgb(rojo2,verde2,azul2));

            cMandelin.setBackgroundColor(Color.rgb(rojo3,verde3,azul3));

            cSimon.setBackgroundColor(Color.rgb(rojo4,verde4,azul4));

            Log.d("prueba1", "Color "+rojo1+" "+verde1+" "+azul1);
            Log.d("prueba2", "Color "+rojo2+" "+verde2+" "+azul2);
            Log.d("prueba3", "Color "+rojo3+" "+verde3+" "+azul3);
            Log.d("prueba4", "Color "+rojo4+" "+verde4+" "+azul4);

            int validador = 0;

            ArrayList<String> listaMarckis = new ArrayList<>();
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaMarckis);
            resMarckis.setAdapter(arrayAdapter);


            if (rojo1 == 256){
                cMarckis.setBackgroundColor(Color.rgb(180,151,120));
                cMarckis.setText("NO REACTION");
                cMarckis.setTextColor(Color.rgb(255,255,255));

                listaMarckis.add("4-MMC");
                listaMarckis.add("Alpha");
                listaMarckis.add("BZP");
                listaMarckis.add("TFMPP");
                listaMarckis.add("PMA");
                listaMarckis.add("PMMA");
                listaMarckis.add("Kethamine");
                listaMarckis.add("Methoxetamine");
                listaMarckis.add("Cocaine");

            } if (rojo1 < 256){
                cMarckis.setBackgroundColor(Color.rgb(rojo1,verde1,azul1));

                if ((rojo1 < 121 && rojo1 > 0) && (verde1 < 76  && verde1 > 0) && (azul1 < 76  && azul1 > 0)){
                    listaMarckis.add(MARCKIS_SMDMA);
                    listaMarckis.add((MARCKIS_SMDE));
                    listaMarckis.add(MARCKIS_SSASS);
                    validador = 1;
                } if ((rojo1 < 58 && rojo1 > 5) && (verde1 < 35  && verde1 > 5) && (azul1 < 35  && azul1 > 5)){
                    listaMarckis.add(MARCKIS_S5APB);
                    validador = 1;
                } if ((rojo1 < 252 && rojo1 > 244) && (verde1 < 250  && verde1 > 238) && (azul1 < 250  && azul1 > 238)){
                    listaMarckis.add(MARCKIS_SMETHYLONE);
                    listaMarckis.add(MARCKIS_SBUTYLONE);
                    listaMarckis.add(MARCKIS_SMDPV);
                    validador = 1;
                } if ((rojo1 < 230 && rojo1 > 220) && (verde1 < 230  && verde1 > 220) && (azul1 < 230  && azul1 > 220)){
                    listaMarckis.add(MARCKIS_SETHYLONE);
                    validador = 1;
                } if ((rojo1 < 247 && rojo1 > 230) && (verde1 < 200  && verde1 > 113) && (azul1 < 200  && azul1 > 113)){
                    listaMarckis.add(MARCKIS_SAMPHETAMINE);
                    listaMarckis.add(MARCKIS_SMETAMPHETAMINE);
                    listaMarckis.add(MARCKIS_SMESCALINE);
                    validador = 1;
                } if ((rojo1 < 220 && rojo1 > 18) && (verde1 < 222  && verde1 > 83) && (azul1 < 222  && azul1 > 83)){
                    listaMarckis.add(MARCKIS_S5APB);
                    validador = 1;
                } if ((rojo1 < 199 && rojo1 > 37) && (verde1 < 221  && verde1 > 70) && (azul1 < 221  && azul1 > 70)){
                    listaMarckis.add(MARCKIS_S6APB);
                    validador = 1;
                } if ((rojo1 < 128 && rojo1 > 12) && (verde1 < 128  && verde1 > 12) && (azul1 < 128  && azul1 > 12)){
                    listaMarckis.add(MARCKIS_SDXM);
                    validador = 1;
                } if ((rojo1 < 176 && rojo1 > 120) && (verde1 < 161  && verde1 > 97) && (azul1 < 161  && azul1 > 97)){
                    listaMarckis.add(MARCKIS_SCODEYNE);
                    validador = 1;
                } if ((rojo1 < 184 && rojo1 > 134) && (verde1 < 156  && verde1 > 44) && (azul1 < 156  && azul1 > 44)){
                    listaMarckis.add(MARCKIS_SMORPHINE);
                    validador = 1;
                } if ((rojo1 < 143 && rojo1 > 121) && (verde1 < 61  && verde1 > 27) && (azul1 < 61  && azul1 > 27)){
                    listaMarckis.add(MARCKIS_SHEROIN);
                    validador = 1;
                } if ((rojo1 < 236 && rojo1 > 205) && (verde1 < 199  && verde1 > 43) && (azul1 < 199  && azul1 > 43)){
                    listaMarckis.add(MARCKIS_SETHYLONE);
                    validador = 1;
                }

                if (validador == 0){
                    listaMarckis.add("UNK");
                }
            }

            ArrayList<String> listaMecke = new ArrayList<>();
            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaMecke);
            resMecke.setAdapter(arrayAdapter2);


            int validador3 = 0;

            if (rojo2 == 256){
                cMecke.setBackgroundColor(Color.rgb(180,151,120));
                cMecke.setText("NO REACTION");
                cMecke.setTextColor(Color.rgb(255,255,255));

                listaMecke.add("Flakka");
                listaMecke.add("Metanphetamine");
                listaMecke.add("Amphetamine");
                listaMecke.add("BZP");
                listaMecke.add("TFMPP");
                listaMecke.add("PMA");
                listaMecke.add("PMMA");
                listaMecke.add("Cocaine");


            } if (rojo2 < 256){
                cMecke.setBackgroundColor(Color.rgb(rojo2,verde2,azul2));

                if ((rojo2 < 96 && rojo2 > 16) && (verde2 < 123  && verde2 > 21) && (azul2 < 123  && azul2 > 21)){
                    listaMecke.add(MECKE_SMDMA);
                    listaMecke.add((MECKE_SMDE));
                    listaMecke.add(MECKE_SSASS);
                    validador3 = 1;
                } if ((rojo2 < 73 && rojo2 > 5) && (verde2 < 73  && verde2 > 5) && (azul2 < 73  && azul2 > 5)){
                    listaMecke.add(MECKE_S5APB);
                    validador3 = 1;
                } if ((rojo2 < 119 && rojo2 > 16) && (verde2 < 113  && verde2 > 49) && (azul2 < 113  && azul2 > 49)){
                    listaMecke.add("Mescaline");
                    listaMecke.add("2-ci");
                    validador3 = 1;
                } if ((rojo2 < 167 && rojo2 > 151) && (verde2 < 95  && verde2 > 55) && (azul2 < 95  && azul2 > 55)){
                    listaMecke.add(MECKE_SMETHYLONE);
                    validador3 = 1;
                } if ((rojo2 < 212 && rojo2 > 144) && (verde2 < 202  && verde2 > 105) && (azul2 < 202  && azul2 > 105)){
                    listaMecke.add(MECKE_SETHYLONE);
                    validador3 = 1;
                } if ((rojo2 < 229 && rojo2 > 130) && (verde2 < 230  && verde2 > 82) && (azul2 < 230  && azul2 > 82)){
                    listaMecke.add(MECKE_SBUTYLONE);
                    validador3 = 1;
                } if ((rojo2 < 237 && rojo2 > 196) && (verde2 < 226  && verde2 > 176) && (azul2 < 226  && azul2 > 176)){
                    listaMecke.add("Mephedrone");
                    validador3 = 1;
                } if ((rojo2 < 240 && rojo2 > 230) && (verde2 < 240  && verde2 > 230) && (azul2 < 240  && azul2 > 230)){
                    listaMecke.add(MECKE_SMDPV);
                    validador3 = 1;
                } if ((rojo2 < 198 && rojo2 > 96) && (verde2 < 182  && verde2 > 72) && (azul2 < 182  && azul2 > 72)){
                    listaMecke.add("2c-b");
                    validador3 = 1;
                } if ((rojo2 < 156 && rojo2 > 59) && (verde2 < 141  && verde2 > 47) && (azul2 < 141  && azul2 > 47)){
                    listaMecke.add("2c-i");
                    validador3 = 1;
                } if ((rojo2 < 168 && rojo2 > 82) && (verde2 < 185  && verde2 > 93) && (azul2 < 185  && azul2 > 93)){
                    listaMecke.add("Methoxatamine");
                    validador3 = 1;
                } if ((rojo2 < 250 && rojo2 > 230) && (verde2 < 250  && verde2 > 230) && (azul2 < 250  && azul2 > 230)){
                    listaMecke.add("DXM");
                    validador3 = 1;
                } if ((rojo2 < 35 && rojo2 > 25) && (verde2 < 70  && verde2 > 49) && (azul2 < 70  && azul2 > 49)){
                    listaMecke.add(MECKE_SCODEYNE);
                    validador3 = 1;
                } if ((rojo2 < 78 && rojo2 > 31) && (verde2 < 98  && verde2 > 49) && (azul2 < 98  && azul2 > 49)){
                    listaMecke.add(MECKE_SMORPHINE);
                    validador3 = 1;
                } if ((rojo2 < 225 && rojo2 > 190) && (verde2 < 2250  && verde2 > 170) && (azul2 < 223  && azul2 > 170)){
                    listaMecke.add(MECKE_SOXYCODONE);
                    validador3 = 1;
                }  if ((rojo2 < 60 && rojo2 > 50) && (verde2 < 70  && verde2 > 60) && (azul2 < 70  && azul2 > 60)){
                    listaMecke.add(MECKE_SHEROIN);
                    validador3 = 1;
                } if ((rojo2 < 119 && rojo2 > 66) && (verde2 < 113  && verde2 > 49) && (azul2 < 113  && azul2 > 49)){
                    listaMecke.add("Mescaline");
                    validador3 = 1;
                } if ((rojo2 < 73 && rojo2 > 5) && (verde2 < 73  && verde2 > 5) && (azul2 < 73  && azul2 > 5)){
                    listaMecke.add(MECKE_S5APB);
                    validador3 = 1;
                } if (validador3 == 0){
                    listaMecke.add("UNK");
                }
            }

            ArrayList<String> listaMandelin = new ArrayList<>();
            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaMandelin);
            resMandeli.setAdapter(arrayAdapter3);

            int validador2 = 0;

            if (rojo3 == 256){
                cMandelin.setBackgroundColor(Color.rgb(180,151,120));
                cMandelin.setText("NO REACTION");
                cMandelin.setTextColor(Color.rgb(255,255,255));




            } if (rojo3 < 256) {
                cMandelin.setBackgroundColor(Color.rgb(rojo3, verde3, azul3));

                if ((rojo3 < 123 && rojo3 > 0) && (verde3 < 123  && verde3 > 0) && (azul3 < 123  && azul3 > 0)) {
                    listaMandelin.add(MANDELIN_SMDMA);
                    listaMandelin.add(MANDELIN_SMDE);
                    listaMandelin.add(MANDELIN_SSASS);
                    listaMandelin.add(MANDELIN_S5APB);
                    validador2 = 1;

                } if ((rojo3 < 112 && rojo3 > 11) && (verde3 < 136  && verde3 > 79) && (azul3 < 136  && azul3 > 79)) {
                    listaMandelin.add(MANDELIN_SMETAMPHETAMINE);
                    listaMandelin.add(MANDELIN_SAMPHETAMINE);
                    validador2 = 1;

                } if ((rojo3 < 230 && rojo3 > 220) && (verde3 < 197  && verde3 > 153) && (azul3 < 197  && azul3 > 153)) {
                    listaMandelin.add(MANDELIN_SCODEYNE);
                    listaMandelin.add("Cocaine");
                    validador2 = 1;

                } if ((rojo3 < 155 && rojo3 > 92) && (verde3 < 187  && verde3 > 74) && (azul3 < 187  && azul3 > 74)) {
                    listaMandelin.add("PMA");
                    listaMandelin.add("PMMA");
                    validador2 = 1;

                } if ((rojo3 < 243 && rojo3 > 128) && (verde3 < 240  && verde3 > 78) && (azul3 < 240  && azul3 > 78)) {
                    listaMandelin.add(MANDELIN_SMETHYLONE);
                    validador2 = 1;

                } if ((rojo3 < 202 && rojo3 > 160) && (verde3 < 202  && verde3 > 107) && (azul3 < 202  && azul3 > 127)) {
                    listaMandelin.add(MANDELIN_SETHYLONE);
                    validador2 = 1;

                } if ((rojo3 < 170 && rojo3 > 28) && (verde3 < 170  && verde3 > 28) && (azul3 < 170  && azul3 > 23)) {
                    listaMandelin.add("Mephedrone");
                    validador = 1;

                } if ((rojo3 < 172 && rojo3 > 61) && (verde3 < 180  && verde3 > 54) && (azul3 < 180  && azul3 > 54)) {
                    listaMandelin.add(MANDELIN_SMDPV);
                    validador2 = 1;

                } if ((rojo3 < 230 && rojo3 > 200) && (verde3 < 230  && verde3 > 210) && (azul3 < 230  && azul3 > 210)) {
                    listaMandelin.add("2C-b");
                    validador2 = 1;

                } if ((rojo3 < 149 && rojo3 > 7) && (verde3 < 129  && verde3 > 7) && (azul3 < 129  && azul3 > 7)) {
                    listaMandelin.add("2C-i");
                    validador2 = 1;

                } if ((rojo3 < 240 && rojo3 > 120) && (verde3 < 190  && verde3 > 70) && (azul3 < 190  && azul3 > 70)) {
                    listaMandelin.add("Ketamine");
                    validador2 = 1;

                } if ((rojo3 < 230 && rojo3 > 140) && (verde3 < 220  && verde3 > 130) && (azul3 < 200  && azul3 > 130)) {
                    listaMandelin.add("Methoxetamine");
                    validador2 = 1;

                } if ((rojo3 < 150 && rojo3 > 70) && (verde3 < 150  && verde3 > 75) && (azul3 < 155  && azul3 > 80)) {
                    listaMandelin.add(MANDELIN_SOXYCODONE);
                    validador2 = 1;

                } if ((rojo3 < 220 && rojo3 > 110) && (verde3 < 100  && verde3 > 80) && (azul3 < 100  && azul3 > 80)) {
                    listaMandelin.add(MANDELIN_SHEROIN);
                    validador2 = 1;

                } if ((rojo3 < 210 && rojo3 > 110) && (verde3 < 200  && verde3 > 90) && (azul3 < 200  && azul3 > 90)) {
                    listaMandelin.add("MEscaline");
                    validador2 = 1;

                } if (validador2 == 0){
                    listaMandelin.add("UNK");
                }
            }

            ArrayList<String> listaSimon = new ArrayList<>();
            ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaSimon);
            resSimon.setAdapter(arrayAdapter4);

            int validador4 = 0;

            if (rojo4 == 256){
                cSimon.setBackgroundColor(Color.rgb(180,151,120));
                cSimon.setText("NO REACTION");
                cSimon.setTextColor(Color.rgb(255,255,255));

                listaSimon.add(SIMON_SMDMA);
                listaSimon.add(SIMON_SSASS);
                listaSimon.add("Flakka");
                listaSimon.add(SIMON_SMETAMPHETAMINE);
                listaSimon.add("PMMA");
                listaSimon.add("BZP");
                listaSimon.add("2C-i");
                listaSimon.add("Ketamine");
                listaSimon.add("Methoxetamine");
                listaSimon.add("DMX");
                listaSimon.add(SIMON_SCODEYNE);
                listaSimon.add(SIMON_SMORPHINE);
                listaSimon.add(SIMON_SOXYCODONE);
                listaSimon.add(SIMON_SMORPHINE);
                listaSimon.add(SIMON_SHEROIN);
                listaSimon.add("Cocaine");
                listaSimon.add("Mescaline");


                
            } if (rojo4 < 256){
                cSimon.setBackgroundColor(Color.rgb(rojo4,verde4,azul4));
                listaSimon.add(SIMON_SMDMA);
                listaSimon.add(SIMON_SMDE);
                listaSimon.add(SIMON_SMETHYLONE);
                listaSimon.add(SIMON_SETHYLONE);
                listaSimon.add(SIMON_SBUTYLONE);
                listaSimon.add("Mephedrone");
                listaSimon.add(SIMON_SMDPV);
                listaSimon.add(SIMON_SAMPHETAMINE);
                listaSimon.add("2C-b");

                validador = 1;
            } if (validador == 0){
                listaSimon.add("UNK");
                listaSimon.add("TFMPP");
                listaSimon.add("PMA");
            }

        }

    }


}
