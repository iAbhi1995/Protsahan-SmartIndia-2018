package com.mota.tribal.protsahan.Profile.View;

import com.mota.tribal.protsahan.Profile.Model.Data.Profile;

public interface ProfileView {


    void showMessage(String message);


    void showProgressBar(boolean b);

    void onProfilePosted();

    void onProfileGet(Profile profile);
}
