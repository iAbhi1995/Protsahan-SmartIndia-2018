package com.mota.tribal.protsahan.Profile.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mota.tribal.protsahan.Helper.Urls;
import com.mota.tribal.protsahan.Profile.Api.ProfileApi;
import com.mota.tribal.protsahan.Profile.Model.Data.Profile;
import com.mota.tribal.protsahan.Profile.Model.Data.ProfileData;
import com.mota.tribal.protsahan.Profile.ProfileCallback;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhi on 10-Mar-18.
 */

public class RetrofitProfileProvider implements ProfileProvider {

    private final Retrofit retrofit;
    private ProfileApi api;

    public RetrofitProfileProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(120, java.util.concurrent.TimeUnit.SECONDS).
                readTimeout(120, java.util.concurrent.TimeUnit.SECONDS).
                writeTimeout(120, java.util.concurrent.TimeUnit.SECONDS).
                addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }


    @Override
    public void getProfile(String id, final ProfileCallback callback) {
        api = retrofit.create(ProfileApi.class);
        Call<ProfileData> call = api.getProfile(id);
        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    @Override
    public void postProfile(Profile profile, final ProfileCallback callback) {

        api = retrofit.create(ProfileApi.class);
        Call<ProfileData> call = api.postProfile(profile);
        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });

    }
}
