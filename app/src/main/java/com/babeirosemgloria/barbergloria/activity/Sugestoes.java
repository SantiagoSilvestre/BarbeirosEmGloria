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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Base64Custom;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.babeirosemgloria.barbergloria.model.SugestoesReclamacoes;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Sugestoes extends AppCompatActivity {

    private FirebaseAuth usuarioAutenticacao;
    private Button btnEnviar;
    private EditText editSugestao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestoes);

        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Barbeiros em Glórias");
        setSupportActionBar( toolbar );

        btnEnviar = findViewById(R.id.btnEnviar);
        editSugestao = findViewById(R.id.editSugestao);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sugestao = editSugestao.getText().toString();
                if(sugestao.equals("")) {
                    Toast.makeText(getApplication(), "Escreva um texto antes de enviar!", Toast.LENGTH_SHORT).show();
                }else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
                    Date data = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(data);
                    Date data_atual = cal.getTime();
                    String data_completa = dateFormat.format(data_atual);

                    Preferencias preferencias = new Preferencias(Sugestoes.this);

                    SugestoesReclamacoes sg = new SugestoesReclamacoes();
                    sg.setCliente(preferencias.getNome());
                    sg.setId(preferencias.getIdentificador());
                    sg.setSugestao(sugestao);
                    sg.setEmail(Base64Custom.decodificarBase64(preferencias.getIdentificador()));
                    sg.salvar(data_completa);

                    Toast.makeText(getApplication(), "Registro Gravado com Sucesso!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                    finish();
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
        Intent intent = new Intent(Sugestoes.this, Login.class);
        startActivity(intent);
        finish();
    }
    private void abrirContatosMensagens(){
        //Intent intent = new Intent(MainActivity.this, MensagemGerencia.class );
        //startActivity(intent);
    }
}
