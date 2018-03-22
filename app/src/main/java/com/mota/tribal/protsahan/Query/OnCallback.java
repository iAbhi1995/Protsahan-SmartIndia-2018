package com.mota.tribal.protsahan.Query;

import com.mota.tribal.protsahan.Query.Model.Data.Data;

public interface OnCallback {
    void onSuccess(Data data);

    void onFailure();
}
