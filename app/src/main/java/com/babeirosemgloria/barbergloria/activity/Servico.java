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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Servico extends AppCompatActivity {

    private Button btnCancelar;
    private Button btnConfirmar;
    private CheckBox barba;
    private CheckBox corte;
    private CheckBox prog;
    private CheckBox relaxamento;
    private CheckBox luzes;
    private CheckBox corInfantil;
    private CheckBox pezinho;
    private CheckBox limpeza;
    private CheckBox corRela;
    private CheckBox sombrancelha;
    private TextView txtValor;
    private TextView textoBarba;
    private TextView textoCorte;
    private TextView textoRelaxamento;
    private TextView textoProgressiva;
    private TextView textoSombrancelha;
    private TextView textoLimpeza;
    private TextView textoPezinho;
    private TextView textoInfantil;
    private TextView textoLuzes;
    private Double valorPref = 0.0;
    private double valor = valorPref;
    Preferencias preferencias;
    private FirebaseAuth usuarioAutenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);
        preferencias = new Preferencias(Servico.this);
        encontrarElementos();
        verificaCheckBox();


        if(preferencias.getValor() != null ) {  txtValor.setText(preferencias.getValor()); }

        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Serviços Oferecidos");
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
                    Intent intent = new Intent(getApplication(), Barbeiros.class);
                    startActivity(intent);
                } else {
                    android.app.AlertDialog.Builder alert = new AlertDialog.Builder(Servico.this);
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
        if(preferencias.getCheckProg() == "1") { prog.setChecked(true); }
        if(preferencias.getCheckRel() == "1") { relaxamento.setChecked(true); }
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
        prog = findViewById(R.id.checkProg);
        relaxamento = findViewById(R.id.checkRelax);
        corInfantil = findViewById(R.id.checkcorteinfantil);
        luzes = findViewById(R.id.checkLuzes);
        limpeza = findViewById(R.id.checkLimpeza);
        pezinho = findViewById(R.id.checkpezinho);
        sombrancelha = findViewById(R.id.checkSobrancelha);
        txtValor = findViewById(R.id.txtValor);
        btnCancelar = findViewById(R.id.btnCancelar);
        txtValor = findViewById(R.id.txtValor);
        textoBarba = findViewById(R.id.textoBarba);
        textoCorte = findViewById(R.id.textoCorte);
        textoProgressiva = findViewById(R.id.textoProgessiva);
        textoRelaxamento = findViewById(R.id.textoRelaxamento);
        textoSombrancelha = findViewById(R.id.textoSombrancelha);
        textoPezinho = findViewById(R.id.textoPezinho);
        textoInfantil = findViewById(R.id.textoInfantil);
        textoLuzes = findViewById(R.id.textoLuzes);
        textoLimpeza = findViewById(R.id.textoLimpeza);
        btnConfirmar = findViewById(R.id.btnConfimar);
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
            case R.id.item_mensagens:
                abrirContatosMensagens();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void deslogarUsuario() {
        usuarioAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        usuarioAutenticacao.signOut();
        Intent intent = new Intent(Servico.this, Login.class);
        startActivity(intent);
        finish();
    }
    private void abrirContatosMensagens(){
        //Intent intent = new Intent(MainActivity.this, MensagemGerencia.class );
        //startActivity(intent);
    }

    public void recuperaValorBarba(View view) {

        preferencias = new Preferencias(this);

        String valBarba = barba.getText().toString();

        String vBarba = valBarba.replace(",", ".");
        double valorBarba = Double.parseDouble(vBarba);


        if (barba.isChecked()) {
            preferencias.salvarCkBarba("1", textoBarba.getText().toString());
            valor = valorBarba + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!barba.isChecked()){
            preferencias.salvarCkBarba("0", textoBarba.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorBarba;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

    }
    public void recuperaValorCorte(View view) {

        preferencias = new Preferencias(this);

        String valCorte = corte.getText().toString();

        String vCorte = valCorte.replace(",", ".");
        double valorCorte = Double.parseDouble(vCorte);


        if (corte.isChecked()) {
            preferencias.salvarCkCorte("1", textoCorte.getText().toString());
            valor = valorCorte + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!corte.isChecked()){
            preferencias.salvarCkCorte("0", textoCorte.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorCorte;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

    }
    public void recuperaValorRelaxamento(View view) {

        preferencias = new Preferencias(this);

        String valRelaxamento = relaxamento.getText().toString();

        String vRelamento = valRelaxamento.replace(",", ".");
        double valorRelamento = Double.parseDouble(vRelamento);


        if (relaxamento.isChecked()) {
            preferencias.salvarCkRel("1", textoRelaxamento.getText().toString());
            valor = valorRelamento + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!relaxamento.isChecked()){
            preferencias.salvarCkRel("0", textoRelaxamento.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorRelamento;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

    }
    public void recuperaValorProg(View view) {

        preferencias = new Preferencias(this);

        String valprog = prog.getText().toString();

        String vProg = valprog.replace(",", ".");
        double valorProg = Double.parseDouble(vProg);


        if (prog.isChecked()) {
            preferencias.salvarCkProg("1", textoProgressiva.getText().toString());
            valor = valorProg + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!prog.isChecked()){
            preferencias.salvarCkProg("0", textoProgressiva.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorProg;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

    }
    public void recuperaValorSomb(View view) {

        preferencias = new Preferencias(this);

        String valSomb = sombrancelha.getText().toString();

        String vSomb = valSomb.replace(",", ".");
        double valorSomb = Double.parseDouble(vSomb);


        if (sombrancelha.isChecked()) {
            preferencias.salvaCkSombrancelha("1", textoSombrancelha.getText().toString());
            valor = valorSomb + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!sombrancelha.isChecked()){
            preferencias.salvaCkSombrancelha("0", textoSombrancelha.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorSomb;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

    }
    public void recuperaValorLimp(View view) {

        preferencias = new Preferencias(this);

        String valLimp = limpeza.getText().toString();

        String vLimp = valLimp.replace(",", ".");
        double valorLimp = Double.parseDouble(vLimp);


        if (limpeza.isChecked()) {
            preferencias.salvarCkLimpeza("1", textoLimpeza.getText().toString());
            valor = valorLimp + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!limpeza.isChecked()){
            preferencias.salvarCkLimpeza("0", textoLimpeza.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorLimp;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

    }
    public void recuperaValorPezinho(View view) {

        preferencias = new Preferencias(this);

        String valPezinho = pezinho.getText().toString();

        String vPezinho = valPezinho.replace(",", ".");
        double valorPezinho = Double.parseDouble(vPezinho);


        if (pezinho.isChecked()) {
            preferencias.salvarCkPezinho("1", textoPezinho.getText().toString());
            valor = valorPezinho + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!pezinho.isChecked()){
            preferencias.salvarCkPezinho("0", textoPezinho.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorPezinho;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

    }
    public void recuperaValorInfantil(View view) {

        preferencias = new Preferencias(this);

        String valInfantil = corInfantil.getText().toString();

        String vInfantil = valInfantil.replace(",", ".");
        double valorInfantil = Double.parseDouble(vInfantil);


        if (corInfantil.isChecked()) {
            preferencias.salvarCkCorteInfantil("1", textoInfantil.getText().toString());
            valor = valorInfantil + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!corInfantil.isChecked()){
            preferencias.salvarCkCorteInfantil("0", textoInfantil.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorInfantil;
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

    }
    public void recuperaValorLuzes(View view) {

        preferencias = new Preferencias(this);

        String valLuzes = luzes.getText().toString();

        String vLuzes = valLuzes.replace(",", ".");
        double valorLuzes = Double.parseDouble(vLuzes);


        if (luzes.isChecked()) {
            preferencias.salvarCkLuzes("1", textoLuzes.getText().toString());
            valor = valorLuzes + recuperaValPreferencia(preferencias.getValor());
            txtValor.setText(transformaString(valor));
            preferencias.salvarValor(txtValor.getText().toString());
        }

        if(!luzes.isChecked()){
            preferencias.salvarCkLuzes("0", textoLuzes.getText().toString());
            valor = recuperaValPreferencia(preferencias.getValor());
            valor -= valorLuzes;
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
