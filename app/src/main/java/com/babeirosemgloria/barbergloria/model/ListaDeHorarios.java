package com.babeirosemgloria.barbergloria.model;

import com.babeirosemgloria.barbergloria.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class ListaDeHorarios {

    private String id;
    private String hora1;
    private String hora2;
    private String hora3;
    private String hora4;
    private String hora5;
    private String hora6;
    private String hora7;
    private String hora8;
    private String hora9;

    public ListaDeHorarios() {

        hora1 = "10:00";
        hora2 = "11:00";
        hora3 = "12:00";
        hora4 = "13:00";
        hora5 = "14:00";
        hora6 = "15:00";
        hora7 = "16:00";
        hora8 = "17:00";
        hora9 = "18:00";

    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHora1() {
        return hora1;
    }

    public void setHora1(String hora1) {
        this.hora1 = hora1;
    }

    public String getHora2() {
        return hora2;
    }

    public void setHora2(String hora2) {
        this.hora2 = hora2;
    }

    public String getHora3() {
        return hora3;
    }

    public void setHora3(String hora3) {
        this.hora3 = hora3;
    }

    public String getHora4() {
        return hora4;
    }

    public void setHora4(String hora4) {
        this.hora4 = hora4;
    }

    public String getHora6() {
        return hora6;
    }

    public void setHora6(String hora6) {
        this.hora6 = hora6;
    }

    public String getHora7() {
        return hora7;
    }

    public void setHora7(String hora7) {
        this.hora7 = hora7;
    }

    public String getHora8() {
        return hora8;
    }

    public void setHora8(String hora8) {
        this.hora8 = hora8;
    }

    public String getHora9() {
        return hora9;
    }

    public void setHora9(String hora9) {
        this.hora9 = hora9;
    }

    public String getHora10() {
        return hora5;
    }

    public void setHora10(String hora5) {
        this.hora5 = hora5;
    }
}
