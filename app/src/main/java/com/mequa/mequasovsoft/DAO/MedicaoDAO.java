package com.mequa.mequasovsoft.DAO;

import android.content.Context;

import com.mequa.mequasovsoft.MODAL.*;

import java.io.IOException;

public class MedicaoDAO  extends ComunsDAO  {


    public void add(User u,Medicao medicao, Context c) throws IOException {
        Refdatabase.child(u.getClass().getSimpleName().toLowerCase()+"/"+u.getCPF()+
                "/"+medicao.getClass().getSimpleName().toLowerCase()+"/"+medicao.getId()).setValue(medicao);
        Refdatabase.child(u.getClass().getSimpleName().toLowerCase()+"/"+u.getCPF()+
                "/Plantas/"+medicao.getPlanta()+"/"+medicao.getClass().getSimpleName().toLowerCase()+"/"+medicao.getId()).setValue(medicao);
    }
}
