package com.mota.tribal.protsahan.Profile.Presenter;

import android.content.Context;

import com.mota.tribal.protsahan.Profile.Model.Data.Profile;
import com.mota.tribal.protsahan.Profile.Model.Data.ProfileData;
import com.mota.tribal.protsahan.Profile.Model.ProfileProvider;
import com.mota.tribal.protsahan.Profile.ProfileCallback;
import com.mota.tribal.protsahan.Profile.View.ProfileView;
import com.mota.tribal.protsahan.R;

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
    public void getProfile(String id) {
        view.showProgressBar(true);
        provider.getProfile(id, new ProfileCallback() {
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
}
