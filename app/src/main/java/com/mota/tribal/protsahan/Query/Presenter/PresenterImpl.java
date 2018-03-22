package com.mota.tribal.protsahan.Query.Presenter;

import android.content.Context;
import android.util.Log;

import com.mota.tribal.protsahan.Query.Model.Data.Data;
import com.mota.tribal.protsahan.Query.Model.DataProvider;
import com.mota.tribal.protsahan.Query.OnCallback;
import com.mota.tribal.protsahan.Query.View.View;

public class PresenterImpl implements Presenter {

    private DataProvider provider;
    private View view;
    private Context context;

    public PresenterImpl(DataProvider provider, View view, Context context) {
        this.context = context;
        this.provider = provider;
        this.view = view;
    }

    @Override
    public void getResponse(String email, String password) {
        view.showProgressBar(true);
        provider.get(email, password, new OnCallback() {
            @Override
            public void onSuccess(Data body) {
                Log.d("Ayush", body.toString());
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    view.response(body);
                } else
                    view.showMessage(body.getMessage());
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showMessage("Login Failed.Try again later!!!");
            }
        });
    }
}

