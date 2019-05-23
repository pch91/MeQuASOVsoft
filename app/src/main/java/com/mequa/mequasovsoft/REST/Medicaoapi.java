package com.mequa.mequasovsoft.REST;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

import com.mequa.mequasovsoft.MODAL.*;

public interface Medicaoapi {

    @Headers("Content-Type: application/json")
    @GET("/")
    Call<Medicao> loaddescription();

}
