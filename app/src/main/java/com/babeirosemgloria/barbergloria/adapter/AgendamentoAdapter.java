package com.babeirosemgloria.barbergloria.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.activity.ListaHorarios;
import com.babeirosemgloria.barbergloria.activity.MeusAgendamentos;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.babeirosemgloria.barbergloria.model.Agendamentos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoAdapter extends ArrayAdapter<Agendamentos> {

    private  ArrayList<Agendamentos> agendamentos;
    private  Context context;
    Agendamentos ag;

    public AgendamentoAdapter(@NonNull Context c,  @NonNull ArrayList<Agendamentos> objects) {
        super(c, 0,  objects);
        this.agendamentos = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        //Verifica se a lista ta vazia
        if(agendamentos != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // Monta a view
            view = inflater.inflate(R.layout.lista_agendamento, parent, false);

            //Recupera o elemento para exibicao
            final TextView data = (TextView) view.findViewById(R.id.tv_data);
            final TextView hora = (TextView) view.findViewById(R.id.tv_hora);
            final TextView total = (TextView) view.findViewById(R.id.tv_total);
            final TextView barbeiro = (TextView) view.findViewById(R.id.tv_barbeiro);
            final Button btnCancel = view.findViewById(R.id.btnCancelar);



            ag = agendamentos.get(position);

            data.setText(ag.getData());
            hora.setText(ag.getHora());
            total.setText(ag.getTotal());
            barbeiro.setText(ag.getBarbaeiro());
            btnCancel.setText(ag.getId());

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    android.app.AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    alert.setTitle("Atenção");
                    alert.setMessage("Tem certeza que deseja cancelar o serviço?");
                    alert.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            removerReferencia(btnCancel.getText().toString(), barbeiro.getText().toString(),
                                    data.getText().toString(), hora.getText().toString());
                        }
                    });
                    alert.show();



                }
            });
        }

        return view;
    }

    private void removerReferencia (String id, String barbeiro, String data, String hora ) {
        Preferencias preferencias = new Preferencias(getContext());
        String identificadorUsuarioLogado = preferencias.getIdentificador();
        DatabaseReference firebase;
        DatabaseReference reference;
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Agendamentos")
                .child( identificadorUsuarioLogado ).child(id);


        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    dataSnapshot.getRef().removeValue();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference = ConfiguracaoFirebase.getFirebase()
                .child(barbeiro).child(data).child("Agendamento").child(hora);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    dataSnapshot.getRef().removeValue();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
