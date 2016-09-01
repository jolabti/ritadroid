package com.guru.login;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by johan on 25/08/16.
 */
public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/rjbanadmin/masukkan.php")
    Call<PojoDemo> serverCall(

            @Field("id_user") String iduser,
            @Field("kd_ban") String kodban,
            @Field("pesanan") int pesanan,
            @Field("email") String Email,
            @Field("nama_lengkap") String namalengkap,
            @Field("alamat") String alamat,
            @Field("no_tlp") String telpon,
            @Field("total") int total


    );

}


