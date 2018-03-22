package com.mota.tribal.protsahan.Query.Presenter;

import android.content.Context;

import com.mota.tribal.protsahan.Query.Model.Data.QueryData;
import com.mota.tribal.protsahan.Query.Model.QueryProvider;
import com.mota.tribal.protsahan.Query.QueryCallback;
import com.mota.tribal.protsahan.Query.View.QueryView;

public class QueryPresenterImpl implements QueryPresenter {

    private QueryProvider provider;
    private QueryView view;
    private Context context;

    public QueryPresenterImpl(QueryProvider provider, QueryView view, Context context) {
        this.context = context;
        this.provider = provider;
        this.view = view;
    }

    @Override
    public void getAllQueries(String username, String token) {
        view.showProgressBar(true);
        provider.getAllQueries(username, token, new QueryCallback() {
            @Override
            public void onSuccess(QueryData body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    view.onGettingAllQueries(body);
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

