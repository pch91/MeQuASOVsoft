package com.mequa.mequasovsoft.BO;

import android.content.Context;

import com.mequa.mequasovsoft.CALBACKS.FireBaseCalback;
import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.MODAL.Planta;
import com.mequa.mequasovsoft.DAO.PlantaDAO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class PlantaBO {

    PlantaDAO plantadao = new PlantaDAO();

    public void setEventiListenerMedicao(Context c) throws IllegalAccessException, InstantiationException{
        plantadao.setEventiListener(Planta.class, "", "",new FireBaseCalback() {
            Context c;

            FireBaseCalback FireBaseCalback(Context c){
                this.c = c;
             return this;
            }

            @Override
            public <T> void onCalback(List<T> list) {
                for (T p:list) {
                    try {
                        add((Planta) p,c);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.FireBaseCalback(c));
    }

    public void add(Planta p, Context c) throws IOException {
        FileOutputStream output = c.openFileOutput("Plantas.txt", Context.MODE_PRIVATE);
        OutputStreamWriter escreve = new OutputStreamWriter(output);

        escreve.append(p.to_jeson());

        escreve.append("\n");
        escreve.close();
        output.close();
    }

    public List<Planta> listaAll(Context c) throws IOException, JSONException {
        List<Planta> lp = new ArrayList<Planta>();
        InputStream inputStream = c.openFileInput("Plantas.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String recebe_string;


        while((recebe_string = bufferedReader.readLine())!=null){
            lp.add(new Planta(new JSONObject(recebe_string)));
        }

        inputStreamReader.close();
        bufferedReader.close();
        return lp;
    }

    public Planta getPlanta(String PlantaName, Context c) throws IOException, JSONException {
        Planta p = new Planta();
        InputStream inputStream = c.openFileInput("Plantas.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String recebe_string;


        while((recebe_string = bufferedReader.readLine())!=null){
            p = new Planta(new JSONObject(recebe_string));

            if(p.getNome().equals(PlantaName)){
                break;
            }
        }
        inputStreamReader.close();
        bufferedReader.close();
        return p;
    }
    public void removePlanta(String PlantaName, Context c) throws IOException, JSONException {
        List<Planta> s = new ArrayList<>();
        Planta p = new Planta();
        InputStream inputStream = c.openFileInput("Plantas.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String recebe_string;


        while((recebe_string = bufferedReader.readLine())!=null){
            p = new Planta(new JSONObject(recebe_string));

            if(!(p.getNome().equals(PlantaName))){
                s.add(p);
            }
        }

        bufferedReader.close();
        inputStreamReader.close();
        FileOutputStream output = c.openFileOutput("Plantas.txt", Context.MODE_PRIVATE);
        OutputStreamWriter escreve = new OutputStreamWriter(output);

        for (Planta up: s) {
            escreve.append(up.to_jeson());
            escreve.append("\n");
        }
        escreve.flush();
        escreve.close();
        output.close();
    }



}
