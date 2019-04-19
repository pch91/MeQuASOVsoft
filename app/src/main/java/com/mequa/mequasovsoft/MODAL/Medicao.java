package com.mequa.mequasovsoft.MODAL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Medicao {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("done")
    @Expose
    private Boolean done;
    @SerializedName("id")
    @Expose
    private String id;

}
