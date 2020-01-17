package com.babeirosemgloria.barbergloria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.babeirosemgloria.barbergloria.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Button btnServicos;
    private Button btnBarbeiro;
    private Button btnData;
    private Button btnlocalizacao;
    private Button btnpromocoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnServicos = findViewById(R.id.btnServico);
        btnBarbeiro = findViewById(R.id.btnBarbeiro);
        btnData = findViewById(R.id.btnData);
        btnlocalizacao = findViewById(R.id.btnlocalizacao);

        btnServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Servico.class);
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
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DataHora.class);
                startActivity(intent);
            }
        });
        btnlocalizacao.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        btnpromocoes.setOnClickListener(new View.OnClickListener() {
            @Override
                     public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Promocoes.class);
                startActivity(intent);
            }
        });
        // databaseReference.child("pontos").setValue(100);

    }
}
