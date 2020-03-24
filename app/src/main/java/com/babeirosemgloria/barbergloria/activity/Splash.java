package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Splash extends AppCompatActivity {

    DatabaseReference firebase;
    DatabaseReference reference;
    DatabaseReference firebases;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


            try {
                PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    String hashKey = new String(Base64.encode(md.digest(), 0));
                    Log.i("dsjvcbfhj", "printHashKey() Hash Key: " + hashKey);
                }
            } catch (NoSuchAlgorithmException e) {
                Log.e("dsjvcbfhj", "printHashKey()", e);
            } catch (Exception e) {
                Log.e("dsjvcbfhj", "printHashKey()", e);
            }


        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        if (autenticacao.getCurrentUser() == null) {
            final int MILISEGUNDOS = 3000;
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    Intent i = new Intent(Splash.this, Login.class);
                    Splash.this.startActivity(i);
                }
            }, MILISEGUNDOS);
        } else {

            final int MILISEGUNDOS = 3000;
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    Splash.this.startActivity(i);
                }
            }, MILISEGUNDOS);
        }


    }


}
