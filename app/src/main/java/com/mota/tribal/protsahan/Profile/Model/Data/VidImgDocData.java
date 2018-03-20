package com.mota.tribal.protsahan.Profile.Model.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class VidImgDocData {

    private ArrayList<Obj> images;
    private ArrayList<Obj> videos;

    public VidImgDocData(ArrayList<Obj> images, ArrayList<Obj> videos) {
        this.images = images;
        this.videos = videos;
    }

    public ArrayList<Obj> getImages() {
        return images;
    }

    public ArrayList<Obj> getVideos() {
        return videos;
    }


    public class Obj implements Serializable {
        private String url, title, _id;

        Obj(String url, String title, String id) {
            this.url = url;
            this.title = title;
            _id = id;
        }

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }

        public String get_id() {
            return _id;
        }
    }
}
