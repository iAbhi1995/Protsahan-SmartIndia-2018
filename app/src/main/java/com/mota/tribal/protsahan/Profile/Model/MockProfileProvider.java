package com.mota.tribal.protsahan.Profile.Model;

import android.os.Handler;

import com.mota.tribal.protsahan.Profile.Model.Data.Profile;
import com.mota.tribal.protsahan.Profile.Model.Data.ProfileData;
import com.mota.tribal.protsahan.Profile.Model.Data.VidImgDocData;
import com.mota.tribal.protsahan.Profile.ProfileCallback;
import com.mota.tribal.protsahan.Profile.VidImgDocCallback;

import java.util.ArrayList;

import okhttp3.MultipartBody;

/**
 * Created by Abhi on 14-Mar-18.
 */

public class MockProfileProvider implements ProfileProvider {


    @Override
    public void getProfile(String id, String username, final ProfileCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData1());
            }
        }, 500);
    }

    @Override
    public void postProfile(Profile profile, final ProfileCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData2());
            }
        }, 500);
    }

    @Override
    public void getVideos(String id, String username, final VidImgDocCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockVideoData());
            }
        }, 500);
    }

    @Override
    public void getImages(String id, String username, final VidImgDocCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockImageData());
            }
        }, 500);
    }

    @Override
    public void getDocs(String id, String username, final VidImgDocCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockDocData());
            }
        }, 500);
    }

    @Override
    public void postProfilePic(String token, String username, MultipartBody.Part file, final ProfileCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockPicUploadData());
            }
        }, 500);
    }

    public ProfileData getMockData1() {
        Profile profile = new Profile("01", "Danti", "Working", "Mahora",
                "Bastar", "123456789123", "8299231633", "Female",
                "http://www.echhattisgarh.co.in/images/bastar-tribal.jpg", "Akbar");
        ProfileData mockData1 = new ProfileData(true, "Success", profile);
        return mockData1;
    }

    public ProfileData getMockData2() {
        ProfileData mockData2 = new ProfileData(true, "success", null);
        return mockData2;
    }

    public VidImgDocData getMockVideoData() {
        ArrayList<String> urls = new ArrayList<>();
        urls.add("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
        urls.add("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
        urls.add("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
        urls.add("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
        urls.add("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
        VidImgDocData mockVideoData = new VidImgDocData(true, urls);
        return mockVideoData;
    }


    public VidImgDocData getMockImageData() {
        ArrayList<String> imagesList = new ArrayList<>();
        imagesList.add("https://upload.wikimedia.org/wikipedia/commons/2/23/Lake_mapourika_NZ.jpeg");
        imagesList.add("https://jpeg.org/images/jpeg2000-home.jpg");
        imagesList.add("https://www.gettyimages.ie/gi-resources/images/Homepage/Hero/UK/CMS_Creative_164657191_Kingfisher.jpg");
        imagesList.add("https://netdna.webdesignerdepot.com/uploads/2008/12/jpeg-format-illustration.jpg");
        imagesList.add("https://upload.wikimedia.org/wikipedia/commons/2/23/Lake_mapourika_NZ.jpeg");
        imagesList.add("https://www.gettyimages.ie/gi-resources/images/Homepage/Hero/UK/CMS_Creative_164657191_Kingfisher.jpgg");
        imagesList.add("https://netdna.webdesignerdepot.com/uploads/2008/12/jpeg-format-illustration.jpg");

        VidImgDocData mockImageData = new VidImgDocData(true, imagesList);
        return mockImageData;
    }


    public VidImgDocData getMockDocData() {

        ArrayList<String> docList = new ArrayList<>();
        docList.add("http://www.fisica.net/relatividade/stephen_hawking_a_brief_history_of_time.pdf");
        docList.add("http://www.fisica.net/relatividade/stephen_hawking_a_brief_history_of_time.pdf");
        docList.add("http://www.fisica.net/relatividade/stephen_hawking_a_brief_history_of_time.pdf");
        docList.add("http://www.fisica.net/relatividade/stephen_hawking_a_brief_history_of_time.pdf");
        docList.add("http://www.fisica.net/relatividade/stephen_hawking_a_brief_history_of_time.pdf");
        docList.add("http://www.fisica.net/relatividade/stephen_hawking_a_brief_history_of_time.pdf");
        VidImgDocData mockDocData = new VidImgDocData(true, docList);
        return mockDocData;
    }

    public ProfileData getMockPicUploadData() {
        ProfileData mockPicUploadData = new ProfileData(true, "true", null);
        return mockPicUploadData;
    }
}
