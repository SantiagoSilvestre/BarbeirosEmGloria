package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.le.AdvertisingSetParameters;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.babeirosemgloria.barbergloria.model.Horario;
import com.babeirosemgloria.barbergloria.model.ListaDeHorarios;
import com.babeirosemgloria.barbergloria.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListaHorarios extends AppCompatActivity {

    private Button btnConfirmar;
    private String dataEscolhida;
    private DatabaseReference firebase;
    private String disp;
    private TextView hora1;
    private TextView hora2;
    private TextView hora3;
    private TextView hora4;
    private TextView hora5;
    private TextView hora6;
    private TextView hora7;
    private TextView hora8;
    private TextView hora9;
    private TextView tx10;
    private TextView tx11;
    private TextView tx12;
    private TextView tx13;
    private TextView tx14;
    private TextView tx15;
    private TextView tx16;
    private TextView tx17;
    private TextView tx18;
    Preferencias preferencias;
    private ValueEventListener eventListener;
    Horario horario = new Horario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horarios);

        preferencias = new Preferencias(ListaHorarios.this);
        recuperaValores();
        setDisponiblidade();

        Intent intent = getIntent();
        dataEscolhida = intent.getStringExtra("data");
    }

    public void backMain(View view) {

        Intent intent = new Intent(this,DataHora.class);
        startActivity(intent);

    }

    public void verificaDisponibilidade(final TextView txt, String hora) {
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Barbeiro")
                .child( "02-11-2020" )
                .child("Agendamento" )
                .child(hora);
        eventListener = new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() ) {

                    horario = dataSnapshot.getValue( Horario.class );
                    if(horario.getDisponibilidade().equals("não")) {
                        preferencias.salvarDisp("");
                        txt.setText("Não Disponível");
                    }

                }else {
                    txt.setText("Diponível");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        firebase.addListenerForSingleValueEvent(eventListener);
    }

    public void recuperaValores() {
        hora1 = findViewById(R.id.txtHora1);
        hora2 = findViewById(R.id.txtHora2);
        hora3 = findViewById(R.id.txtHora3);
        hora4 = findViewById(R.id.txtHora4);
        hora5 = findViewById(R.id.txtHora5);
        hora6 = findViewById(R.id.txtHora6);
        hora7 = findViewById(R.id.txtHora7);
        hora8 = findViewById(R.id.txtHora8);
        hora9 = findViewById(R.id.txtHora9);
        btnConfirmar = findViewById(R.id.btnConfimar);
        tx10 = findViewById(R.id.tx10);
        tx11 = findViewById(R.id.tx11);
        tx12 = findViewById(R.id.tx12);
        tx13 = findViewById(R.id.tx13);
        tx14 = findViewById(R.id.tx14);
        tx15 = findViewById(R.id.tx15);
        tx16 = findViewById(R.id.tx16);
        tx17 = findViewById(R.id.tx17);
        tx18 = findViewById(R.id.tx18);
    }


    public void setDisponiblidade(){



        verificaDisponibilidade(hora1,"10:00");
        verificaDisponibilidade(hora2,"11:00");
        verificaDisponibilidade(hora3,"12:00");
        verificaDisponibilidade(hora4,"13:00");
        verificaDisponibilidade(hora5,"14:00");
        verificaDisponibilidade(hora6,"15:00");
        verificaDisponibilidade(hora7,"16:00");
        verificaDisponibilidade(hora8,"17:00");
        verificaDisponibilidade(hora9,"18:00");


    }

}

