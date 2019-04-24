package com.mequa.mequasovsoft.item_view_holder;

import android.support.annotation.NonNull;
import android.view.View;

import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.R;
import com.mequa.mequasovsoft.viewHolder.MedicaoListViewHouder;
import com.xwray.groupie.Item;

public class MedicaoListItem extends Item<MedicaoListViewHouder> {

    Medicao medicao;

    public MedicaoListItem(Medicao m) {
        this.medicao = m;
    }

    @Override
    public void bind(@NonNull MedicaoListViewHouder viewHolder, final int position) {
        viewHolder.temperatura.setText(String.valueOf(medicao.getTemperatura()));
        viewHolder.humidade.setText(String.valueOf(medicao.getUmidadear()));
        viewHolder.humidadesolo.setText(String.valueOf(medicao.getUmidade()));
        viewHolder.chovendo.setText(medicao.getChuvendo() ? "Sim" : "Não");
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
