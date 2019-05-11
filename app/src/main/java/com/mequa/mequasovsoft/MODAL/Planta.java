package com.mequa.mequasovsoft.MODAL;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;

import org.json.JSONException;
import org.json.JSONObject;

@IgnoreExtraProperties
public class Planta {

    public String nome ;
    public String temperatura;
    public String umidade;
    public boolean chuvendo;
    public String umidadear;


    public Planta(){

    }

    public Planta(String nome, String temperatura, String umidade, boolean chuvendo, String umidadear) {
        this.nome = nome;
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.chuvendo = chuvendo;
        this.umidadear = umidadear;
    }

    public Planta(JSONObject j) throws JSONException {
        this.nome = j.getString("name");
        this.temperatura = j.getString("temperatura");
        this.umidade = j.getString("umidade");
        this.chuvendo = j.getBoolean("chuvendo");
        this.umidadear = j.getString("umidadear");
    }


    public String getNome() {
        return nome;
    }

    public String getId() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getUmidade() {
        return umidade;
    }

    public void setUmidade(String umidade) {
        this.umidade = umidade;
    }

    public Boolean getChuvendo() {
        return chuvendo;
    }

    public void setChuvendo(Boolean chuvendo) {
        this.chuvendo = chuvendo;
    }

    public String getUmidadear() {
        return umidadear;
    }

    public void setUmidadear(String umidadear) {
        this.umidadear = umidadear;
    }

    public String to_jeson() {

        JSONObject obj = new JSONObject();

        try {
            obj.put("name", getNome());
            obj.put("chuvendo", getChuvendo());
            obj.put("umidade", getUmidade());
            obj.put("umidadear", getUmidadear());
            obj.put("temperatura", getTemperatura());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
}
