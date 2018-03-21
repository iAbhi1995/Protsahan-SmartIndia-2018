package com.mota.tribal.protsahan.ViewAllProfiles.View;

import com.mota.tribal.protsahan.ViewAllProfiles.Model.Data.ViewProfilesData;

import java.util.ArrayList;

/**
 * Created by Abhi on 20-Mar-18.
 */

public interface ViewProfilesView {
    void showProgressBar(boolean b);

    void showMessage(String message);

    void showResult(ArrayList<ViewProfilesData.Docs> docs);
}
