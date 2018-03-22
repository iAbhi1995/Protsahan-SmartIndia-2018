package com.mota.tribal.protsahan.Query.Model;

import android.os.Handler;

import com.mota.tribal.protsahan.Query.Model.Data.Data;
import com.mota.tribal.protsahan.Query.OnCallback;


/**
 * Created by ayush on 03-02-2018.
 */

public class MockData implements DataProvider {
    private Data mockData;

    @Override
    public void get(String username, String password, final OnCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData());
            }
        }, 500);
    }

    public Data getMockData() {

        mockData = new Data(true, "success", "123456789", "ayushjain17aug@gmail.com");
        return mockData;
    }
}
