package com.mota.tribal.protsahan.Profile.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mota.tribal.protsahan.R;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}
