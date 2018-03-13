package com.mota.tribal.protsahan.Profile;

import com.mota.tribal.protsahan.Profile.Model.Data.ProfileData;

/**
 * Created by Abhi on 13-Mar-18.
 */

public interface ProfileCallback {
    void onSuccess(ProfileData body);

    void onFailure();
}
