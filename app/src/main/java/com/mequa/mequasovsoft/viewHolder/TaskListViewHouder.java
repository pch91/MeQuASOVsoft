package com.mequa.mequasovsoft.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.mequa.mequasovsoft.R;
import com.xwray.groupie.ViewHolder;


public class TaskListViewHouder extends ViewHolder {

    //elementos da viel que vai dentro do recicle view
    public TextView Desc;

    public TaskListViewHouder(@NonNull View rootView) {
        super(rootView);

        //Desc = itemView.findViewById(R.id.tasldescRc);
    }
}

