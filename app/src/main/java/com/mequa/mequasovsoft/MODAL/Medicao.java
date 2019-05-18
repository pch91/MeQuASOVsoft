package com.mequa.mequasovsoft.MODAL;

import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mequa.mequasovsoft.Util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@IgnoreExtraProperties
public class Medicao implements Comparable<Medicao>{

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

    public String datamedicao;

    public String getDatamedicao() {
        return datamedicao;
    }

    @Exclude
    public void setDatamedicao(String datamedicao) {
        datamedicao = datamedicao;
    }

    public void setDatamedicaoDate(Date datamedicao) {
        this.datamedicao = Util.convertDateToString(datamedicao);
    }

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

    @Exclude
    @Override
    public int compareTo(@NonNull Medicao o) {
        if (getDatamedicao() == null || o.getDatamedicao() == null)
            return 0;
        try {
            return (Util.convertStringTodate(getDatamedicao())).compareTo( Util.convertStringTodate(o.getDatamedicao()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
