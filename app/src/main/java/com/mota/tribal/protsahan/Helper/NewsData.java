package com.mota.tribal.protsahan.Helper;

/**
 * Created by ayush on 30-03-2018.
 */

public class NewsData {

    public final String title;
    public final String description;
    public final String imgurl;

    public NewsData(String title, String description, String imgurl) {
        this.title = title;
        this.description = description;
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getDescription() {
        return description;
    }

}
