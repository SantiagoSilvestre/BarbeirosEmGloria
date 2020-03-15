package com.babeirosemgloria.barbergloria.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.bluetooth.le.AdvertisingSetParameters;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.babeirosemgloria.barbergloria.model.Agendamentos;
import com.babeirosemgloria.barbergloria.model.Horario;
import com.babeirosemgloria.barbergloria.model.ListaDeHorarios;
import com.babeirosemgloria.barbergloria.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ListaHorarios extends AppCompatActivity {

    private Button btnConfirmar;
    private DatabaseReference firebase;
    private DatabaseReference referencia;
    private TextView hora1;
    private TextView hora2;
    private TextView hora3;
    private TextView hora4;
    private TextView hora5;
    private TextView hora6;
    private TextView hora7;
    private TextView hora8;
    private TextView hora9;
    private TextView horaDisp;
    private TextView displayDate;
    private Button btnData;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog DatePickerDialog;
    private FirebaseAuth usuarioAutenticacao;
    private TextView txtValor;
    Preferencias preferencias;
    private ValueEventListener eventListener;
    Horario horario = new Horario();
    Horario hor = new Horario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horarios);

        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Barbeiros em Glórias");
        setSupportActionBar( toolbar );

        preferencias = new Preferencias(ListaHorarios.this);
        recuperaValores();
        if(preferencias.getValor() != null ) {  txtValor.setText(preferencias.getValor()); }

        // Define a localidade sendo como Brasl
        Locale brasil = new Locale("pt", "BR");

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", brasil);

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateTimeField();
                DatePickerDialog.show();
                preferencias.salvarData(displayDate.getText().toString());


            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(preferencias.getHora().equals("")) {
                    android.app.AlertDialog.Builder alert = new AlertDialog.Builder(ListaHorarios.this);
                    alert.setTitle("Atenção");
                    alert.setMessage("Nenhum Horário foi Selecionado");
                    alert.setCancelable(false);
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert.show();
                } else {

                    agendarHorario();
                    Intent intent1 = new Intent(ListaHorarios.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
            }
        });

    }

    public void backMain(View view) {

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    public void verificaDisponibilidade(final TextView txt, final String hora, String barbeiro, String data) {

        if(preferencias.getCHAVE_COD_BAR().equals("4")) {
            preferencias.salvarBarbeiro("Danilo");
            String barb = preferencias.getBarbeiro();



            referencia = ConfiguracaoFirebase.getFirebase()
                    .child(barb)
                    .child(data)
                    .child("Agendamento")
                    .child(hora);
            //Log.i("testeRefe", teste);

            referencia.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists() ) {
                        hor = dataSnapshot.getValue( Horario.class );
                        if(hor.getDisponibilidade().equals("Não")) {

                            Log.i("testeRefe", "entrou no danilo");

                            preferencias.salvarBarbeiro("Igor");

                            referencia = ConfiguracaoFirebase.getFirebase().child(preferencias.getBarbeiro())
                                    .child(hor.getData())
                                    .child("Agendamento")
                                    .child(hor.getHora());

                            referencia.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists() ) {
                                        Horario h = new Horario();
                                        h = dataSnapshot.getValue( Horario.class );
                                        if(h.getDisponibilidade().equals("Não")) {
                                            Log.i("testeRefe", "Entrou no Igor");
                                            preferencias.salvarBarbeiro("Kaique");

                                            referencia = ConfiguracaoFirebase.getFirebase().child(preferencias.getBarbeiro())
                                                    .child(h.getData())
                                                    .child("Agendamento")
                                                    .child(h.getHora());

                                            referencia.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.exists()) {
                                                        Log.i("testeRefe", "Entrou no Kaique");
                                                        txt.setText("Não diponível");
                                                    } else {
                                                        preferencias.salvarBarbeiro("Kaique");
                                                        Log.i("testeRefe", "Entrou no Kaique disponivel");
                                                        Log.i("testeRefe", preferencias.getBarbeiro());
                                                        txt.setText("Dísponivel");
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                    }else {
                                        Log.i("testeRefe", "Entrou no Igor disponivel");
                                        txt.setText("Diponível");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                    }else {
                        Log.i("testeRefe", "Entrou no Danilo disponivel");
                        txt.setText("Diponível");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else  {

            firebase = ConfiguracaoFirebase.getFirebase()
                    .child(barbeiro)
                    .child(data)
                    .child("Agendamento" )
                    .child(hora);
            eventListener = new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists() ) {

                        horario = dataSnapshot.getValue( Horario.class );
                        if(horario.getDisponibilidade().equals("Não")) {
                            if(horario.getServicos().size() > 1 ){
                                String val = hora.replace(":", "");
                                val = val.substring(0, 2);
                                int i = Integer.parseInt(val);
                                int d = 1;
                                while( d < horario.getServicos().size()){
                                    i += 1;
                                    LinearLayout linearLayout = recuperaLinear(i);
                                    linearLayout.setVisibility(View.GONE);
                                    d++;
                                }

                            }
                            txt.setText("Não Disponível");
                        }

                    }else {
                        txt.setText("Diponível");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            firebase.addListenerForSingleValueEvent(eventListener);

        }

    }

    public void recuperaValores() {
        hora1 = findViewById(R.id.txtHora1);
        hora2 = findViewById(R.id.txtHora2);
        hora3 = findViewById(R.id.txtHora3);
        hora4 = findViewById(R.id.txtHora4);
        hora5 = findViewById(R.id.txtHora5);
        hora6 = findViewById(R.id.txtHora6);
        hora7 = findViewById(R.id.txtHora7);
        hora8 = findViewById(R.id.txtHora8);
        hora9 = findViewById(R.id.txtHora9);
        horaDisp = findViewById(R.id.horaDisponivel);
        btnConfirmar = findViewById(R.id.btnConfimar);
        btnData = findViewById(R.id.btnData);
        displayDate = findViewById(R.id.displayDate);
        txtValor = findViewById(R.id.txtValor);

    }


    public void setDisponiblidade(String data){
        int cont = 10;
        while(cont < 18){
            LinearLayout linearLayout = recuperaLinear(cont);
            linearLayout.setVisibility(View.VISIBLE);
            cont ++;
        }


        verificaDisponibilidade(hora1,"10:00", preferencias.getBarbeiro(), data);
        verificaDisponibilidade(hora2,"11:00", preferencias.getBarbeiro(), data);
        verificaDisponibilidade(hora3,"12:00", preferencias.getBarbeiro(), data);
        verificaDisponibilidade(hora4,"13:00", preferencias.getBarbeiro(), data);
        verificaDisponibilidade(hora5,"14:00", preferencias.getBarbeiro(), data);
        verificaDisponibilidade(hora6,"15:00", preferencias.getBarbeiro(), data);
        verificaDisponibilidade(hora7,"16:00", preferencias.getBarbeiro(), data);
        verificaDisponibilidade(hora8,"17:00", preferencias.getBarbeiro(), data);
        verificaDisponibilidade(hora9,"18:00", preferencias.getBarbeiro(), data);

    }

    public void agendar1(View view) {

        TextView txtHora = findViewById(R.id.tx10);
        String hora = txtHora.getText().toString();
        preferencias.salvarHora(hora);

    }
    public void agendar2(View view) {

        TextView txtHora = findViewById(R.id.tx11);
        String hora = txtHora.getText().toString();
        preferencias.salvarHora(hora);

    }
    public void agendar3(View view) {

        TextView txtHora = findViewById(R.id.tx12);
        String hora = txtHora.getText().toString();
        preferencias.salvarHora(hora);

    }
    public void agendar4(View view) {

        TextView txtHora = findViewById(R.id.tx13);
        String hora = txtHora.getText().toString();
        preferencias.salvarHora(hora);

    }
    public void agendar5(View view) {

        TextView txtHora = findViewById(R.id.tx14);
        String hora = txtHora.getText().toString();
        preferencias.salvarHora(hora);

    }
    public void agendar6(View view) {

        TextView txtHora = findViewById(R.id.tx15);
        String hora = txtHora.getText().toString();
        preferencias.salvarHora(hora);

    }
    public void agendar7(View view) {

        TextView txtHora = findViewById(R.id.tx16);
        String hora = txtHora.getText().toString();
        preferencias.salvarHora(hora);

    }
    public void agendar8(View view) {

        TextView txtHora = findViewById(R.id.tx17);
        String hora = txtHora.getText().toString();
        preferencias.salvarHora(hora);
    }
    public void agendar9(View view) {

        TextView txtHora = findViewById(R.id.tx18);
        String hora = txtHora.getText().toString();
        preferencias.salvarHora(hora);

    }

    public void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                displayDate.setText(dateFormatter.format(newDate.getTime()));
                String dt = displayDate.getText().toString();
                dt = dt.replace("/","-");
                preferencias.salvarData(dt);
                String data = preferencias.getData();
                setDisponiblidade(data);
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
        Intent intent = new Intent(ListaHorarios.this, Login.class);
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
        if(preferencias.getCheckSombrancelha().equals("1")) {
            servicos.add(preferencias.getCheTXSombrancelha());
        }
        if(preferencias.getCheckRel().equals("1")) {
            servicos.add(preferencias.getCheTXRel());
        }
        if(preferencias.getCheckProg().equals("1")) {
            servicos.add(preferencias.getCheTXProg());
        }
        if(preferencias.getCheckLimpeza().equals("1")) {
            servicos.add(preferencias.getCheTXLimpeza());
        }
        if(preferencias.getCheckPezinho().equals("1")) {
            servicos.add(preferencias.getCheTXPezinho());
        }
        if(preferencias.getCheckCorInfantil().equals("1")) {
            servicos.add(preferencias.getCheTxCorInfantil());
        }
        if(preferencias.getCheckLuzes().equals("1")) {
            servicos.add(preferencias.getCheTXLuzes());
        }
        if(preferencias.getCheckCorProg().equals("1")) {
            servicos.add(preferencias.getCheTXCorProg());
        }

        if(preferencias.getCheckCorRel().equals("1")) {
            servicos.add(preferencias.getCheTXCorRel());
        }





        return servicos;
    }

    private void agendarHorario(){
        // Aqui vai fazer toda a lógica que recuperar todos os dados e manda para o objeto
        horario = new Horario();
        Agendamentos ag = new Agendamentos();

        String data = preferencias.getData();
        data = data.replace("/", "-");
        horario.setData(data);
        horario.setDisponibilidade("Não");
        horario.setCliente(preferencias.getNome());
        horario.setHora(preferencias.getHora());
        horario.setServicos(servicosArray());
        horario.setTotal(txtValor.getText().toString());
        horario.salvar(preferencias.getBarbeiro());

        ag.setBarbaeiro(preferencias.getBarbeiro());
        ag.setData(preferencias.getData());
        ag.setHora(preferencias.getHora());
        ag.setTotal(preferencias.getValor());
        ag.setId(preferencias.getIdentificadorTel());
        ag.setServicos(servicosArray());

        DatabaseReference refereciaFirebase = ConfiguracaoFirebase.getFirebase();
        refereciaFirebase.child("Agendamentos").child(preferencias.getIdentificador())
        .child(preferencias.getIdentificadorTel()).setValue(ag);



        preferencias.clearPreferencias();
    }

    public LinearLayout recuperaLinear(int i ){
        LinearLayout linearLayout;
        switch(i) {
            case 10:
                linearLayout = findViewById(R.id.LinearHour1);
                break;
            case 11:
                linearLayout = findViewById(R.id.LinearHour2);
                break;
            case 12:
                linearLayout = findViewById(R.id.LinearHour3);
                break;
            case 13:
                linearLayout = findViewById(R.id.LinearHour4);
                break;
            case 14:
                linearLayout = findViewById(R.id.LinearHour5);
                break;
            case 15:
                linearLayout = findViewById(R.id.LinearHour6);
                break;
            case 16:
                linearLayout = findViewById(R.id.LinearHour7);
                break;
            case 17:
                linearLayout = findViewById(R.id.LinearHour8);
                break;
            default:
                linearLayout = findViewById(R.id.LinearHour9);
        }
        return linearLayout;
    }
}

