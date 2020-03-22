package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Base64Custom;
import com.babeirosemgloria.barbergloria.helper.Mask;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import java.text.SimpleDateFormat;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWebException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Cadastro extends AppCompatActivity {
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText area;
    private EditText ddd;
    private EditText telefone;
    private EditText dtNasc;
    private Button btn_cadastrar;
    private Usuario usuario;

    private FirebaseAuth autenticacao;
    private DatabaseReference firebase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.edit_nome_user);
        email = findViewById(R.id.edit_email);
        senha = findViewById(R.id.edit_senha);
        area = findViewById(R.id.edit_codArea);
        ddd = findViewById(R.id.edit_ddd);
        telefone = findViewById(R.id.edit_telefone);
        dtNasc = findViewById(R.id.edit_dtNasc);

        dtNasc.addTextChangedListener(Mask.insert(Mask.DT_NASC, dtNasc));

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        SimpleMaskFormatter simpleMaskArea = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter simpleMaskDdd = new SimpleMaskFormatter("NN");

        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        MaskTextWatcher maskArea = new MaskTextWatcher(area, simpleMaskArea);
        MaskTextWatcher maskDdd = new MaskTextWatcher(ddd, simpleMaskDdd);

        telefone.addTextChangedListener( maskTelefone );
        area.addTextChangedListener( maskArea );
        ddd.addTextChangedListener( maskDdd );


        btn_cadastrar = findViewById(R.id.btn_cadastrar);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String telefoneCompleto = area.getText().toString()
                        + ddd.getText().toString()
                        + telefone.getText().toString();
                String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-","");

                usuario = new Usuario();

                usuario.setNome( nome.getText().toString() );
                usuario.setEmail( email.getText().toString());
                usuario.setSenha( senha.getText().toString() );
                String nascimento = dtNasc.getText().toString();
                usuario.setDataNascimento(nascimento.replace("/", "-"));
                usuario.setTelefone(telefoneSemFormatacao);


                if (usuario.getNome().equals("")){
                    Toast.makeText(Cadastro.this, "Usuário não pode ser vazio", Toast.LENGTH_SHORT).show();
                } else if ( usuario.getSenha().equals("")){
                    Toast.makeText(Cadastro.this, "Senha não pode ser vazio", Toast.LENGTH_SHORT).show();
                } else if(usuario.getEmail().equals("")){
                    Toast.makeText(Cadastro.this, "E-mail não pode ser vazio", Toast.LENGTH_SHORT).show();

                } else {
                    cadastrarUsuario();
                }

            }
        });

    }

    private void cadastrarUsuario() {


        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful() ){
                    Toast.makeText(Cadastro.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();


                    FirebaseUser usuarioFirebase = task.getResult().getUser();

                    String identificadorUsuario = Base64Custom.codificarBase64( usuario.getEmail() );
                    String identificadorUsuarioLogadoTel = Base64Custom.codificarBase64((usuario.getTelefone()));


                    usuario.setId( identificadorUsuario );
                    usuario.salvar();



                    Preferencias preferencias = new Preferencias(Cadastro.this);
                    preferencias.salvarDados(identificadorUsuario, usuario.getNome(), identificadorUsuarioLogadoTel);

                    abrirLoginUsuario();


                } else  {

                    String erroExcesao = "";

                    try {
                        throw task.getException();
                    } catch ( FirebaseAuthWebException e ) {
                        erroExcesao = "O e-mail digitado é inválido por favor digite um novo";
                    } catch ( FirebaseAuthInvalidCredentialsException e ) {
                        erroExcesao = "Digite uma senha mais forte, contendo mais caracteres e com letras e números, ou verifique se o e-mail é válido!";
                    } catch ( FirebaseAuthUserCollisionException e) {
                        erroExcesao = "Esse e-mail já está em uso no App!";
                    } catch ( Exception e  ){
                        erroExcesao = "Erro ao cadastrar usuário!" + e;
                        e.printStackTrace();
                    }

                    Toast.makeText(Cadastro.this, " Erro: "+erroExcesao, Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    public void abrirLoginUsuario(){


        Intent intent = new Intent(Cadastro.this, MainActivity.class);
        startActivity(intent);
        finish();

    }


    public void abrirLogin(View view) {
        Intent intent = new Intent(Cadastro.this, Login.class);
        startActivity(intent);
        finish();

    }
}
