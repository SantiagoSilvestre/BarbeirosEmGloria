package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.bluetooth.le.AdvertisingSetParameters;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private TextView hora1;
    private TextView hora2;
    private TextView hora3;
    private TextView hora4;
    private TextView hora5;
    private TextView hora6;
    private TextView hora7;
    private TextView hora8;
    private TextView hora9;
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
                .child("Igor")
                .child("17-02-2020")
                .child("Agendamento" )
                .child(hora);
        eventListener = new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() ) {

                    horario = dataSnapshot.getValue( Horario.class );
                    if(horario.getDisponibilidade().equals("Não")) {
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

    public void agendar1(View view) {

        TextView txtHora = findViewById(R.id.tx10);
        String hora = txtHora.getText().toString();
        horario.setCliente(preferencias.getNome());
        horario.setDisponibilidade("Não");
        horario.setHora(hora);
        horario.setData(dataEscolhida);
        horario.salvar(preferencias.getBarbeiro());
        setDisponiblidade();

    }
    public void agendar2(View view) {

        TextView txtHora = findViewById(R.id.tx11);
        String hora = txtHora.getText().toString();
        horario.setCliente(preferencias.getNome());
        horario.setDisponibilidade("Não");
        horario.setHora(hora);
        horario.setData(dataEscolhida);
        horario.salvar(preferencias.getBarbeiro());
        verificaDisponibilidade(hora2,hora);

    }
    public void agendar3(View view) {

        TextView txtHora = findViewById(R.id.tx12);
        String hora = txtHora.getText().toString();
        horario.setCliente(preferencias.getNome());
        horario.setDisponibilidade("Não");
        horario.setHora(hora);
        horario.setData(dataEscolhida);
        horario.salvar(preferencias.getBarbeiro());
        verificaDisponibilidade(hora3,hora);

    }
    public void agendar4(View view) {

        TextView txtHora = findViewById(R.id.tx13);
        String hora = txtHora.getText().toString();
        horario.setCliente(preferencias.getNome());
        horario.setDisponibilidade("Não");
        horario.setHora(hora);
        horario.setData(dataEscolhida);
        horario.salvar(preferencias.getBarbeiro());
        verificaDisponibilidade(hora4,hora);

    }
    public void agendar5(View view) {

        TextView txtHora = findViewById(R.id.tx14);
        String hora = txtHora.getText().toString();
        horario.setCliente(preferencias.getNome());
        horario.setDisponibilidade("Não");
        horario.setHora(hora);
        horario.setData(dataEscolhida);
        horario.salvar(preferencias.getBarbeiro());
        verificaDisponibilidade(hora5,hora);

    }
    public void agendar6(View view) {

        TextView txtHora = findViewById(R.id.tx15);
        String hora = txtHora.getText().toString();
        horario.setCliente(preferencias.getNome());
        horario.setDisponibilidade("Não");
        horario.setHora(hora);
        horario.setData(dataEscolhida);
        horario.salvar(preferencias.getBarbeiro());
        verificaDisponibilidade(hora6,hora);

    }
    public void agendar7(View view) {

        TextView txtHora = findViewById(R.id.tx16);
        String hora = txtHora.getText().toString();
        horario.setCliente(preferencias.getNome());
        horario.setDisponibilidade("Não");
        horario.setHora(hora);
        horario.setData(dataEscolhida);
        horario.salvar(preferencias.getBarbeiro());
        verificaDisponibilidade(hora7,hora);

    }
    public void agendar8(View view) {

        TextView txtHora = findViewById(R.id.tx17);
        String hora = txtHora.getText().toString();
        horario.setCliente(preferencias.getNome());
        horario.setDisponibilidade("Não");
        horario.setHora(hora);
        horario.setData(dataEscolhida);
        horario.salvar(preferencias.getBarbeiro());
        verificaDisponibilidade(hora8,hora);

    }
    public void agendar9(View view) {

        TextView txtHora = findViewById(R.id.tx18);
        String hora = txtHora.getText().toString();
        horario.setCliente(preferencias.getNome());
        horario.setDisponibilidade("Não");
        horario.setHora(hora);
        horario.setData(dataEscolhida);
        horario.salvar(preferencias.getBarbeiro());
        verificaDisponibilidade(hora9,hora);

    }
}

