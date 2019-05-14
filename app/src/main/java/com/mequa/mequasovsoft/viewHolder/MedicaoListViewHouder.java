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
    public ImageView humidadesololike;
    public ImageView humidadesolounlike;
    public ImageView humidadeunlike;
    public ImageView humidadelike;
    public ImageView temperaturalike;
    public ImageView temperaturaunlike;

    public MedicaoListViewHouder(@NonNull View rootView) {
        super(rootView);

        temperatura = itemView.findViewById(R.id.valuetemperatura);
        chovendo = itemView.findViewById(R.id.valuechovendo);
        humidade = itemView.findViewById(R.id.valuehumidade);
        humidadesolo = itemView.findViewById(R.id.valuehumidadesolo);
        humidadesololike = itemView.findViewById(R.id.valuehumidadesololike);
        humidadesolounlike = itemView.findViewById(R.id.valuehumidadesolounlike);
        humidadelike = itemView.findViewById(R.id.valuehumidadelike);
        humidadeunlike = itemView.findViewById(R.id.valuehumidadeunlike);
        temperaturalike = itemView.findViewById(R.id.valuetemperaturaunlike);
        temperaturaunlike = itemView.findViewById(R.id.valuetemperaturalike);


    }
}

