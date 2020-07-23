package com.mequa.mequasovsoft.MODAL;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public User(){};

    public User(JSONObject j) throws JSONException {
        this.cpf = j.getString("CPF");
        this.id = j.getString("CPF");
    }

    @Exclude
    public String to_jeson() {

        JSONObject obj = new JSONObject();

        try {
            obj.put("CPF", getCPF());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
}
