package com.mota.tribal.protsahan.Query.Model;

import com.mota.tribal.protsahan.Query.OnCallback;

/**
 * Created by ayush on 03-02-2018.
 */

public interface DataProvider {
    void get(String email, String password, OnCallback callback);
}
