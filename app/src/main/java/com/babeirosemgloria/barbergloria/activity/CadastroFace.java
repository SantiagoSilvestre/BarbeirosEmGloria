package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Base64Custom;
import com.babeirosemgloria.barbergloria.helper.Mask;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.babeirosemgloria.barbergloria.model.Agendamentos;
import com.babeirosemgloria.barbergloria.model.Usuario;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CadastroFace extends AppCompatActivity {

    private Button btn_cadastrar;
    private EditText editData;
    private EditText area;
    private EditText ddd;
    private EditText numb;
    DatabaseReference firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_face);


        editData = findViewById(R.id.edit_dtNasc);
        editData.addTextChangedListener(Mask.insert(Mask.DT_NASC, editData));

        area = findViewById(R.id.edit_codArea);
        ddd = findViewById(R.id.edit_ddd);
        numb = findViewById(R.id.edit_telefone);

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        SimpleMaskFormatter simpleMaskArea = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter simpleMaskDdd = new SimpleMaskFormatter("NN");

        MaskTextWatcher maskTelefone = new MaskTextWatcher(numb, simpleMaskTelefone);
        MaskTextWatcher maskArea = new MaskTextWatcher(area, simpleMaskArea);
        MaskTextWatcher maskDdd = new MaskTextWatcher(ddd, simpleMaskDdd);

        numb.addTextChangedListener( maskTelefone );
        area.addTextChangedListener( maskArea );
        ddd.addTextChangedListener( maskDdd );

        btn_cadastrar = findViewById(R.id.btn_cadastrar);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefoneCompleto = area.getText().toString()
                        + ddd.getText().toString()
                        + numb.getText().toString();
                String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-","");

                Preferencias preferencias = new Preferencias(CadastroFace.this);
                Usuario usuario = new Usuario();
                usuario.setId(preferencias.getIdentificador());
                usuario.setNome(preferencias.getNome());
                String nascimento = editData.getText().toString();
                usuario.setDataNascimento(nascimento.replace("/", "-"));
                usuario.setTelefone(telefoneSemFormatacao);
                usuario.setEmail(Base64Custom.decodificarBase64(preferencias.getIdentificador()));
                if (usuario.getDataNascimento().equals("")){
                    Toast.makeText(CadastroFace.this, "Digite sua data de nascimento", Toast.LENGTH_SHORT).show();
                } else if (numb.getText().toString().equals("")){
                    Toast.makeText(CadastroFace.this, "Digite seu telefone", Toast.LENGTH_SHORT).show();
                } else {
                    usuario.salvar();
                    startActivity(new Intent(CadastroFace.this, MainActivity.class));
                }

            }
        });


    }


}
