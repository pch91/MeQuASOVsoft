package com.mequa.mequasovsoft.BO;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.mequa.mequasovsoft.CALBACKS.FireBaseCalback;
import com.mequa.mequasovsoft.CALBACKS.UserCalback;
import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.MODAL.Planta;
import com.mequa.mequasovsoft.MODAL.User;
import com.mequa.mequasovsoft.DAO.UserDao;
import com.mequa.mequasovsoft.Util.Setings;
import com.mequa.mequasovsoft.Util.Util;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class UserBO {

    UserDao Userdao =  new UserDao();

    public void getUser(FireBaseCalback fireBaseCalback, User user) throws IllegalAccessException, InstantiationException{
        Userdao.getObject(User.class, "cadastros", user.getCPF(),fireBaseCalback);
    }

    public void Login(View view, UserCalback UserCalback, User user) throws IllegalAccessException, InstantiationException{
        Userdao.getObject(User.class, "cadastros", user.getCPF(), new FireBaseCalback() {
            User user;
            View view;
            UserCalback UserCalback;

            public FireBaseCalback load(View view,UserCalback UserCalback, User user ){
                this.user = user;
                this.view = view;
                this.UserCalback = UserCalback;
                return this;
            }

            @Override
            public <T> void onCalback(List<T> list) {
                List<User> lu = (List<User>) list;

                for (User u:lu) {
                    if(u.getCPF().equals(this.user.getCPF()) && Util.castMD5(this.user.getSenha()).equals(u.getSenha())){
                        Setings.user = u;
                        UserCalback.onCalback(u);
                    }else{
                        Snackbar.make(view, "Senha ou cpf Incorreto.", Snackbar.LENGTH_LONG).show();
                    }
                }
                if(lu.size() < 1)
                    Snackbar.make(view, "Usuario não cadastrado.", Snackbar.LENGTH_LONG).show();
            }
        }.load(view, UserCalback ,user));
    }

    public void add(User u,Context c,UserCalback userCalback){
        try {
            u.setSenha(Util.castMD5(u.getSenha()));
            Userdao.add(u,c);
            Setings.user = u;
            userCalback.onCalback(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addFile(User u, Context c) throws IOException {
        FileOutputStream output = c.openFileOutput("User.txt", Context.MODE_PRIVATE);
        OutputStreamWriter escreve = new OutputStreamWriter(output);
        escreve.append(u.to_jeson());
        escreve.append("\n");
        escreve.close();
        output.close();
    }

    public User verificaLogado(String CPF, Context c) throws IOException, JSONException {
        User u = null;
        InputStream inputStream = c.openFileInput("User.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String recebe_string;


        while((recebe_string = bufferedReader.readLine())!=null){
            u = new User(new JSONObject(recebe_string));

            if(u.getCPF() != null){
                break;
            }
        }
        inputStreamReader.close();
        bufferedReader.close();
        return u;
    }

    public void Deslogando(Context c) throws IOException, JSONException {
        FileOutputStream output = c.openFileOutput("User.txt", Context.MODE_PRIVATE);
        OutputStreamWriter escreve = new OutputStreamWriter(output);
        escreve.append("");
        escreve.close();
        output.close();
    }
}
