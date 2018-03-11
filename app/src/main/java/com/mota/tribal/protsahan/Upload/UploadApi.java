package com.mota.tribal.protsahan.Upload;

import com.mota.tribal.protsahan.Helper.Urls;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadApi {

    @Multipart
    @POST(Urls.VIDEO_UPLOAD)
    Call<Data> uploadVideoToServer(@Part("id") String access_token, @Part MultipartBody.Part video);

    @Multipart
    @POST(Urls.IMG_UPLOAD)
    Call<Data> uploadImageToServer(@Part("id") String access_token, @Part MultipartBody.Part image);

    @Multipart
    @POST(Urls.DOC_UPLOAD)
    Call<Data> uploadDocToServer(@Part("id") String access_token, @Part MultipartBody.Part doc);

}
