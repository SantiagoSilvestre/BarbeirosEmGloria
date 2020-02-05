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
    private CheckBox sombrancelha;
    private TextView txtValor;
    Preferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);
        encontrarElementos();
        preferencias = new Preferencias(Servico.this);

        if(preferencias.getValor() != null ) {  txtValor.setText(preferencias.getValor()); }

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
        preferencias = new Preferencias(Servico.this);

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
        String valSombrancelha = sombrancelha.getText().toString();

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
        String vSombrancelha = valSombrancelha.replace(",", ".");


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
        double valorSombrancelha = Double.parseDouble(vSombrancelha);

        double valor = 0;


        // Aqui faz a somatório dos valores escolhidos
        if (barba.isChecked()) {
            preferencias.salvarCkBarba("1");
            valor += valorBarba;
            txtValor.setText(transformaString(valor));
        } else {
            preferencias.salvarCkBarba("0");
            if (valor == 0) {
                txtValor.setText("00,00");
            } else {
                valor -= valorBarba;
                txtValor.setText(transformaString(valor));
            }
        }
        if (corte.isChecked()) {
            preferencias.salvarCkCorte("1");
            valor += valorCorte;
            txtValor.setText(transformaString(valor));
        } else {
            preferencias.salvarCkCorte("0");
        }
        if (corBarb.isChecked()) {
            preferencias.salvarCkCorBar("1");
            valor += valorCorBarba;
            txtValor.setText(transformaString(valor));
        } else {
            preferencias.salvarCkCorBar("0");
        }

        if (corProg.isChecked()) {
            preferencias.salvarCkCorProg("1");
            valor += valorCorProg;
            txtValor.setText(transformaString(valor));
        } else {
            preferencias.salvarCkCorProg("0");
        }

        if (corRela.isChecked()){
            preferencias.salvarCkCorRel("1");
            valor += valorCorRel;
            txtValor.setText(transformaString(valor));
        } else {
            preferencias.salvarCkCorRel("0");
        }

        if(corInfantil.isChecked()){
            preferencias.salvarCkCorteInfantil("1");
            valor += valorCorInfantil;
            txtValor.setText(transformaString(valor));
        } else {
            preferencias.salvarCkCorteInfantil("0");
        }

        if(luzes.isChecked()){
            preferencias.salvarCkLuzes("1");
            valor += valorLuzes;
            txtValor.setText(transformaString(valor));
        } else {
            preferencias.salvarCkLuzes("0");
        }

        if(limpeza.isChecked()){
            preferencias.salvarCkLimpeza("1");
            valor += valorLimpeza;
            txtValor.setText(transformaString(valor));
        } else {
            preferencias.salvarCkLimpeza("0");
        }

        if(pezinho.isChecked()){
            preferencias.salvarCkPezinho("1");
            valor += valorPezinho;
            txtValor.setText(transformaString(valor));
        } else {
            preferencias.salvarCkPezinho("0");
        }

        if(sombrancelha.isChecked()) {
            preferencias.salvaCkSombrancelha("1");
            valor += valorSombrancelha;
            txtValor.setText(transformaString(valor));
        } else {
            preferencias.salvaCkSombrancelha("0");
        }


    }

    public String transformaString(double valor) {

        String stringValor = String.valueOf(valor);
        String variavelFormat = stringValor.replace(".", ",");
        variavelFormat += "0";

        return variavelFormat;
    }

    public void verificaCheckBox(){
        preferencias = new Preferencias(Servico.this);
        if(preferencias.getCheckCorte() == "1") { corte.setChecked(true); }
        if(preferencias.getCheckBarba() == "1") { barba.setChecked(true); }
        if(preferencias.getCheckCorBar() == "1") { corBarb.setChecked(true); }
        if(preferencias.getCheckCorProg() == "1") { corProg.setChecked(true); }
        if(preferencias.getCheckCorRel() == "1") { corRela.setChecked(true); }
        if(preferencias.getCheckCorInfantil() == "1") { corInfantil.setChecked(true); }
        if(preferencias.getCheckLimpeza() == "1") { limpeza.setChecked(true); }
        if(preferencias.getCheckLuzes() == "1") { luzes.setChecked(true); }
        if(preferencias.getCheckPezinho() == "1") { pezinho.setChecked(true); }
        if(preferencias.getCheckSombrancelha() == "1") { sombrancelha.setChecked(true); }
    }

    public void encontrarElementos() {
        preferencias = new Preferencias(Servico.this);
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
        sombrancelha = findViewById(R.id.checkSobrancelha);
        txtValor = findViewById(R.id.txtValor);
        btnCancelar = findViewById(R.id.btnCancelar);
        txtValor = findViewById(R.id.txtValor);
        btnConfirmar = findViewById(R.id.btnConfimar);
        verificaCheckBox();
    }
}
