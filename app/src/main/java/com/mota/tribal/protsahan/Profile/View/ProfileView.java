package com.mota.tribal.protsahan.Profile.View;

import com.mota.tribal.protsahan.Profile.Model.Data.Profile;

import java.util.ArrayList;

public interface ProfileView {


    void showMessage(String message);


    void showProgressBar(boolean b);

    void onProfilePosted();

    void onProfileGet(Profile profile);

    void showVideos(ArrayList<String> urls);

    void showImages(ArrayList<String> urls);

    void showDocs(ArrayList<String> urls);

}
