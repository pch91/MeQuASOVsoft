package com.mequa.mequasovsoft.DAO;

import android.content.Context;

import com.google.android.gms.common.util.Strings;
import com.mequa.mequasovsoft.MODAL.*;

import java.io.IOException;
import java.lang.ref.Reference;

public class MedicaoDAO  extends ComunsDAO  {


    public void add(User u,Medicao medicao, Context c) throws IOException {
        if(Strings.isEmptyOrWhitespace(medicao.getId())){
            try {
                medicao.setId(createTransactionID());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Refdatabase.child(u.getClass().getSimpleName().toLowerCase()+"/"+u.getCPF()+
                "/"+medicao.getClass().getSimpleName().toLowerCase()+"/"+medicao.getId()).setValue(medicao);
        Refdatabase.child(u.getClass().getSimpleName().toLowerCase()+"/"+u.getCPF()+
                "/Plantas/"+medicao.getPlanta()+"/"+medicao.getClass().getSimpleName().toLowerCase()+"/"+medicao.getId()).setValue(medicao);

        Refdatabase.keepSynced(true);
    }
}
