package com.babeirosemgloria.barbergloria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.babeirosemgloria.barbergloria.R;

import java.util.ArrayList;

public class Servico extends AppCompatActivity {

    private Button btnCancelar;
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String>  servicos;
    private CheckBox barba;
    private CheckBox corte;
    private CheckBox corBarb;
    private CheckBox corProg;
    private CheckBox hidratacao;
    private CheckBox corRela;
    private TextView txtValor;
    private CheckBox teste;

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

    public void recuperaValor(View view) {
        // Aqui será feita a lógica para recuperar os serviços
        barba = findViewById(R.id.checkBarba);
        corte = findViewById(R.id.checkCorte);
        corBarb = findViewById(R.id.checkCorBar);
        corProg = findViewById(R.id.checkCorProg);
        hidratacao = findViewById(R.id.checkHidratacao);
        corRela = findViewById(R.id.checkCorRelax);
        txtValor = findViewById(R.id.txtValor);
        teste = findViewById(R.id.checkBarba);
        String bar = barba.getText().toString();
        txtValor.setText(bar);

        if (!teste.isChecked()){
            txtValor.setText("00,00");
        }
    }
}
