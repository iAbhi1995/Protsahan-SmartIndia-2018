package com.mota.tribal.protsahan.Profile.Model;

import android.os.Handler;

import com.mota.tribal.protsahan.Profile.Model.Data.Profile;
import com.mota.tribal.protsahan.Profile.Model.Data.ProfileData;
import com.mota.tribal.protsahan.Profile.ProfileCallback;

/**
 * Created by Abhi on 14-Mar-18.
 */

public class MockProfileProvider implements ProfileProvider {
    private ProfileData mockData1, mockData2;

    @Override
    public void getProfile(String id, final ProfileCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData1());
            }
        }, 500);
    }

    @Override
    public void postProfile(Profile profile, final ProfileCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData2());
            }
        }, 500);
    }

    public ProfileData getMockData1() {
        Profile profile = new Profile("01", "Danti", "Working", "Mahora",
                "Bastar", "123456789123", "8299231633", "Female",
                "http://www.echhattisgarh.co.in/images/bastar-tribal.jpg");
        mockData1 = new ProfileData(true, "Success", profile);
        return mockData1;
    }

    public ProfileData getMockData2() {
        mockData2 = new ProfileData(true, "success", null);
        return mockData2;
    }
}
