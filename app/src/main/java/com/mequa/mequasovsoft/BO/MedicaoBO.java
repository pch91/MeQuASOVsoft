package com.mequa.mequasovsoft.BO;

import android.content.Context;

import com.mequa.mequasovsoft.CALBACKS.FireBaseCalback;
import com.mequa.mequasovsoft.DAO.MedicaoDAO;
import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.MODAL.User;

import java.io.IOException;

public class MedicaoBO {
    MedicaoDAO medicaoDao =  new MedicaoDAO();

    public void setEventiListenerMedicao(FireBaseCalback fireBaseCalback, User user) throws IllegalAccessException, InstantiationException{
        medicaoDao.getObject(Medicao.class, user.getCPF(), "",fireBaseCalback);
    }

    public void add(User u,Medicao medicao, Context c){
        try {
            medicaoDao.add(u,medicao,c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
