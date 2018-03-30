package com.mota.tribal.protsahan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mota.tribal.protsahan.Helper.BottomNavigationViewHelper;
import com.mota.tribal.protsahan.Login.View.AccountActivity;
import com.mota.tribal.protsahan.Profile.View.ProfileActivity;
import com.mota.tribal.protsahan.Query.View.QueryActivity;
import com.mota.tribal.protsahan.Schemes.View.SchemeActivity;
import com.mota.tribal.protsahan.Upload.UploadActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;

    private Intent intent;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_scheme:
                    Intent intent = new Intent(MainActivity.this, SchemeActivity.class);
                    startActivity(intent);
                case R.id.navigation_profiles:
                    return true;
                case R.id.navigation_account:
                    intent = new Intent(MainActivity.this, AccountActivity.class);
                    startActivity(intent);
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        setSupportActionBar(toolbar);

        initCollapsingToolbar();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        ArrayList<String> titles = new ArrayList<>();
        titles.add("title 0");
        titles.add("title 1");
        titles.add("title 2");
        titles.add("title 3");
        titles.add("title 4");
        titles.add("title 5");
        titles.add("title 6");
        titles.add("title 7");
        titles.add("title 8");
        titles.add("title 9");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SchemeNotificationAdapter adapter = new SchemeNotificationAdapter(this, titles);
        recyclerView.setAdapter(adapter);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Notifications");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.profile_menu, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);

//        if (hideState) {
//            MenuItem item = menu.findItem(R.id.edit);
//            item.setVisible(false);
//        }
//
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        if (id == R.id.my_profile) {
            i = new Intent(this, ProfileActivity.class);
            i.putExtra("call_from", "main");
            startActivity(i);
        } else if (id == R.id.upload) {
            i = new Intent(this, UploadActivity.class);
            startActivity(i);
        } else if (id == R.id.query) {
            i = new Intent(this, QueryActivity.class);
            startActivity(i);
        }

        return true;
    }


}
