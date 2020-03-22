package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Button btnServicos;
    private Button btnBarbeiro;
    private Button btnData;
    private Button btnMinhaAgenda;
    private Button btnpromocoes;
    private Button btnSugestoes;
    private FirebaseAuth usuarioAutenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnServicos = findViewById(R.id.btnServico);
        btnBarbeiro = findViewById(R.id.btnBarbeiro);
        btnData = findViewById(R.id.btnData);
        btnMinhaAgenda = findViewById(R.id.btnMinhaAgenda);
        btnpromocoes = findViewById(R.id.btnPromocoes);
        btnSugestoes = findViewById(R.id.btnSugestoes);

        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Barbeiros em Glórias");
        setSupportActionBar( toolbar );


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
                Intent intent = new Intent(getApplication(), ListaHorarios.class);
                startActivity(intent);
            }
        });

        btnMinhaAgenda.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v) {
               Intent intent = new Intent(getBaseContext(), MeusAgendamentos.class);
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
        btnSugestoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sugestoes.class);
                startActivity(intent);
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
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void deslogarUsuario() {
        usuarioAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        usuarioAutenticacao.signOut();
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
