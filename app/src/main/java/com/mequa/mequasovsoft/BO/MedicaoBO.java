package com.mequa.mequasovsoft.BO;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.mequa.mequasovsoft.CALBACKS.FireBaseCalback;
import com.mequa.mequasovsoft.CALBACKS.MedicaoApiBaseCalback;
import com.mequa.mequasovsoft.DAO.MedicaoDAO;
import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.MODAL.User;
import com.mequa.mequasovsoft.R;
import com.mequa.mequasovsoft.REST.Medicaoapi;
import com.mequa.mequasovsoft.Util.Setings;
import com.mequa.mequasovsoft.Util.Util;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicaoBO {

    MedicaoDAO medicaoDao =  new MedicaoDAO();

    public void setEventiListenerMedicao(FireBaseCalback fireBaseCalback, User user) throws IllegalAccessException, InstantiationException{
        medicaoDao.setEventiListener(Medicao.class, user.getCPF(), "",fireBaseCalback);
    }

    public void add(User u,Medicao medicao, Context c){
        try {
            medicaoDao.add(u,medicao,c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Medicao medir(View view, Context c, MedicaoApiBaseCalback callback, View.OnClickListener wifi) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS).build();


        if (Setings.planta != null) {

            Util.showload("Medindo...");
            /*httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().build();
                    return chain.proceed(request);
                }
            });*/

            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(c.getString(R.string.url))
                        .client(httpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Medicaoapi medicaoapi = retrofit.create(Medicaoapi.class);
                Call<Medicao> clmedicao = medicaoapi.loaddescription();

                clmedicao.enqueue(new Callback<Medicao>() {
                    Context c;
                    View view;
                    MedicaoApiBaseCalback callb;
                    View.OnClickListener wifi;

                    public Callback<Medicao> load(View view,Context c, MedicaoApiBaseCalback call,View.OnClickListener wifi) {
                        this.c = c;
                        this.callb = call;
                        this.view = view;
                        this.wifi = wifi;
                        return this;
                    }

                    @Override
                    public void onResponse(Call<Medicao> call, retrofit2.Response<Medicao> response) {
                        if (response.isSuccessful()) {
                            Medicao medicao = response.body();
                            medicao.setPlanta(Setings.planta.getNome());
                            medicao.setDatamedicaoDate(new Date());
                            add(Setings.user, medicao, c);
                            if (callb != null) {
                                callb.onCalback(medicao);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<Medicao> call, Throwable t) {
                        Util.hidload("Medindo...");
                        Snackbar.make(view,R.string.msg_erro_wifi1,Snackbar.LENGTH_LONG)
                                .setDuration(2300).show();

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Snackbar.make(view, R.string.msg_erro_wifi2, Snackbar.LENGTH_LONG)
                                        .setDuration(8000)
                                        .setAction(R.string.msg_alerta_wifi, wifi).show();
                            }
                        }, 2301);
                    }
                }.load(view,c, callback,wifi));
            }catch (Exception e){
                Util.hidload("Medindo...");
                Snackbar.make(view, R.string.msg_erro_connectSuport, Snackbar.LENGTH_LONG).show();
            }
        } else {
            Util.hidload("Medindo...");
            Snackbar.make(view, R.string.msg_BO_selectpl, Snackbar.LENGTH_LONG).show();
        }
        return null;
    }
}
