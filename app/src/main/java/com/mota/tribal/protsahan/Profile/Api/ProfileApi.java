package com.mota.tribal.protsahan.Profile.Api;

import com.mota.tribal.protsahan.Helper.Urls;
import com.mota.tribal.protsahan.Profile.Model.Data.DeleteData;
import com.mota.tribal.protsahan.Profile.Model.Data.ProfileData;
import com.mota.tribal.protsahan.Profile.Model.Data.VidImgDocData;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ProfileApi {

    @FormUrlEncoded
    @POST(Urls.SAVE_PROFILE)
    Call<ProfileData> postProfile(@Field("username") String username, @Field("token") String token,
                                  @Field("name") String name, @Field("description") String description,
                                  @Field("tribe") String tribe, @Field("address") String address,
                                  @Field("aadharNO") String aadharNO, @Field("phone") String phone,
                                  @Field("gender") String gender, @Field("state") String state);

    @FormUrlEncoded
    @POST(Urls.GET_PROFILE)
    Call<ProfileData> getProfile(@Field("username") String username, @Field("token") String token);

    @FormUrlEncoded
    @POST(Urls.GET_VIDEO)
    Call<VidImgDocData> getVideos(@Field("token") String token, @Field("username") String username);

    @FormUrlEncoded
    @POST(Urls.GET_IMAGE)
    Call<VidImgDocData> getImages(@Field("token") String token, @Field("username") String username);

    @FormUrlEncoded
    @POST(Urls.GET_DOCUMENTS)
    Call<VidImgDocData> getDocuments(@Field("token") String token, @Field("username") String username);

    @Multipart
    @POST(Urls.SAVE_PROFILE_PIC)
    Call<ProfileData> postProfilePic(@Part("token") String token, @Part("username") String username, @Part MultipartBody.Part picture);

    @FormUrlEncoded
    @POST(Urls.DELETE_IMAGE)
    Call<DeleteData> deleteImage(@Field("token") String token, @Field("username") String username,
                                 @Field("_id") String id, @Field("title") String imageTitle);

    @FormUrlEncoded
    @POST(Urls.DELETE_VIDEO)
    Call<DeleteData> deleteVideo(@Field("token") String token, @Field("username") String username,
                                 @Field("_id") String id, @Field("title") String videoTitle);

}
