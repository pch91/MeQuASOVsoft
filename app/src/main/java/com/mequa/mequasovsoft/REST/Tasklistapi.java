package com.mequa.mequasovsoft.REST;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import com.mequa.mequasovsoft.MODAL.*;

public interface Tasklistapi {

    @GET("tasks/")
    Call<Medicao> loaddescription(@Header("Authorization") String authorization);

}
