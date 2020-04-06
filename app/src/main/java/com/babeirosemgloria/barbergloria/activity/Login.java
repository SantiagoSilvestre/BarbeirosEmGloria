package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.BaseColumns;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.reflect.Modifier.FINAL;


public class Login extends AppCompatActivity {
    DatabaseReference firebase;
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
    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCallbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
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
        mCallbackManager = CallbackManager.Factory.create();
        btnFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(Login.this,Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("dsjvcbfhj", "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d("dsjvcbfhj", "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("dsjvcbfhj", "facebook:onError", error);
                        // ...
                    }
                });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("dsjvcbfhj", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("dsjvcbfhj", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Preferencias preferencias = new Preferencias(Login.this);

                            Usuario usuario = new Usuario();
                            usuario.setEmail(user.getEmail());
                            usuario.setNome(user.getDisplayName());
                            usuario.setDataNascimento("Não Informado");
                            usuario.setTelefone(user.getPhoneNumber());
                            String identificador = Base64Custom.codificarBase64(user.getEmail());
                            String tel = user.getPhoneNumber();
                            String telefone;
                            if (tel != null){
                                telefone = Base64Custom.codificarBase64(tel);
                            } else {
                                telefone = Base64Custom.codificarBase64("55117070-7070");
                            }

                            preferencias.salvarDados(identificador,
                                    user.getDisplayName(), telefone);
                            usuario.setId(identificador);

                            verificaNascimento();

                            //usuario.salvar();



                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("dsjvcbfhj", "signInWithCredential:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void verificaNascimento(){
        //Recuperar contatos do firebase
        Preferencias preferencias = new Preferencias(this);
        String identificadorUsuarioLogado = preferencias.getIdentificador();
        //identificadorUsuarioLogado = "c2FudGhpYWdvOTlAaG90bWFpbC5jb20=";
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("usuarios")
                .child( identificadorUsuarioLogado ).child("dataNascimento");


        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Log.i("apenasTEste", dataSnapshot.getValue().toString());

                    if(dataSnapshot.getValue().toString().equals("Não Informado")) {
                        startActivity(new Intent(Login.this, CadastroFace.class));
                        finish();
                    } else {
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }
                } else {
                    startActivity(new Intent(Login.this, CadastroFace.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void recuperarSenha(View view) {
        startActivity(new Intent(getApplicationContext(), ResetSenha.class));
    }
}
