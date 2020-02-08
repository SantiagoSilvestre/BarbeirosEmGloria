package com.babeirosemgloria.barbergloria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.le.AdvertisingSetParameters;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.babeirosemgloria.barbergloria.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListaHorarios extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> horarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horarios);

        horarios = new ArrayList<>();
        horarios.add("Hora 1");
        horarios.add("Hora 2");
        horarios.add("Hora 3");

        listView = findViewById(R.id.lv_horarios);

        adapter = new ArrayAdapter(getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    horarios
                );

        listView.setAdapter(adapter);

    }
}
