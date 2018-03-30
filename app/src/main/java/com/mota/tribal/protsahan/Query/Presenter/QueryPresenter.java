package com.mota.tribal.protsahan.Query.Presenter;

public interface QueryPresenter {
    void getAllQueries(String username, String token);

    void askQuery(String username, String token, String question);
}
