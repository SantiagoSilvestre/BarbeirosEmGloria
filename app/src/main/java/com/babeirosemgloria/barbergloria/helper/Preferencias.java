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

    public void salvarValor(String valor) {
        editor.putString(CHAVE_VALOR, valor);
        editor.commit();
    }
    public void salvarBarbeiro(String barbeiro){
        editor.putString(CHAVE_BARBEIRO, barbeiro);
        editor.commit();
    }
    public void salvarData(String data, String hora) {
        editor.putString(CHAVE_DATA, data);
        editor.putString(CHAVE_HORA, hora);
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

}
