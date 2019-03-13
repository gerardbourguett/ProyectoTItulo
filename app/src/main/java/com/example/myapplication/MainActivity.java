package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
                comenzar.setEnabled(true);
                Intent intent = new Intent(MainActivity.this, CamaraActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Bienvenido...", Toast.LENGTH_SHORT).show();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarApp();
            }
        });
    }

    private void cerrarApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.alert_light_frame);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea salir de la aplicación?");
        builder.setCancelable(false);
        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Saliendo...", Toast.LENGTH_SHORT).show();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
}
