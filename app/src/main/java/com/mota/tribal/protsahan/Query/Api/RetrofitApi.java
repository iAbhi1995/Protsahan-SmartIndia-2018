package com.mota.tribal.protsahan.Query.Api;

import com.mota.tribal.protsahan.Query.Model.Data.Data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitApi {

    @FormUrlEncoded
    @POST("artisans/login")
    Call<Data> getUserDetails(@Field("username") String username, @Field("password") String password);


}

