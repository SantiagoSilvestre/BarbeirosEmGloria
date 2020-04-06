package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetSenha extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText email;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onResume(){
        super.onResume();
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
    }

    private void init(){
        toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Barbeiros em Glórias");
        setSupportActionBar( toolbar );
        email =  findViewById(R.id.email);
    }

    public void reset(View view){
        firebaseAuth
                .sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        email.setText("");
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetSenha.this, "E-mail de recuperação enviado",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetSenha.this, "Verifique o e-mail digitado está correto",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
