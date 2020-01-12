package com.babeirosemgloria.barbergloria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.babeirosemgloria.barbergloria.R;

import java.util.ArrayList;

public class Servico extends AppCompatActivity {

    private Button btnCancelar;
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String>  servicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);


        servicos = new ArrayList<>();
        servicos.add("Teste");
        servicos.add("Teste 2");
        servicos.add("TEste 3");
        btnCancelar = findViewById(R.id.btnCancelar);
        // listView = findViewById(R.id.lvServicos);

        adapter = new ArrayAdapter(
            getApplication(),
            android.R.layout.simple_list_item_1,
            servicos
        );

        //listView.setAdapter( adapter );

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
