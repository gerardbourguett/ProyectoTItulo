package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.constantes.Marckis;
import com.example.myapplication.objeto.ObjectMarckis;

public class ResultadosActivity extends AppCompatActivity implements Marckis {

    TextView r1,g1,b1;
    String rojo1,verde1,azul1;
    String rojo2,verde2,azul2;
    String rojo3,verde3,azul3;
    String rojo4,verde4,azul4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        r1 = findViewById(R.id.marR);
        g1 = findViewById(R.id.marG);
        b1 = findViewById(R.id.marB);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){

            rojo1 = bundle.getString("mar_red");
            verde1 = bundle.getString("mar_gre");
            azul1 = bundle.getString("mar_blu");

            if (rojo1 != null && verde1 != null && azul1 != null){
                r1.setText(rojo1);
                g1.setText(verde1);
                b1.setText(azul1);
            } else {
                r1.setText("NR");
                g1.setText("NR");
                b1.setText("NR");
            }
            /*ObjectMarckis objectMarckis = bundle.getParcelable("objUno");

            r1.setText(String.format("s%\n%s\n%s\n",
                    objectMarckis.getrMarckis(), objectMarckis.getgMarckis(), objectMarckis.getbMarckis()));*/
        }

    }
}
