package com.mota.tribal.protsahan.Profile;

import com.mota.tribal.protsahan.Profile.Model.Data.VidImgDocData;

/**
 * Created by Abhi on 15-Mar-18.
 */

public interface VidImgDocCallback {
    void onSuccess(VidImgDocData body);

    void onFailure();
}
