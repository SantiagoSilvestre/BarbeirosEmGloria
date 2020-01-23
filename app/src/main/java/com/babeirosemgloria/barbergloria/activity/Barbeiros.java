package com.babeirosemgloria.barbergloria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.babeirosemgloria.barbergloria.R;

public class Barbeiros extends AppCompatActivity {

    private Button btnCancelar;
    private Button btnDvscuts;
    private Button btnIgor;
    private Button btnKaique;
    private Button btnSemPref;
    private int id = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barbeiros);

        btnCancelar = findViewById(R.id.btnCancelar);
        btnDvscuts = findViewById(R.id.btnDvscuts);
        btnIgor = findViewById(R.id.btnIgor);
        btnKaique = findViewById(R.id.btnKaique);
        btnSemPref = findViewById(R.id.btnSemPref);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnDvscuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 1;
                Toast.makeText(getApplicationContext(), "Dvscuts irá te atender", Toast.LENGTH_LONG);
            }
        });
        btnIgor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 2;
                Toast.makeText(getApplicationContext(), "Igor irá te atender", Toast.LENGTH_LONG);
            }
        });
        btnKaique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 3;
                Log.i("teste Button", "Ok");
                Toast.makeText(getApplicationContext(), "Kaique irá te atender", Toast.LENGTH_LONG);
            }
        });
        btnSemPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 4;
                Toast.makeText(getApplicationContext(), "O Barbeiro disponível irá te atender", Toast.LENGTH_LONG);
            }
        });


    }
}
