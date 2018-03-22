package com.mota.tribal.protsahan.Query.Model;

import com.mota.tribal.protsahan.Query.QueryCallback;

/**
 * Created by ayush on 03-02-2018.
 */

public interface QueryProvider {
    void getAllQueries(String email, String password, QueryCallback callback);
}
