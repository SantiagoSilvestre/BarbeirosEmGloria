package com.babeirosemgloria.barbergloria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.helper.Preferencias;

public class Barbeiros extends AppCompatActivity {

    private Button btnCancelar;
    private Button btnConfirmar;
    private Button btnDvscuts;
    private Button btnIgor;
    private Button btnKaique;
    private Button btnSemPref;
    private TextView txtBarb;
    private int id = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barbeiros);

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
                    String barb = "O";
                    String testeBar = valString.substring(0,1);
                    if (testeBar.equals("K")) {
                        barb = valString.substring(0, 6);
                    } else if (testeBar.equals("D")){
                        barb = valString.substring(0, 6);
                    } else if (testeBar.equals("I")){
                        barb = valString.substring(0, 4);
                    } else {
                        barb = "O Barbeiro Disponível irá te atender";
                    }

                    preferencias.salvarBarbeiro(barb);
                    Intent intent = new Intent(getApplication(),DataHora.class);
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
}
