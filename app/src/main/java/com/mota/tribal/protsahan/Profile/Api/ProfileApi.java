package com.mota.tribal.protsahan.Profile.Api;

import com.mota.tribal.protsahan.Helper.Urls;
import com.mota.tribal.protsahan.Profile.Model.Data.Profile;
import com.mota.tribal.protsahan.Profile.Model.Data.ProfileData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Abhi on 10-Mar-18.
 */

public interface ProfileApi {

    @POST(Urls.SAVE_PROFILE)
    Call<ProfileData> postProfile(@Field("profile") Profile profile);

    @GET(Urls.GET_PROFILE)
    Call<ProfileData> getProfile(@Field("id") String id);

}
