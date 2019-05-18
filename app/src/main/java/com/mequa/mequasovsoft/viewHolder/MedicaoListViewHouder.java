package com.mequa.mequasovsoft.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.R;
import com.xwray.groupie.ViewHolder;


public class MedicaoListViewHouder extends ViewHolder {

    //elementos da viel que vai dentro do recicle view
    public TextView temperatura;
    public TextView chovendo;
    public TextView humidade;
    public TextView humidadesolo;
    public TextView planta;
    public TextView date;

    public ImageView humidadesololike;
    public ImageView humidadelike;
    public ImageView temperaturalike;

    public MedicaoListViewHouder(@NonNull View rootView) {
        super(rootView);

        temperatura = itemView.findViewById(R.id.valuetemperatura);
        chovendo = itemView.findViewById(R.id.valuechovendo);
        humidade = itemView.findViewById(R.id.valuehumidade);
        humidadesolo = itemView.findViewById(R.id.valuehumidadesolo);
        humidadesololike = itemView.findViewById(R.id.valuehumidadesololike);
        humidadelike = itemView.findViewById(R.id.valuehumidadelike);
        temperaturalike = itemView.findViewById(R.id.valuetemperaturalike);
        planta = itemView.findViewById(R.id.textnomePlanta);
        date = itemView.findViewById(R.id.textData);

    }
}

