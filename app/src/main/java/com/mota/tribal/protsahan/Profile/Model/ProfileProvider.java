package com.mota.tribal.protsahan.Profile.Model;

import com.mota.tribal.protsahan.Profile.Model.Data.Profile;
import com.mota.tribal.protsahan.Profile.ProfileCallback;

/**
 * Created by Abhi on 10-Mar-18.
 */

public interface ProfileProvider {
    void getProfile(String id, ProfileCallback callback);

    void postProfile(Profile profile, ProfileCallback callback);
}
