package com.guru.login;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by johan on 06/09/16.
 */
public interface RequestInterface {

    MerekBan tarikban = new MerekBan();
     //public static final String email = tarikmenuban.getEmail().toString();
    String jsonandroids="jsonandroid";
    public final static String email =tarikban.getEmail().toString();
    @GET("rita@gmail.com")
    Call<JSONResponse> getJSON();
}


