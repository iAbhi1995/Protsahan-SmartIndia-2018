package com.mota.tribal.protsahan.Login.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mota.tribal.protsahan.Helper.ConnectivityReceiver;
import com.mota.tribal.protsahan.Login.Model.Data.UserInfo;
import com.mota.tribal.protsahan.Login.Model.RetrofitLogin;
import com.mota.tribal.protsahan.Login.Presenter.LoginPresenter;
import com.mota.tribal.protsahan.Login.Presenter.LoginPresenterImpl;
import com.mota.tribal.protsahan.Login.SQLiteHandler;
import com.mota.tribal.protsahan.R;
import com.mota.tribal.protsahan.Upload.UploadActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private SessionManager session;
    private EditText etUsername;
    private EditText etPassword;
    private Button bLogin;
    private TextView forgotpassword;
    private SQLiteHandler db;
    private LoginPresenter presenter;
    private ProgressBar progressBar;
    private ConnectivityReceiver connectivityReceiver;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.progress_bar);
        connectivityReceiver = new ConnectivityReceiver();
        /*if (!connectivityReceiver.isConnected())
            Snackbar.make(findViewById(R.id.login), "Connection Error", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        session = new SessionManager(getApplicationContext());
        pDialog = new ProgressDialog(LoginActivity.this);

        session.setLogin(false);
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
            UserInfo user = sqLiteHandler.getUser();
            Intent intent = new Intent(LoginActivity.this, UploadActivity.class);
            startActivity(intent);
            finish();
        }
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        forgotpassword = findViewById(R.id.forgotpassword);

        /*forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etUsername.getText().toString().trim();
                setForgotpassword();

            }
        });*/
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String email = etUsername.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                presenter = new LoginPresenterImpl(new RetrofitLogin(), LoginActivity.this, LoginActivity.this);
                presenter.getResponse(email, password);
            }
        });


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
        Snackbar.make(findViewById(R.id.login), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void showUserDetails(UserInfo data) {

        db = new SQLiteHandler(LoginActivity.this);
        db.addUser(data.getUsername(), data.getToken());
        session = new SessionManager(getApplicationContext());
        session.setLogin(true);
        Snackbar.make(findViewById(R.id.login), "Logged in " + data.getUsername() + "  " + data.getToken(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
