package com.mota.tribal.protsahan.Query.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.mota.tribal.protsahan.Helper.BottomNavigationViewHelper;
import com.mota.tribal.protsahan.MainActivity;
import com.mota.tribal.protsahan.R;
import com.mota.tribal.protsahan.Schemes.View.SchemeActivity;

public class QueryActivity extends AppCompatActivity {

    private Toolbar mtoolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_scheme:
                    Intent intent = new Intent(QueryActivity.this, SchemeActivity.class);
                    startActivity(intent);
                case R.id.navigation_profiles:
                    return true;
                case R.id.navigation_account:
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        setUpViewPager();
        /*session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
            User user = sqLiteHandler.getUser();
            Intent intent = new Intent(AccountActivity.this, UserActivity.class);
            startActivity(intent);
            finish();
        }*/
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }

    /* private void updateNavigationBarState(BottomNavigationView bottomNavigationView,int actionId){
         Menu menu = bottomNavigationView.getMenu();
         for (int i = 0, size = menu.size(); i < size; i++) {
             MenuItem item = menu.getItem(i);
             item.setChecked(item.getItemId() == actionId);
         }
     }*/
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(QueryActivity.this, MainActivity.class);
        startActivity(intent1);
    }

    private void setUpViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AskQuestion());
        adapter.addFragment(new QueriesAll());
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Ask Question");
        tabLayout.getTabAt(1).setText("Your QueriesAll");
    }


}
