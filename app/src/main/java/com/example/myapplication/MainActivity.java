package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button comenzar, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comenzar = findViewById(R.id.btnCam);
        salir = findViewById(R.id.btnSalir);

        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CamaraActivity.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "Bienvenido...", Toast.LENGTH_SHORT).show();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                Toast.makeText(MainActivity.this, "Saliendo...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
