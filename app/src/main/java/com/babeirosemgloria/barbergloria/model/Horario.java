package com.babeirosemgloria.barbergloria.model;

import android.provider.ContactsContract;
import android.text.BoringLayout;

import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class Horario {

    private String id;
    private String cliente;
    private String data;
    private String hora;
    private String disponibilidade;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    private String total;


    public ArrayList<String> getServicos() {
        return servicos;
    }

    public void setServicos(ArrayList<String> servicos) {
        this.servicos = servicos;
    }

    private ArrayList<String> servicos;

    public Horario() {
    }

    public void salvar(String barbeiro) {
        DatabaseReference refereciaFirebase = ConfiguracaoFirebase.getFirebase();
        refereciaFirebase.child(barbeiro).child(getData()).child("Agendamento").child( getHora() ).setValue( this );
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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

    public String getDisponibilidade() {return disponibilidade; }

    public void setDisponibilidade(String disponibilidade) { this.disponibilidade = disponibilidade; }




}
