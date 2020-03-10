package com.babeirosemgloria.barbergloria.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    private  Context contexto;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "barberGloria";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private String CHAVE_IDENTIFICADOR = "identificadorUsuarioLogado";
    private String CHAVE_NOME = "nomeUsuarioLogado";
    private String CHAVE_VALOR = "valorTotal";
    private String CHAVE_DATA = "chaveData";
    private String CHAVE_HORA = "chaveHora";
    private String CHAVE_BARBEIRO = "chaveBarbeiro";
    private String CHAVE_CK_CORTE= "chaveCkCorte";
    private String CHAVE_CK_BARBA= "chaveCkBarba";
    private String CHAVE_CK_CORBAR= "chaveCkCorBar";
    private String CHAVE_CK_CORPROG= "chaveCkCorProg";
    private String CHAVE_CK_CORREL= "chaveCkRel";
    private String CHAVE_CK_PROG= "chaveCkProg";
    private String CHAVE_CK_REL= "chaveCkCorRel";
    private String CHAVE_CK_CORINFANTIL= "chaveCkCorInfantil";
    private String CHAVE_CK_LUZES= "chaveCkLuzes";
    private String CHAVE_CK_LIMPEZA= "chaveCkLimpeza";
    private String CHAVE_CK_PEZINHO= "chaveCkPezinho";
    private String CHAVE_CK_SOMBRANCELHA = "chaveCkSombrancelha";
    private String CHAVE_TX_CORTE= "chaveTXCorte";
    private String CHAVE_TX_BARBA= "chaveTXBarba";
    private String CHAVE_TX_CORBAR= "chaveTXCorBar";
    private String CHAVE_TX_CORPROG= "chaveTXCorProg";
    private String CHAVE_TX_CORREL= "chaveTXCorRel";
    private String CHAVE_TX_PROG= "chaveTXProg";
    private String CHAVE_TX_REL= "chaveTXRel";
    private String CHAVE_TX_CORINFANTIL= "chaveTXCorInfantil";
    private String CHAVE_TX_LUZES= "chaveTXLuzes";
    private String CHAVE_TX_LIMPEZA= "chaveTXLimpeza";
    private String CHAVE_TX_PEZINHO= "chaveTXPezinho";
    private String CHAVE_TX_SOMBRANCELHA = "chaveTXSombrancelha";
    private String CHAVE_DISP = "chaveDisp";
    private String CHAVE_COD_BAR = "chaveCod";

    public Preferencias( Context contextoParemetro ) {

        contexto = contextoParemetro;
        preferences = contexto.getSharedPreferences( NOME_ARQUIVO, MODE );
        editor = preferences.edit();

    }

    public void salvarDados( String identificadorUsuario, String nomeUsuario) {

        editor.putString(CHAVE_IDENTIFICADOR, identificadorUsuario);
        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();

    }

    public void salvarDisp(String disp){
        editor.putString(CHAVE_DISP, disp);
        editor.commit();
    }

    public void salvarValor(String valor) {
        editor.putString(CHAVE_VALOR, valor);
        editor.commit();
    }
    public void salvarBarbeiro(String barbeiro){
        editor.putString(CHAVE_BARBEIRO, barbeiro);
        editor.commit();
    }
    public void salvarCod(String codigo){
        editor.putString(CHAVE_COD_BAR, codigo);
        editor.commit();
    }
    public void salvarData(String data) {
        editor.putString(CHAVE_DATA, data);
        editor.commit();
    }

    public void salvarHora(String hora) {
        editor.putString(CHAVE_HORA, hora);
        editor.commit();
    }
    public void salvarCkCorte(String val, String corte) {
        editor.putString(CHAVE_CK_CORTE, val);
        editor.putString(CHAVE_TX_CORTE, corte);
        editor.commit();
    }
    public void salvarCkBarba(String val, String barba) {
        editor.putString(CHAVE_CK_BARBA, val);
        editor.putString(CHAVE_TX_BARBA, barba);
        editor.commit();
    }

    public void salvarCkCorBar(String val, String corBar) {
        editor.putString(CHAVE_CK_CORBAR, val);
        editor.putString(CHAVE_TX_CORBAR, corBar);
        editor.commit();
    }
    public void salvarCkCorProg(String val, String corProg) {
        editor.putString(CHAVE_CK_CORPROG, val);
        editor.putString(CHAVE_TX_CORPROG, corProg);
        editor.commit();
    }
    public void salvarCkRel(String val, String corRel) {
        editor.putString(CHAVE_CK_REL, val);
        editor.putString(CHAVE_TX_REL, corRel);
        editor.commit();
    }

    public void salvarCkProg(String val, String corProg) {
        editor.putString(CHAVE_CK_PROG, val);
        editor.putString(CHAVE_TX_PROG, corProg);
        editor.commit();
    }
    public void salvarCkCorRel(String val, String corRel) {
        editor.putString(CHAVE_CK_CORREL, val);
        editor.putString(CHAVE_TX_CORREL, corRel);
        editor.commit();
    }

    public void salvarCkCorteInfantil(String val, String infantil) {
        editor.putString(CHAVE_CK_CORINFANTIL, val);
        editor.putString(CHAVE_TX_CORINFANTIL, infantil);
        editor.commit();
    }
    public void salvarCkLimpeza(String val, String limpeza) {
        editor.putString(CHAVE_CK_LIMPEZA, val);
        editor.putString(CHAVE_TX_LIMPEZA, limpeza);
        editor.commit();
    }
    public void salvarCkPezinho(String val, String pezinho) {
        editor.putString(CHAVE_CK_PEZINHO, val);
        editor.putString(CHAVE_TX_PEZINHO, pezinho);
        editor.commit();
    }
    public void salvarCkLuzes(String val, String luzes) {
        editor.putString(CHAVE_CK_LUZES, val);
        editor.putString(CHAVE_TX_LUZES, luzes);
        editor.commit();
    }
    public void salvaCkSombrancelha(String val, String sombracelha ) {
        editor.putString(CHAVE_CK_SOMBRANCELHA, val);
        editor.putString(CHAVE_TX_SOMBRANCELHA, sombracelha);
        editor.commit();
    }

    public void clearPreferencias(){
        editor.putString(CHAVE_DISP, "");
        editor.putString(CHAVE_VALOR, "");
        editor.putString(CHAVE_BARBEIRO, "");
        editor.putString(CHAVE_CK_CORTE, "");
        editor.putString(CHAVE_CK_BARBA, "");
        editor.putString(CHAVE_CK_CORBAR, "");
        editor.putString(CHAVE_CK_CORPROG, "");
        editor.putString(CHAVE_CK_SOMBRANCELHA, "");
        editor.putString(CHAVE_CK_CORREL, "");
        editor.putString(CHAVE_CK_CORINFANTIL, "");
        editor.putString(CHAVE_CK_LUZES, "");
        editor.putString(CHAVE_CK_LIMPEZA, "");
        editor.putString(CHAVE_CK_PEZINHO, "");
        editor.putString(CHAVE_DATA, "");
        editor.putString(CHAVE_HORA, "");
        editor.commit();
    }



    public String getIdentificador() {
        return preferences.getString(CHAVE_IDENTIFICADOR, null);
    }

    public String getNome() {
        return preferences.getString(CHAVE_NOME, null);
    }

    public String getValor() { return preferences.getString(CHAVE_VALOR, null);}

    public String getBarbeiro() { return preferences.getString(CHAVE_BARBEIRO, null);}

    public String getData() { return preferences.getString(CHAVE_DATA, null);}

    public String getHora() { return preferences.getString(CHAVE_HORA, null);}

    public String getCheTXCorte() {return  preferences.getString(CHAVE_TX_CORTE, null);}

    public String getCheTXBarba() {return  preferences.getString(CHAVE_TX_BARBA, null);}

    public String getCheTXCorBar() {return  preferences.getString(CHAVE_TX_CORBAR, null);}

    public String getCheTXCorProg() {return  preferences.getString(CHAVE_TX_CORPROG, null);}

    public String getCheTXCorRel() {return  preferences.getString(CHAVE_TX_CORREL, null);}

    public String getCheTXProg() {return  preferences.getString(CHAVE_TX_PROG, null);}

    public String getCheTXRel() {return  preferences.getString(CHAVE_TX_REL, null);}

    public String getCheckCorInfantil() {return  preferences.getString(CHAVE_CK_CORINFANTIL, null);}

    public String getCheTXLimpeza() {return  preferences.getString(CHAVE_TX_LIMPEZA, null);}

    public String getCheTXLuzes() {return  preferences.getString(CHAVE_TX_LUZES, null);}

    public String getCheTXPezinho() {return  preferences.getString(CHAVE_TX_PEZINHO, null);}

    public String getCheTXSombrancelha() {return  preferences.getString(CHAVE_TX_SOMBRANCELHA, null);}

    public String getCheckCorte() {return  preferences.getString(CHAVE_CK_CORTE, null);}

    public String getCheckBarba() {return  preferences.getString(CHAVE_CK_BARBA, null);}

    public String getCheckCorBar() {return  preferences.getString(CHAVE_CK_CORBAR, null);}

    public String getCheckCorProg() {return  preferences.getString(CHAVE_CK_CORPROG, null);}

    public String getCheckRel() {return  preferences.getString(CHAVE_CK_REL, null);}

    public String getCheckProg() {return  preferences.getString(CHAVE_CK_PROG, null);}

    public String getCheckCorRel() {return  preferences.getString(CHAVE_CK_CORREL, null);}

    public String getCheTxCorInfantil() {return  preferences.getString(CHAVE_TX_CORINFANTIL, null);}

    public String getCheckLimpeza() {return  preferences.getString(CHAVE_CK_LIMPEZA, null);}

    public String getCheckLuzes() {return  preferences.getString(CHAVE_CK_LUZES, null);}

    public String getCheckPezinho() {return  preferences.getString(CHAVE_CK_PEZINHO, null);}

    public String getCheckSombrancelha() {return  preferences.getString(CHAVE_CK_SOMBRANCELHA, null);}

    public String getChaveDisp() { return preferences.getString(CHAVE_DISP,  null);}

    public String getCHAVE_COD_BAR() {return  preferences.getString(CHAVE_COD_BAR, null);};
}
