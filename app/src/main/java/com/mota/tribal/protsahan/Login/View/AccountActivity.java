package com.mota.tribal.protsahan.Login.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mota.tribal.protsahan.Helper.BottomNavigationViewHelper;
import com.mota.tribal.protsahan.MainActivity;
import com.mota.tribal.protsahan.Query.View.SectionsPagerAdapter;
import com.mota.tribal.protsahan.R;
import com.mota.tribal.protsahan.Schemes.View.SchemeActivity;
import com.mota.tribal.protsahan.SignUp.SignupFragment;
import com.mota.tribal.protsahan.Upload.UploadActivity;
import com.mota.tribal.protsahan.ViewAllProfiles.View.ViewProfilesActivity;

public class AccountActivity extends AppCompatActivity {

    private Intent intent;
    // private Toolbar mtoolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_scheme:
                    intent = new Intent(AccountActivity.this, SchemeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_profiles:
                    intent = new Intent(AccountActivity.this, ViewProfilesActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_account:
                    return true;
                case R.id.navigation_settings:
                    intent = new Intent(AccountActivity.this, UploadActivity.class);
                    startActivity(intent);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
          /*  mtoolbar=findViewById(R.id.toolbar);
            setSupportActionBar(mtoolbar);
            getSupportActionBar().setTitle("Your Account");*/
        setUpViewPager();
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
        Intent intent1 = new Intent(AccountActivity.this, MainActivity.class);
        startActivity(intent1);
    }

    private void setUpViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment());
        adapter.addFragment(new SignupFragment());
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Login");
        tabLayout.getTabAt(1).setText("Signup");
    }


}
