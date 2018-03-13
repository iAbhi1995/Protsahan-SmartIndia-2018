package com.mota.tribal.protsahan.Profile.Model.Data;

/**
 * Created by Abhi on 10-Mar-18.
 */

public class ProfileData {
    private boolean success;
    private String message;
    private Profile profile;

    public ProfileData(boolean success, String message, Profile profile) {
        this.success = success;
        this.message = message;
        this.profile = profile;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Profile getProfile() {
        return profile;
    }
}
