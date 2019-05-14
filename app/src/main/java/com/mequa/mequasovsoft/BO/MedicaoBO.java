package com.mequa.mequasovsoft.BO;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.mequa.mequasovsoft.CALBACKS.FireBaseCalback;
import com.mequa.mequasovsoft.CALBACKS.MedicaoApiBaseCalback;
import com.mequa.mequasovsoft.DAO.MedicaoDAO;
import com.mequa.mequasovsoft.ListMedicao;
import com.mequa.mequasovsoft.MODAL.Medicao;
import com.mequa.mequasovsoft.MODAL.User;
import com.mequa.mequasovsoft.R;
import com.mequa.mequasovsoft.REST.Medicaoapi;
import com.mequa.mequasovsoft.Util.Setings;

import java.io.IOException;

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

    public Medicao medir(View view, Context c, MedicaoApiBaseCalback callback) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (Setings.planta != null) {

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().build();
                    return chain.proceed(request);
                }
            });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(c.getString(R.string.url))
                    .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build())
                    .build();

            Medicaoapi medicaoapi = retrofit.create(Medicaoapi.class);
            Call<Medicao> clmedicao = medicaoapi.loaddescription();

            clmedicao.enqueue(new Callback<Medicao>() {
                Context c;
                View view;
                MedicaoApiBaseCalback callb;

                public Callback<Medicao> load(View view,Context c, MedicaoApiBaseCalback call) {
                    this.c = c;
                    this.callb = call;
                    this.view = view;
                    return this;
                }

                @Override
                public void onResponse(Call<Medicao> call, retrofit2.Response<Medicao> response) {
                    if (response.isSuccessful()) {
                        Medicao medicao = response.body();
                        medicao.setPlanta(Setings.planta.getNome());
                        add(Setings.user, medicao, c);
                        if (callb != null) {
                            callb.onCalback(medicao);
                        }

                        Snackbar.make(view, "Medida Realizada", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Medicao> call, Throwable t) {
                    int a = 4;
                }
            }.load(view,c, callback));

        } else {

            Snackbar.make(view, "Por favor, Selecione uma planta.", Snackbar.LENGTH_LONG).show();
        }
        return null;
    }
}
