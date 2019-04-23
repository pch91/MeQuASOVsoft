package com.mequa.mequasovsoft.BO;

import com.mequa.mequasovsoft.CALBACKS.FireBaseCalback;
import com.mequa.mequasovsoft.DAO.MedicaoDAO;
import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.MODAL.User;

public class MedicaoBO {
    MedicaoDAO listaPromo =  new MedicaoDAO();

    public void setEventiListenerMedicao(FireBaseCalback fireBaseCalback, User user) throws IllegalAccessException, InstantiationException{
        listaPromo.getObject(Medicao.class, user.getCPF(), "",fireBaseCalback);
    }



}
