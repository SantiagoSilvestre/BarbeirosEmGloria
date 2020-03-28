package com.babeirosemgloria.barbergloria.model;

import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class Agendamentos {

    private String id;
    private String data;
    private String hora;
    private String total;
    private String barbaeiro;
    private ArrayList<String> servicos;

    public Agendamentos(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBarbaeiro() {
        return barbaeiro;
    }

    public void setBarbaeiro(String barbaeiro) {
        this.barbaeiro = barbaeiro;
    }
    public ArrayList<String> getServicos() {
        return servicos;
    }

    public void setServicos(ArrayList<String> servicos) {
        this.servicos = servicos;
    }
}
