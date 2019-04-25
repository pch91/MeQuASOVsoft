package com.mequa.mequasovsoft.MODAL;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;

import org.json.JSONException;
import org.json.JSONObject;

@IgnoreExtraProperties
public class Planta {

    public String nome ;
    public int temperatura;
    public double umidade;
    public Boolean chuvendo;
    public double umidadear;


    public Planta(){

    }

    public Planta(String nome, int temperatura, double umidade, boolean chuvendo, double umidadear) {
        this.nome = nome;
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.chuvendo = chuvendo;
        this.umidadear = umidadear;
    }

    public Planta(JSONObject j) throws JSONException {
        this.nome = j.getString("name");
        this.temperatura = j.getInt("temperatura");
        this.umidade = j.getDouble("umidade");
        this.chuvendo = j.getBoolean("chuvendo");
        this.umidadear = j.getDouble("umidadear");
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

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }

    public Boolean getChuvendo() {
        return chuvendo;
    }

    public void setChuvendo(Boolean chuvendo) {
        this.chuvendo = chuvendo;
    }

    public double getUmidadear() {
        return umidadear;
    }

    public void setUmidadear(double umidadear) {
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
