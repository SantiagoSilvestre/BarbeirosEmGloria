package com.babeirosemgloria.barbergloria.model;

import android.provider.ContactsContract;
import android.text.BoringLayout;

import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class Horario {

    private String id;
    private String user;
    private String data;
    private String hora;
    private String barbeiro;
    private Boolean disponibilidade = true ;
    private ArrayList<String> horarios ;

    public Horario() {
    }


    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(String barbeiro) {
        this.barbeiro = barbeiro;
    }

    public Boolean getDisponibilidade() {return disponibilidade; }

    public void setDisponibilidade(Boolean disponibilidade) { this.disponibilidade = disponibilidade; }

    @Exclude
    public ArrayList<String> getHorarios() {return horarios; }

    public void setHorarios(String horarios) { this.horarios.add(horarios); }
}
