package com.babeirosemgloria.barbergloria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.babeirosemgloria.barbergloria.R;
import com.babeirosemgloria.barbergloria.model.Agendamentos;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoAdapter extends ArrayAdapter<Agendamentos> {

    private  ArrayList<Agendamentos> agendamentos;
    private  Context context;

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
            TextView data = (TextView) view.findViewById(R.id.tv_data);
            TextView hora = (TextView) view.findViewById(R.id.tv_hora);
            TextView total = (TextView) view.findViewById(R.id.tv_total);
            TextView barbeiro = (TextView) view.findViewById(R.id.tv_barbeiro);

            Agendamentos ag = agendamentos.get(position);

            data.setText(ag.getData());
            hora.setText(ag.getHora());
            total.setText(ag.getTotal());
            barbeiro.setText(ag.getBarbaeiro());
        }

        return view;
    }
}
