package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Base64Custom;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.babeirosemgloria.barbergloria.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class Login extends AppCompatActivity {

    private DatabaseReference firebase;
    private DatabaseReference reference;
    private DatabaseReference referenceFirebase;

    private EditText usuarioL;
    private EditText senhaL;
    private Button btn_logar;
    private Usuario user;
    private FirebaseAuth auth;
    private ValueEventListener eventListener;
    private String identificarUsuarioLogado;
    private String identificarUsuarioLogadoTel;
    private TextView txtNaoTemConta;
    private Button btnFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verificarUsuarioLogado();

        txtNaoTemConta = findViewById(R.id.text_cadastrar);

        txtNaoTemConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Cadastro.class);
                startActivity(intent);
            }
        });

        usuarioL = findViewById(R.id.edit_login_email);
        senhaL = findViewById(R.id.edit_login_senha);
        btn_logar = findViewById(R.id.btnLogar);
        btnFace = findViewById(R.id.btnFace);

        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = new Usuario();
                user.setEmail(usuarioL.getText().toString().trim());
                user.setSenha(senhaL.getText().toString());

                if ( user.getEmail().equals("") & user.getSenha().equals("") ) {
                    Toast.makeText(Login.this, "E-mail ou senha incorreto", Toast.LENGTH_SHORT).show();
                } else {
                    validarLogin();
                }
            }
        });

        btnFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
    private void verificarUsuarioLogado(){
        auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (auth.getCurrentUser() != null) {

            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void validarLogin() {

        try {
            auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
            auth.signInWithEmailAndPassword(user.getEmail(), user.getSenha()
            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        identificarUsuarioLogado = Base64Custom.codificarBase64(user.getEmail());

                        referenceFirebase = ConfiguracaoFirebase.getFirebase().child("usuarios")
                                .child(identificarUsuarioLogado);

                        eventListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Usuario usuarioRecuperado = dataSnapshot.getValue( Usuario.class );

                                Preferencias preferencias = new Preferencias(Login.this);
                                identificarUsuarioLogadoTel = Base64Custom.codificarBase64(usuarioRecuperado.getTelefone());
                                preferencias.salvarDados(identificarUsuarioLogado, usuarioRecuperado.getNome(),identificarUsuarioLogadoTel);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        };
                        referenceFirebase.addListenerForSingleValueEvent(eventListener);

                        Toast.makeText(Login.this, "Sucesso ao fazer Login", Toast.LENGTH_SHORT).show();
                        Log.i("testeUser", identificarUsuarioLogado);

                        abrirTelaPrincipal();

                    } else {
                        Toast.makeText(Login.this, "Usuário e/ou senha incorreto", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } catch ( Exception e ){
            Toast.makeText(this, "Campos não podem ser vazios", Toast.LENGTH_SHORT).show();
        }

    }

    public void abrirCadastroUsuario(View view) {
        Intent intent = new Intent(Login.this, Cadastro.class);
        startActivity(intent);
    }

    private void abrirTelaPrincipal(){
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
