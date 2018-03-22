package com.mota.tribal.protsahan.SignUp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ayush on 22-03-2018.
 */

public interface Api {
    @FormUrlEncoded
    @POST("artisans/register")
    Call<Data> getResponse(@Field("username") String username, @Field("password") String password);
}