package com.guru.login;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by johan on 06/09/16.
 */
public interface RequestInterface {
    @GET("android/jsonandroid")
    Call<JSONResponse> getJSON();
}
