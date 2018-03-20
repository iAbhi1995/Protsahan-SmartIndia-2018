package com.mota.tribal.protsahan.ViewAllProfiles;

import com.mota.tribal.protsahan.ViewAllProfiles.Model.Data.ViewProfilesData;

/**
 * Created by Abhi on 20-Mar-18.
 */

public interface ViewProfilesCallback {
    void onSuccess(ViewProfilesData body);

    void onFailure();
}
