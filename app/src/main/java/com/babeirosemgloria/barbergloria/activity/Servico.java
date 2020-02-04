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
import com.babeirosemgloria.barbergloria.helper.Preferencias;

import java.util.ArrayList;

public class Servico extends AppCompatActivity {

    private Button btnCancelar;
    private Button btnConfirmar;
    private CheckBox barba;
    private CheckBox corte;
    private CheckBox corBarb;
    private CheckBox corProg;
    private CheckBox luzes;
    private CheckBox corInfantil;
    private CheckBox pezinho;
    private CheckBox limpeza;
    private CheckBox corRela;
    private TextView txtValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);

        btnCancelar = findViewById(R.id.btnCancelar);
        txtValor = findViewById(R.id.txtValor);
        btnConfirmar = findViewById(R.id.btnConfimar);

        final Preferencias preferencias = new Preferencias(Servico.this);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencias.salvarValor(txtValor.getText().toString());
                Intent intent = new Intent(getApplication(), DataHora.class);
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
        corRela = findViewById(R.id.checkCorRelax);
        corInfantil = findViewById(R.id.checkcorteinfantil);
        luzes = findViewById(R.id.checkLuzes);
        limpeza = findViewById(R.id.checkLimpeza);
        pezinho = findViewById(R.id.checkpezinho);
        txtValor = findViewById(R.id.txtValor);


        // Aqui recebe as strings do activity
        String valBarba = barba.getText().toString();
        String valCorte = corte.getText().toString();
        String valCorBar = corBarb.getText().toString();
        String valCorProg = corProg.getText().toString();
        String valCorRel = corRela.getText().toString();
        String valCorInfantil = corInfantil.getText().toString();
        String valLuzes = luzes.getText().toString();
        String valLimpesa = limpeza.getText().toString();
        String valPezinho = pezinho.getText().toString();

        // Aqui substitui as vírgulas pelo ponto
        String vBarba = valBarba.replace(",", ".");
        String vCorte = valCorte.replace(",", ".");
        String vCorteBar = valCorBar.replace(",", ".");
        String vCorteProg = valCorProg.replace(",", ".");
        String vCorterel = valCorRel.replace(",", ".");
        String vCorIntantil = valCorInfantil.replace(",", ".");
        String vLuzes = valLuzes.replace(",", ".");
        String vLimpeza = valLimpesa.replace(",", ".");
        String vPezinho = valPezinho.replace(",", ".");


        // aqui pega os valores e transforma em double
        Double valorBarba = Double.parseDouble(vBarba);
        double valorCorte = Double.parseDouble(vCorte);
        double valorCorBarba = Double.parseDouble(vCorteBar);
        double valorCorProg = Double.parseDouble(vCorteProg);
        double valorCorRel = Double.parseDouble(vCorterel);
        double valorCorInfantil = Double.parseDouble(vCorIntantil);
        double valorLuzes = Double.parseDouble(vLuzes);
        double valorLimpeza = Double.parseDouble(vLimpeza);
        double valorPezinho = Double.parseDouble(vPezinho);

        double valor = 0;


        // Aqui faz a somatório dos valores escolhidos
        if (barba.isChecked()) {
            valor += valorBarba;
            txtValor.setText(transformaString(valor));
        } else {
            if (valor == 0) {
                txtValor.setText("00,00");
            } else {
                valor -= valorBarba;
                txtValor.setText(transformaString(valor));
            }
        }
        if (corte.isChecked()) {
            valor += valorCorte;
            txtValor.setText(transformaString(valor));
        }
        if (corBarb.isChecked()) {
            valor += valorCorBarba;
            txtValor.setText(transformaString(valor));
        }
        if (corProg.isChecked()) {
            valor += valorCorProg;
            txtValor.setText(transformaString(valor));
        }
        if (corRela.isChecked()){
            valor += valorCorRel;
            txtValor.setText(transformaString(valor));
        }
        if(corInfantil.isChecked()){
            valor += valorCorInfantil;
            txtValor.setText(transformaString(valor));
        }
        if(luzes.isChecked()){
            valor += valorLuzes;
            txtValor.setText(transformaString(valor));
        }
        if(limpeza.isChecked()){
            valor += valorLimpeza;
            txtValor.setText(transformaString(valor));
        }
        if(pezinho.isChecked()){
            valor += valorPezinho;
            txtValor.setText(transformaString(valor));
        }


    }

    public String transformaString(double valor) {

        String stringValor = String.valueOf(valor);
        String variavelFormat = stringValor.replace(".", ",");
        variavelFormat += "0";

        return variavelFormat;
    }
}
