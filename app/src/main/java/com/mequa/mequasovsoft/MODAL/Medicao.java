package com.mequa.mequasovsoft.MODAL;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@IgnoreExtraProperties
public class Medicao {

    public String id;

    public String planta;

    @SerializedName("temperatura")
    @Expose
    public int temperatura;

    @SerializedName("umidade")
    @Expose
    public double umidade;

    @SerializedName("chuvendo")
    @Expose
    public Boolean chuvendo;

    @SerializedName("umidadear")
    @Expose
    public double umidadear;

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
