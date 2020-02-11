package com.babeirosemgloria.barbergloria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.le.AdvertisingSetParameters;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.model.Horario;
import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListaHorarios extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> horarios;
    private Button btnConfirmar;
    private Button btnCancelar;
    private String dataEscolhida;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horarios);

        recuparDados();

        horarios = new ArrayList<>();
        horarios.add("");

        listView = findViewById(R.id.lv_horarios);

        adapter = new ArrayAdapter(getApplicationContext(),
                    R.layout.list_horarios,
                    horarios
                );

        listView.setAdapter(adapter);

        Intent intent = getIntent();
        dataEscolhida = intent.getStringExtra("data");
    }

    public void recuparDados() {
        btnCancelar = findViewById(R.id.btnCancelar);
        btnConfirmar = findViewById(R.id.btnConfimar);
    }

}
