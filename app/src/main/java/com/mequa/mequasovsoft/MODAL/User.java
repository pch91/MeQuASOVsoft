package com.mequa.mequasovsoft.MODAL;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String id;
    public String cpf;
    public String senha;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String CPF) {
        this.cpf = CPF;
    }

    public String getCell() {
        return senha;
    }

    public void setCell(String senha) {
        this.senha = senha;
    }
}
