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
import android.widget.TextView;
import android.widget.Toast;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.google.firebase.auth.FirebaseAuth;

public class Barbeiros extends AppCompatActivity {

    private Button btnCancelar;
    private Button btnConfirmar;
    private Button btnDvscuts;
    private Button btnIgor;
    private Button btnKaique;
    private Button btnSemPref;
    private TextView txtBarb;
    private int id = 0 ;
    private FirebaseAuth usuarioAutenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barbeiros);
        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Barbeiros em Glórias");
        setSupportActionBar( toolbar );

        final Preferencias preferencias = new Preferencias(Barbeiros.this);

        btnCancelar = findViewById(R.id.btnCancelar);
        btnConfirmar = findViewById(R.id.btnConfimar);
        btnDvscuts = findViewById(R.id.btnDvscuts);
        btnIgor = findViewById(R.id.btnIgor);
        btnKaique = findViewById(R.id.btnKaique);
        btnSemPref = findViewById(R.id.btnSemPref);
        txtBarb = findViewById(R.id.txtBarb);
        if(preferencias.getBarbeiro() != null ) {txtBarb.setText(preferencias.getBarbeiro());}

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
                txtBarb.setText("Danilo selecionado");
            }
        });
        btnIgor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 2;
                txtBarb.setText("Igor selecionado");

            }
        });
        btnKaique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 3;
                txtBarb.setText("Kaique selecionado");
            }
        });
        btnSemPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 4;
                txtBarb.setText("O barbeiro disponível irá te atender");
            }
        });



        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String verificaMudança = "Selecione o Barbeiro";
                TextView valueTxtBarb = findViewById(R.id.txtBarb);
                String valString = valueTxtBarb.getText().toString();
                if (! verificaMudança.equals(valString)){
                    String barb;
                    String cod;
                    String testeBar = valString.substring(0,1);
                    if (testeBar.equals("K")) {
                        cod = "3";
                        barb = valString.substring(0, 6);
                    } else if (testeBar.equals("D")){
                        cod = "1";
                        barb = valString.substring(0, 6);
                    } else if (testeBar.equals("I")){
                        cod = "2";
                        barb = valString.substring(0, 4);
                    } else {
                        cod = "4";
                        barb = "O Barbeiro Disponível irá te atender";
                    }

                    preferencias.salvarBarbeiro(barb);
                    preferencias.salvarCod(cod);
                    Intent intent = new Intent(getApplication(),ListaHorarios.class);
                    startActivity(intent);
                } else{
                    android.app.AlertDialog.Builder alert = new AlertDialog.Builder(Barbeiros.this);
                    alert.setTitle("Atenção");
                    alert.setMessage("Nenhum Barbeiro selecionado");
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
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
    private void abrirContatosMensagens(){
        //Intent intent = new Intent(MainActivity.this, MensagemGerencia.class );
        //startActivity(intent);
    }
}
