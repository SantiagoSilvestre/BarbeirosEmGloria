package com.babeirosemgloria.barbergloria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.babeirosemgloria.barbergloria.R;

public class DataHora extends AppCompatActivity {

    private Button btnServico;
    private Button btnBarbeiro;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);

        btnServico = findViewById(R.id.btnServico);
        btnBarbeiro = findViewById(R.id.btnBarbeiro);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Servico.class);
                startActivity(intent);
            }
        });
        btnBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Barbeiros.class);
                startActivity(intent);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
