package com.mota.tribal.protsahan.Profile.Model.Data;


import java.util.ArrayList;

public class DeleteData {
    private boolean success;
    private Docs docs;

    public DeleteData(boolean success, Docs docs) {
        this.success = success;
        this.docs = docs;
    }

    public boolean isSuccess() {
        return success;
    }

    public Docs getDocs() {
        return docs;
    }


    public static class Docs {
        private ArrayList<VidImgDocData.Obj> videos, images;

        public Docs(ArrayList<VidImgDocData.Obj> videos, ArrayList<VidImgDocData.Obj> images) {
            this.videos = videos;
            this.images = images;
        }

        public ArrayList<VidImgDocData.Obj> getVideos() {
            return videos;
        }

        public ArrayList<VidImgDocData.Obj> getImages() {
            return images;
        }
    }

}
