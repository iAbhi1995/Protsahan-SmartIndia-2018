package com.mota.tribal.protsahan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mota.tribal.protsahan.Helper.BottomNavigationViewHelper;
import com.mota.tribal.protsahan.Login.View.AccountActivity;
import com.mota.tribal.protsahan.Login.View.SessionManager;
import com.mota.tribal.protsahan.Profile.View.ProfileActivity;
import com.mota.tribal.protsahan.Schemes.View.SchemeActivity;

public class MainActivity extends AppCompatActivity {
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
                    SessionManager sessionManager = new SessionManager(getApplicationContext());
                    if (sessionManager.isLoggedIn()) {
                        intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    } else {
                        intent = new Intent(MainActivity.this, AccountActivity.class);
                        startActivity(intent);
                    }
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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Ayush", token + "");
    }

}
