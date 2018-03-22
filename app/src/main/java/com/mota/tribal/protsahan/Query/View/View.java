package com.mota.tribal.protsahan.Query.View;

import com.mota.tribal.protsahan.Query.Model.Data.Data;

public interface View {

    void showProgressBar(boolean b);

    void showMessage(String message);

    void response(Data data);
}
