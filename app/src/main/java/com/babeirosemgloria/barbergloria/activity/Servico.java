package com.babeirosemgloria.barbergloria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        // Aqui recebe as strings do activity
        String valBarba = barba.getText().toString();
        String valCorte = corte.getText().toString();
        String valCorBar = corBarb.getText().toString();
        String valCorProg = corProg.getText().toString();
        String valCorRel = corRela.getText().toString();
        String valHidratacao = hidratacao.getText().toString();

        // Aqui substitui as vírgulas pelo ponto
        String vBarba = valBarba.replace(",", ".");
        String vCorte = valCorte.replace(",", ".");
        String vCorteBar = valCorBar.replace(",", ".");
        String vCorteProg = valCorProg.replace(",", ".");
        String vCorterel = valCorRel.replace(",", ".");
        String vHidratacoa = valHidratacao.replace(",", ".");


        // aqui pega os valores e transforma em double
        Double valorBarba = Double.parseDouble(vBarba);
        double valorCorte = Double.parseDouble(vCorte);
        double valorCorBarba = Double.parseDouble(vCorteBar);
        double valorCorProg = Double.parseDouble(vCorteProg);
        double valorCorRel = Double.parseDouble(vCorterel);
        double valorHidratacao = Double.parseDouble(vHidratacoa);

        double valor = 0;


        if (barba.isChecked()) {
            valor += valorBarba;

            String retornoActivity = transformaString(valor);
            txtValor.setText(retornoActivity);
        } else {
            if (valor == 0) {
                txtValor.setText("00,00");
            } else {
                valor -= valorBarba;

                String retornoActivity = transformaString(valor);
                txtValor.setText(retornoActivity);
            }
        }
        if (corte.isChecked()) {
            valor += valorCorte;

            String retornoActivity = transformaString(valor);
            txtValor.setText(retornoActivity);
        }
        if (corBarb.isChecked()) {
            valor += valorCorBarba;

            String retornoActivity = transformaString(valor);
            txtValor.setText(retornoActivity);
        }
        if (corProg.isChecked()) {
            valor += valorCorProg;

            String retornoActivity = transformaString(valor);
            txtValor.setText(retornoActivity);
        }
        if (corRela.isChecked()){
            valor += valorCorRel;

            String retornoActivity = transformaString(valor);
            txtValor.setText(retornoActivity);
        }
        if (hidratacao.isChecked()) {
            valor += valorHidratacao;

            String retornoActivity = transformaString(valor);
            txtValor.setText(retornoActivity);
        }


    }

    public String transformaString(double valor) {

        String stringValor = String.valueOf(valor);
        String variavelFormat = stringValor.replace(".", ",");
        variavelFormat += "0";


        return variavelFormat;
    }
}
