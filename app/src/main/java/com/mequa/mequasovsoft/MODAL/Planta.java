package com.mequa.mequasovsoft.MODAL;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Planta {

    public String nome ;
    public int temperatura;
    public double umidade;
    public Boolean chuvendo;
    public double umidadear;

    public String getNome() {
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
}
