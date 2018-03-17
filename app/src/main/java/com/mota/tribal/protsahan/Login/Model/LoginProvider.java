package com.mota.tribal.protsahan.Login.Model;

import com.mota.tribal.protsahan.Login.LoginCallback;

/**
 * Created by ayush on 03-02-2018.
 */

public interface LoginProvider {
    void getUser(String email, String password, LoginCallback callback);
}
