package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.google.firebase.auth.FirebaseAuth;

public class Promocoes extends AppCompatActivity {
    private Button btnCancelar;
    private Button btnConfirmar;
    private CheckBox corBarb;
    private CheckBox corProg;
    private CheckBox corRela;
    private TextView txtValor;
    private TextView textoCorteBarba;
    private TextView textoCorteRelaxamento;
    private TextView textoCorteProg;
    private Double valorPref = 0.0;
    private double valor = valorPref;
    Preferencias preferencias ;
    private FirebaseAuth usuarioAutenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocoes);

        preferencias = new Preferencias(this);

        encontrarElementos();
        //Verificando valores de preferencias
        if(preferencias.getValor() != null ) {txtValor.setText(preferencias.getValor());}
        if((preferencias.getCheckCorBar() != null) && (preferencias.getCheckCorBar().equals("1"))){corBarb.setChecked(true);}
        if((preferencias.getCheckCorRel() != null) && (preferencias.getCheckCorRel().equals("1"))){corRela.setChecked(true);}
        if((preferencias.getCheckCorProg() != null) && (preferencias.getCheckCorProg().equals("1"))){corProg.setChecked(true);}
        //Fim da verificação

        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Promoções");
        setSupportActionBar( toolbar );

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
                String d = txtValor.getText().toString();
                d = d.replace(",", "");
                int vl = Integer.parseInt(d);
                if (vl > 0) {
                    preferencias.salvarValor(txtValor.getText().toString());
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                } else {
                    android.app.AlertDialog.Builder alert = new AlertDialog.Builder(Promocoes.this);
                    alert.setTitle("Atenção");
                    alert.setMessage("Nenhum Serviço selecionado");
                    alert.setCancelable(false);
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert.show();
                }

            }
        });
    }

    public void recuperaValorProg(View view) {

        preferencias = new Preferencias(this);

        String valCorProg = corProg.getText().toString();

        String vCorteProg = valCorProg.replace(",", ".");
        double valorCorProg = Double.parseDouble(vCorteProg);


        if (corProg.isChecked()) {
            preferencias.salvarCkCorProg("1", textoCorteProg.getText().toString());
            valor = valorCorProg + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!corProg.isChecked()){
            preferencias.salvarCkCorProg("0", textoCorteProg.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorCorProg;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

    }

    public void recuperaValorRel(View view) {

        preferencias = new Preferencias(this);

        String valCorRel = corRela.getText().toString();

        String vCorteRel = valCorRel.replace(",", ".");
        double valorCorRel = Double.parseDouble(vCorteRel);


        if (corRela.isChecked()) {
            preferencias.salvarCkCorRel("1", textoCorteRelaxamento.getText().toString());
            valor = valorCorRel + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!corRela.isChecked()){
            preferencias.salvarCkCorRel("0", textoCorteProg.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorCorRel;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

    }

    public String transformaString(double valor) {

        String stringValor = String.valueOf(valor);
        String variavelFormat = stringValor.replace(".", ",");
        variavelFormat += "0";

        return variavelFormat;
    }

    public void verificaCheckBox(){
        preferencias = new Preferencias(Promocoes.this);
        if(preferencias.getCheckCorBar() == "1") { corBarb.setChecked(true); }
        if(preferencias.getCheckCorProg() == "1") { corProg.setChecked(true); }
        if(preferencias.getCheckCorRel() == "1") { corRela.setChecked(true); }
    }

    public void encontrarElementos() {
        preferencias = new Preferencias(Promocoes.this);
        // Aqui será feita a lógica para recuperar os serviços
        corProg = findViewById(R.id.checkCorProg);
        corRela = findViewById(R.id.checkCorRelax);
        corBarb = findViewById(R.id.checkCorBar);
        textoCorteProg = findViewById(R.id.textoCorteProgessiva);
        textoCorteRelaxamento = findViewById(R.id.textoCorteRela);
        textoCorteBarba = findViewById(R.id.textoCorteBarba);
        btnConfirmar = findViewById(R.id.btnConfimar);
        txtValor = findViewById(R.id.txtValor);
        btnCancelar = findViewById(R.id.btnCancelar);
        verificaCheckBox();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch ( item.getItemId() ) {
            case R.id.item_sair:
                deslogarUsuario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void deslogarUsuario() {
        usuarioAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        usuarioAutenticacao.signOut();
        Intent intent = new Intent(Promocoes.this, Login.class);
        startActivity(intent);
        finish();
    }

    public void recuperaValorCorBar(View view) {

        preferencias = new Preferencias(this);

        String valCorBar = corBarb.getText().toString();
        // Aqui substitui as vírgulas pelo ponto
        String vCorteBar = valCorBar.replace(",", ".");
        // aqui pega os valores e transforma em double
        double valorCorBarba = Double.parseDouble(vCorteBar);


        // Aqui faz a somatório dos valores escolhidos
        if (corBarb.isChecked()) {
            preferencias.salvarCkCorBar("1", textoCorteBarba.getText().toString());
            valor = valorCorBarba + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }
        if (!corBarb.isChecked()) {
            preferencias.salvarCkCorBar("0", textoCorteBarba.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorCorBarba;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }
    }

    private Double recuperaValPreferencia(String val){


        val = txtValor.getText().toString();
        val = val.replace(",", ".");

        valorPref = Double.parseDouble(val);
        return valorPref;
    }

}
