package com.mota.tribal.protsahan.ViewAllProfiles.View;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.mota.tribal.protsahan.R;
import com.mota.tribal.protsahan.ViewAllProfiles.Model.Data.ViewProfilesData;
import com.mota.tribal.protsahan.ViewAllProfiles.Model.RetrofitViewProfilesProvider;
import com.mota.tribal.protsahan.ViewAllProfiles.Presenter.ViewProfilesPresenter;
import com.mota.tribal.protsahan.ViewAllProfiles.Presenter.ViewProfilesPresenterImpl;

import java.util.ArrayList;

public class ViewProfilesActivity extends AppCompatActivity implements ViewProfilesView {

    private RecyclerView videoRecycler, imageRecycler;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profiles);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageRecycler = findViewById(R.id.image_recycler);
        videoRecycler = findViewById(R.id.video_recycler);
        progressBar = findViewById(R.id.progress_bar);

        imageRecycler.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(imageRecycler);

        videoRecycler.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelperTop = new GravitySnapHelper(Gravity.START);
        snapHelperTop.attachToRecyclerView(imageRecycler);

        ViewProfilesPresenter presenter = new ViewProfilesPresenterImpl(new RetrofitViewProfilesProvider(), this, this);
        Log.d("abhi", "in View Profiles Activity");
        presenter.getProfiles();

    }

    @Override
    public void showProgressBar(boolean b) {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.relative_layout), message, Snackbar.LENGTH_LONG).
                setAction("Action", null).show();
    }

    @Override
    public void showResult(ArrayList<ViewProfilesData.Docs> docs) {
        RecyclerViewAdapter imageAdapter = new RecyclerViewAdapter(this, docs, "image");
        RecyclerViewAdapter videoAdapter = new RecyclerViewAdapter(this, docs, "video");
        imageRecycler.setAdapter(imageAdapter);
        videoRecycler.setAdapter(videoAdapter);
    }
}
