package com.mota.tribal.protsahan.Query.Model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mota.tribal.protsahan.Query.Api.RetrofitApi;
import com.mota.tribal.protsahan.Query.Model.Data.Data;
import com.mota.tribal.protsahan.Query.OnCallback;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitData implements DataProvider {
    private final Retrofit retrofit;
    private RetrofitApi retrofitApi;

    public RetrofitData() {
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
    public void get(String username, String password, final OnCallback callback) {
        retrofitApi = retrofit.create(RetrofitApi.class);
        Call<Data> call = retrofitApi.getUserDetails(username, password);
        call.enqueue(new Callback<Data>() {

            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Log.d("Ayush", response.toString());
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}