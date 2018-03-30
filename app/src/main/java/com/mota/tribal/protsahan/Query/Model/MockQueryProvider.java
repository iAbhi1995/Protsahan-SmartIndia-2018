package com.mota.tribal.protsahan.Query.Model;

import android.os.Handler;

import com.mota.tribal.protsahan.Query.Model.Data.Query;
import com.mota.tribal.protsahan.Query.Model.Data.QueryData;
import com.mota.tribal.protsahan.Query.QueryCallback;

import java.util.ArrayList;

public class MockQueryProvider implements QueryProvider {

    public QueryData getMockData() {
        ArrayList<Query> queries = new ArrayList<>();
        queries.add(new Query("101", "Kya haal chaal hai bhaiya??", "Arree sb mjaaa maa bhau!!"));
        queries.add(new Query("101", "Kya haal chaal hai bhaiya??", "Arree sb mjaaa maa bhau!!"));
        queries.add(new Query("101", "Kya haal chaal hai bhaiya??", "Arree sb mjaaa maa bhau!!"));
        queries.add(new Query("101", "Kya haal chaal hai bhaiya??", "Arree sb mjaaa maa bhau!!"));
        queries.add(new Query("101", "Kya haal chaal hai bhaiya??", "Arree sb mjaaa maa bhau!!"));
        queries.add(new Query("101", "Kya haal chaal hai bhaiya??", "Arree sb mjaaa maa bhau!!"));
        queries.add(new Query("101", "Kya haal chaal hai bhaiya??", "Arree sb mjaaa maa bhau!!"));
        QueryData mockData = new QueryData(queries, true, "Aa gyi saari queries");
        return mockData;
    }

    @Override
    public void getAllQueries(String email, String password, final QueryCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData());
            }
        }, 500);

    }
}
