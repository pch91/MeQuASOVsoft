package com.mequa.mequasovsoft.MODAL;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@IgnoreExtraProperties
public class Medicao {

    @SerializedName("created_at")
    @Expose
    public int temperatura;

    @SerializedName("description")
    @Expose
    public double umidade;

    @SerializedName("done")
    @Expose
    public Boolean chuvendo;

    @SerializedName("id")
    @Expose
    public double umidadear;

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
