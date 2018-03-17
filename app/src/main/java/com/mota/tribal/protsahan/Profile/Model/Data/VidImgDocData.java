package com.mota.tribal.protsahan.Profile.Model.Data;

import java.util.ArrayList;

/**
 * Created by Abhi on 15-Mar-18.
 */

public class VidImgDocData {
    private boolean success;
    private ArrayList<String> urls;

    public VidImgDocData(boolean success, ArrayList<String> urls) {
        this.success = success;
        this.urls = urls;
    }

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }
}
