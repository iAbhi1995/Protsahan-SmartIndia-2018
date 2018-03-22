package com.mota.tribal.protsahan.Profile.Api;

import com.mota.tribal.protsahan.Helper.Urls;
import com.mota.tribal.protsahan.Profile.Model.Data.Profile;
import com.mota.tribal.protsahan.Profile.Model.Data.ProfileData;
import com.mota.tribal.protsahan.Profile.Model.Data.VidImgDocData;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ProfileApi {

    @POST(Urls.SAVE_PROFILE)
    Call<ProfileData> postProfile(@Field("profile") Profile profile);

    @GET(Urls.GET_PROFILE)
    Call<ProfileData> getProfile(@Field("username") String username, @Field("token") String token);

    @GET(Urls.GET_VIDEO)
    Call<VidImgDocData> getVideos(@Field("username") String username, @Field("token") String token);

    @GET(Urls.GET_IMAGE)
    Call<VidImgDocData> getImages(@Field("username") String username, @Field("token") String token);

    @GET(Urls.GET_DOCUMENTS)
    Call<VidImgDocData> getDocuments(@Field("username") String username, @Field("token") String token);

    @POST(Urls.SAVE_PROFILE_PIC)
    Call<ProfileData> postProfilePic(@Part("token") String token, @Part("username") String username, @Part MultipartBody.Part picture);

}
