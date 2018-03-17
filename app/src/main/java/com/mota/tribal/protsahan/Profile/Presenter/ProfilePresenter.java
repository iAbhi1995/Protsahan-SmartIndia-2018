package com.mota.tribal.protsahan.Profile.Presenter;

import com.mota.tribal.protsahan.Profile.Model.Data.Profile;

/**
 * Created by Abhi on 10-Mar-18.
 */

public interface ProfilePresenter {
    void getProfile(String id);

    void postProfile(Profile profile);

    void getVideos(String id);

    void getImages(String id);

    void getDocs(String id);
}
