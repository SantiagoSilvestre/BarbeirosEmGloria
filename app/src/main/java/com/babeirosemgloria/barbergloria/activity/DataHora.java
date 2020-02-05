package com.babeirosemgloria.barbergloria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.helper.Preferencias;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DataHora extends AppCompatActivity {

    private Button btnServico;
    private Button btnBarbeiro;
    private Button btnCancelar;
    private Button btnConfirmar;

    // Variaveis para controle de data
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog DatePickerDialog;
    private Button btnData;
    private Button btnHorario;
    private SimpleDateFormat timeFormatter;
    private TimePickerDialog EntradaTimePickerDialog;
    private TextView displayDate;
    private TextView tvHorarioEntrada;
    private TextView txtValor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);

        final Preferencias preferencias = new Preferencias(DataHora.this);

        // Recuperando Elementos da view
        btnData = findViewById(R.id.btnData);
        btnHorario = findViewById(R.id.btnHorario);
        displayDate = findViewById(R.id.displayDate);
        tvHorarioEntrada = findViewById(R.id.tvHorarioEntrada);
        btnServico = findViewById(R.id.btnServico);
        btnBarbeiro = findViewById(R.id.btnBarbeiro);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnConfirmar = findViewById(R.id.btnConfimar);
        txtValor = findViewById(R.id.txtValor);

        // set o valor na view

        if(preferencias.getValor() != null ) {  txtValor.setText(preferencias.getValor()); }

        // Define a localidade sendo como Brasl
        Locale brasil = new Locale("pt", "BR");

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", brasil);
        timeFormatter = new SimpleDateFormat("HH:mm", brasil);

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateTimeField();
                DatePickerDialog.show();
            }
        });
        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempo = "entrada";
                setTimeField(tempo);
                EntradaTimePickerDialog.show();
            }
        });

        btnServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Servico.class);
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
                // Aqui vai fazer toda a lógica que recuperar todos os dados e manda para o objeto
            }
        });

    }

    public void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                displayDate.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    public void setTimeField(String tempo) {
        Calendar newCalendar = Calendar.getInstance();

        if (tempo.equals("entrada")) {
            EntradaTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Calendar newTime = Calendar.getInstance();
                    newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    newTime.set(Calendar.MINUTE, minute);
                    tvHorarioEntrada.setText(timeFormatter.format(newTime.getTime()));
                }
            }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), true);
        }

    }

}
