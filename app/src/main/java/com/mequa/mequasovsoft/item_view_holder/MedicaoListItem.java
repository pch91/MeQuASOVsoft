package com.mequa.mequasovsoft.item_view_holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.mequa.mequasovsoft.BO.PlantaBO;
import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.MODAL.Planta;
import com.mequa.mequasovsoft.R;
import com.mequa.mequasovsoft.Util.Setings;
import com.mequa.mequasovsoft.viewHolder.MedicaoListViewHouder;
import com.xwray.groupie.Item;

import org.json.JSONException;

import java.io.IOException;

public class MedicaoListItem extends Item<MedicaoListViewHouder> {

    Medicao medicao;
    Context c;

    public MedicaoListItem(Medicao m,Context c) {
        this.medicao = m;
        this.c = c;
    }

    @Override
    public void bind(@NonNull MedicaoListViewHouder viewHolder, final int position) {
        viewHolder.temperatura.setText(String.valueOf(medicao.getTemperatura()));
        viewHolder.humidade.setText(String.valueOf(medicao.getUmidadear()));
        viewHolder.humidadesolo.setText(String.valueOf(medicao.getUmidade()));
        viewHolder.chovendo.setText(medicao.getChuvendo().equals(true) ? "Sim" : "Não");

        PlantaBO pbo = new PlantaBO();
        try {
            Planta planta = pbo.getPlanta(medicao.getPlanta(),c);
            String[] temp = planta.getTemperatura().split(",");
            String[] humsolo = planta.getUmidade().split(",");
            String[] hum = planta.getUmidade().split(",");

            if(Integer.parseInt(temp[0]) < medicao.getTemperatura() && Integer.parseInt(temp[1]) > medicao.getTemperatura() ){
                viewHolder.temperaturalike.setVisibility(View.VISIBLE);
            }else{
                viewHolder.temperaturaunlike.setVisibility(View.VISIBLE);
            }

            if(Double.parseDouble(humsolo[0]) < medicao.getUmidade() && Double.parseDouble(humsolo[1]) > medicao.getUmidade()){
                viewHolder.humidadelike.setVisibility(View.VISIBLE);
            }else{
                viewHolder.humidadeunlike.setVisibility(View.VISIBLE);
            }

            if(Double.parseDouble(hum[0]) < medicao.getUmidadear() && Double.parseDouble(hum[1]) > medicao.getUmidadear()) {
                viewHolder.humidadesololike.setVisibility(View.VISIBLE);
            }else {
                viewHolder.humidadesolounlike.setVisibility(View.VISIBLE);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.medicaoitem;
    }

    @NonNull
    @Override
    public MedicaoListViewHouder createViewHolder(@NonNull View itemView) {
        return new MedicaoListViewHouder(itemView);
    }
}
