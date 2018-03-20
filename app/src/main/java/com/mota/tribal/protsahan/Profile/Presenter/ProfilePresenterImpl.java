package com.mota.tribal.protsahan.Profile.Presenter;

import android.content.Context;

import com.mota.tribal.protsahan.Profile.Model.Data.Profile;
import com.mota.tribal.protsahan.Profile.Model.Data.ProfileData;
import com.mota.tribal.protsahan.Profile.Model.Data.VidImgDocData;
import com.mota.tribal.protsahan.Profile.Model.ProfileProvider;
import com.mota.tribal.protsahan.Profile.ProfileCallback;
import com.mota.tribal.protsahan.Profile.VidImgDocCallback;
import com.mota.tribal.protsahan.Profile.View.ProfileView;
import com.mota.tribal.protsahan.R;

import okhttp3.MultipartBody;

/**
 * Created by Abhi on 10-Mar-18.
 */

public class ProfilePresenterImpl implements ProfilePresenter {

    private ProfileView view;
    private ProfileProvider provider;
    private Context context;

    public ProfilePresenterImpl(ProfileView view, ProfileProvider provider, Context context) {
        this.view = view;
        this.provider = provider;
        this.context = context;
    }

    @Override
    public void getProfile(String id, String username) {
        view.showProgressBar(true);
        provider.getProfile(id, username, new ProfileCallback() {
            @Override
            public void onSuccess(ProfileData body) {
                view.showProgressBar(false);
                if (body.isSuccess())
                    view.onProfileGet(body.getProfile());
                else
                    view.showMessage(body.getMessage());
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showMessage(context.getString(R.string.ConnectionError));
            }
        });
    }

    @Override
    public void postProfile(Profile profile) {
        view.showProgressBar(true);
        provider.postProfile(profile, new ProfileCallback() {
            @Override
            public void onSuccess(ProfileData body) {
                view.showProgressBar(false);
                if (body.isSuccess())
                    view.onProfilePosted();
                else
                    view.showMessage(body.getMessage());
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showMessage(context.getString(R.string.ConnectionError));
            }
        });
    }

    @Override
    public void getVideos(String token, String username) {
        view.showProgressBar(true);
        provider.getVideos(token, username, new VidImgDocCallback() {
            @Override
            public void onSuccess(VidImgDocData body) {
                view.showProgressBar(false);
                view.showVideos(body.getUrls());
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);

            }
        });
    }

    @Override
    public void getImages(String token, String username) {
        view.showProgressBar(true);
        provider.getImages(token, username, new VidImgDocCallback() {
            @Override
            public void onSuccess(VidImgDocData body) {
                view.showProgressBar(false);
                view.showImages(body.getUrls());
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
            }
        });
    }

    @Override
    public void getDocs(String token, String username) {
        view.showProgressBar(true);
        provider.getDocs(token, username, new VidImgDocCallback() {
            @Override
            public void onSuccess(VidImgDocData body) {
                view.showProgressBar(false);
                view.showDocs(body.getUrls());
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
            }
        });
    }

    @Override
    public void postProfilePic(String token, String username, MultipartBody.Part file) {
        view.showProgressBar(true);
        provider.postProfilePic(token, username, file, new ProfileCallback() {
            @Override
            public void onSuccess(ProfileData body) {
                view.showProgressBar(false);
                if (body.isSuccess())
                    view.showMessage("Profile Pic Saved!");
                else
                    view.showMessage(body.getMessage());
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showMessage(context.getString(R.string.ConnectionError));
            }
        });
    }
}