package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.adapter.AgendamentoAdapter;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.babeirosemgloria.barbergloria.model.Agendamentos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MeusAgendamentos extends AppCompatActivity {

    ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Agendamentos> agendamentos;
    DatabaseReference firebase;
    private ValueEventListener valueEventListener;
    private FirebaseAuth usuarioAutenticacao;

    @Override
    protected  void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListener);
    }

    @Override
    protected  void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_agendamentos);

        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Barbeiros em Glórias");
        setSupportActionBar( toolbar );

        listView = findViewById(R.id.lv_agendamento);

        agendamentos = new ArrayList<>();

        /*
        adapter = new ArrayAdapter(
          getApplicationContext(),
          R.layout.lista_agendamento,
          agendamentos
        );

         */

        adapter = new AgendamentoAdapter(this, agendamentos);
        listView.setAdapter( adapter );
        //Recuperar Agendamento do firebase
        Preferencias preferencias = new Preferencias(this);
        String identificarPref = preferencias.getIdentificadorTel();
        String identificadorUser = preferencias.getIdentificador();
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Agendamentos").child(identificadorUser);

        //Recuperar agendamentos
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // listar
                agendamentos.clear();
                for (DataSnapshot dados: dataSnapshot.getChildren()){

                    Agendamentos ag = dados.getValue(Agendamentos.class);
                    agendamentos.add(ag);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        firebase.addValueEventListener(valueEventListener);
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
