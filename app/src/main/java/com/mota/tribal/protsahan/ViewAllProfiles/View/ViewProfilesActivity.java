package com.mota.tribal.protsahan.ViewAllProfiles.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mota.tribal.protsahan.R;

public class ViewProfilesActivity extends AppCompatActivity implements ViewProfilesView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profiles);
    }

    @Override
    public void showProgressBar(boolean b) {

    }

    @Override
    public void showMessage(String message) {

    }
}
