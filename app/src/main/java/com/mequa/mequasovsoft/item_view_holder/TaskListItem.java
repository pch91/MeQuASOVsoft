package com.mequa.mequasovsoft.item_view_holder;

import android.support.annotation.NonNull;
import android.view.View;

import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.R;
import com.mequa.mequasovsoft.viewHolder.TaskListViewHouder;
import com.xwray.groupie.Item;

public class TaskListItem extends Item<TaskListViewHouder> {

    Medicao medicao;

    public TaskListItem(Medicao m) {
        this.medicao = m;
    }

    @Override
    public void bind(@NonNull TaskListViewHouder viewHolder, final int position) {
        //viewHolder.Desc.setText(medicao.getDescription());
    }

    @Override
    public int getLayout() {
        return 0 ; //R.layout.task_list_item;
    }

    @NonNull
    @Override
    public TaskListViewHouder createViewHolder(@NonNull View itemView) {
        return new TaskListViewHouder(itemView);
    }
}
