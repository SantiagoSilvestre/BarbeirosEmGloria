package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.babeirosemgloria.barbergloria.model.Horario;
import com.babeirosemgloria.barbergloria.model.ListaDeHorarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private TextView displayDate;
    private TextView txtValor;
    private DatabaseReference firebase;
    private ListaDeHorarios lisHoras;
    private FirebaseAuth usuarioAutenticacao;
    private Horario horario;
    private Preferencias preferencias;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);

        final Preferencias preferencias = new Preferencias(DataHora.this);
        firebase = ConfiguracaoFirebase.getFirebase();
        lisHoras = new ListaDeHorarios();

        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Barbeiros em Glórias");
        setSupportActionBar( toolbar );

        // Recuperando Elementos da view
        btnData = findViewById(R.id.btnData);
        btnHorario = findViewById(R.id.btnHorario);
        displayDate = findViewById(R.id.displayDate);
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
                Intent intent = new Intent(DataHora.this, ListaHorarios.class);
                String date = displayDate.getText().toString();
                date = date.replace("/", "-");
                preferencias.salvarData(date);
                intent.putExtra("data", preferencias.getData());
                startActivity(intent);
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
                horario = new Horario();
                horario.setData(preferencias.getData());
                horario.setDisponibilidade("Não");
                horario.setCliente(preferencias.getNome());
                horario.setHora(preferencias.getHora());
                horario.setServicos(servicosArray());
                horario.setTotal(txtValor.getText().toString());
                horario.salvar(preferencias.getBarbeiro());
                preferencias.clearPreferencias();
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
        Intent intent = new Intent(DataHora.this, Login.class);
        startActivity(intent);
        finish();
    }
    private void abrirContatosMensagens(){
        //Intent intent = new Intent(MainActivity.this, MensagemGerencia.class );
        //startActivity(intent);
    }

    private ArrayList<String> servicosArray () {

        preferencias = new Preferencias(this);

        ArrayList<String> servicos = new ArrayList<>();
        if(preferencias.getCheckBarba().equals("1")) {
            servicos.add(preferencias.getCheTXBarba());
        }
        if(preferencias.getCheckCorte().equals("1")) {
            servicos.add(preferencias.getCheTXCorte());
        }
        if(preferencias.getCheckCorBar().equals("1")) {
            servicos.add(preferencias.getCheTXCorBar());
        }



        return servicos;
    }
}
