package com.babeirosemgloria.barbergloria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.model.Horario;

import java.util.ArrayList;

public class HorariosAdapter extends ArrayAdapter<Horario> {

    private ArrayList<Horario> horarios;
    private Context context;

    public HorariosAdapter(Context c, ArrayList<Horario> objects) {
        super(c, 0, objects);
        this.horarios = objects;
        this.context = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        // Verifica se a lista está vazia
        if( horarios != null ){

            // inicializar objeto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // Monta view a partir do xml
            view = inflater.inflate(R.layout.list_horarios, parent, false);

            // recupera elemento para exibição
            TextView hora = (TextView) view.findViewById(R.id.tv_hora);
            TextView disponibilidade = (TextView) view.findViewById(R.id.tv_disponibilidade);

            Horario horario = horarios.get( position );
            hora.setText( horario.getHora());

            String disponivel;
            if(horario.getDisponibilidade()){
                disponivel = "Disponível" ;
            } else {
                disponivel  = "Não Disponivel";
            }

            disponibilidade.setText(disponivel);

        }

        return view;

    }

}
