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
    private String CHAVE_CK_CORREL= "chaveCkCorRel";
    private String CHAVE_CK_CORINFANTIL= "chaveCkCorInfantil";
    private String CHAVE_CK_LUZES= "chaveCkLuzes";
    private String CHAVE_CK_LIMPEZA= "chaveCkLimpeza";
    private String CHAVE_CK_PEZINHO= "chaveCkPezinho";
    private String CHAVE_CK_SOMBRANCELHA = "chaveCkSombrancelha";
    private String CHAVE_DISP = "chaveDisp";

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
    public void salvarData(String data) {
        editor.putString(CHAVE_DATA, data);
        editor.commit();
    }
    public void salvarCkCorte(String val) {
        editor.putString(CHAVE_CK_CORTE, val);
        editor.commit();
    }
    public void salvarCkBarba(String val) {
        editor.putString(CHAVE_CK_BARBA, val);
        editor.commit();
    }
    public void salvarCkCorBar(String val) {
        editor.putString(CHAVE_CK_CORBAR, val);
        editor.commit();
    }
    public void salvarCkCorProg(String val) {
        editor.putString(CHAVE_CK_CORPROG, val);
        editor.commit();
    }
    public void salvarCkCorRel(String val) {
        editor.putString(CHAVE_CK_CORREL, val);
        editor.commit();
    }
    public void salvarCkCorteInfantil(String val) {
        editor.putString(CHAVE_CK_CORINFANTIL, val);
        editor.commit();
    }
    public void salvarCkLimpeza(String val) {
        editor.putString(CHAVE_CK_LIMPEZA, val);
        editor.commit();
    }
    public void salvarCkPezinho(String val) {
        editor.putString(CHAVE_CK_PEZINHO, val);
        editor.commit();
    }
    public void salvarCkLuzes(String val) {
        editor.putString(CHAVE_CK_LUZES, val);
        editor.commit();
    }
    public void salvaCkSombrancelha(String val ) {
        editor.putString(CHAVE_CK_SOMBRANCELHA, val);
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

    public String getCheckCorte() {return  preferences.getString(CHAVE_CK_CORTE, null);}

    public String getCheckBarba() {return  preferences.getString(CHAVE_CK_BARBA, null);}

    public String getCheckCorBar() {return  preferences.getString(CHAVE_CK_CORBAR, null);}

    public String getCheckCorProg() {return  preferences.getString(CHAVE_CK_CORPROG, null);}

    public String getCheckCorRel() {return  preferences.getString(CHAVE_CK_CORREL, null);}

    public String getCheckCorInfantil() {return  preferences.getString(CHAVE_CK_CORINFANTIL, null);}

    public String getCheckLimpeza() {return  preferences.getString(CHAVE_CK_LIMPEZA, null);}

    public String getCheckLuzes() {return  preferences.getString(CHAVE_CK_LUZES, null);}

    public String getCheckPezinho() {return  preferences.getString(CHAVE_CK_PEZINHO, null);}

    public String getCheckSombrancelha() {return  preferences.getString(CHAVE_CK_SOMBRANCELHA, null);}

    public String getChaveDisp() { return preferences.getString(CHAVE_DISP,  null);}

}
