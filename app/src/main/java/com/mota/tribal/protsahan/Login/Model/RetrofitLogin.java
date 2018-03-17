package com.mota.tribal.protsahan.Login.Model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mota.tribal.protsahan.Login.Api.LoginApi;
import com.mota.tribal.protsahan.Login.LoginCallback;
import com.mota.tribal.protsahan.Login.Model.Data.UserInfo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitLogin implements LoginProvider {
    private final Retrofit retrofit;
    private LoginApi loginApi;

    public RetrofitLogin() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(15000, java.util.concurrent.TimeUnit.SECONDS).
                readTimeout(15000, java.util.concurrent.TimeUnit.SECONDS).
                writeTimeout(15000, java.util.concurrent.TimeUnit.SECONDS).
                addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.3:3000/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }


    @Override
    public void getUser(String email, String password, final LoginCallback callback) {
        loginApi = retrofit.create(LoginApi.class);
        Call<UserInfo> call = loginApi.getUserDetails(email, password);
        call.enqueue(new Callback<UserInfo>() {

            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                Log.d("Ayush", response.toString());
                // callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}