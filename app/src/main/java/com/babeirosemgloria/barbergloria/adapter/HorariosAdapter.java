package com.babeirosemgloria.barbergloria.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.babeirosemgloria.barbergloria.helper.Preferencias;
import com.babeirosemgloria.barbergloria.model.Horario;
import com.babeirosemgloria.barbergloria.model.ListaDeHorarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HorariosAdapter extends ArrayAdapter<ListaDeHorarios> {

    private ArrayList<ListaDeHorarios> listaDeHorarios;
    private Context context;
    private Horario agenda;
    private DatabaseReference database ;
    private Preferencias preferencias;
    private ValueEventListener valueEventListener;

    public HorariosAdapter(Context c, ArrayList<ListaDeHorarios> objects) {
        super(c, 0, objects);
        this.listaDeHorarios = objects;
        this.context = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        // Verifica se a lista está vazia
        if( listaDeHorarios != null ){

            //Pesquisar no banco se tem disponibilidade
            agenda = new Horario();

            database = ConfiguracaoFirebase.getFirebase().child(preferencias.getData());

            // inicializar objeto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // Monta view a partir do xml
            view = inflater.inflate(R.layout.list_horarios, parent, false);


            // recupera elemento para exibição
            TextView hora = (TextView) view.findViewById(R.id.tv_hora);
            TextView disponibilidade = (TextView) view.findViewById(R.id.tv_disponibilidade);

            ListaDeHorarios listHoras = listaDeHorarios.get( position );
            //hora.setText( horario.getHora());

            String disponivel;
            /*
            if(horario.getDisponibilidade()){
                disponivel = "Disponível" ;
            } else {
                disponivel  = "Não Disponivel";
            }

            disponibilidade.setText(disponivel);


             */
        }

        return view;

    }

}
