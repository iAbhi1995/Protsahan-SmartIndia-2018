package com.mota.tribal.protsahan.Login.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mota.tribal.protsahan.Helper.ConnectivityReceiver;
import com.mota.tribal.protsahan.Login.Model.Data.UserInfo;
import com.mota.tribal.protsahan.Login.Model.MockLogin;
import com.mota.tribal.protsahan.Login.Presenter.LoginPresenter;
import com.mota.tribal.protsahan.Login.Presenter.LoginPresenterImpl;
import com.mota.tribal.protsahan.Login.SQLiteHandler;
import com.mota.tribal.protsahan.R;
import com.mota.tribal.protsahan.Upload.UploadActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements LoginView {

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
    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = getView().findViewById(R.id.progress_bar);
        connectivityReceiver = new ConnectivityReceiver();
        /*if (!connectivityReceiver.isConnected())
            Snackbar.make(findViewById(R.id.login), "Connection Error", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        session = new SessionManager(getContext());
        pDialog = new ProgressDialog(getContext());

        session.setLogin(false);
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            SQLiteHandler sqLiteHandler = new SQLiteHandler(getContext());
            UserInfo user = sqLiteHandler.getUser();
            Intent intent = new Intent(getActivity(), UploadActivity.class);
            startActivity(intent);
        }
        etUsername = getView().findViewById(R.id.etUsername);
        etPassword = getView().findViewById(R.id.etPassword);
        bLogin = getView().findViewById(R.id.bLogin);
        forgotpassword = getView().findViewById(R.id.forgotpassword);

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
                presenter = new LoginPresenterImpl(new MockLogin(), LoginFragment.this, getContext());
                presenter.getResponse(email, password);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        Snackbar.make(getView().findViewById(R.id.login), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void showUserDetails(UserInfo data) {

        db = new SQLiteHandler(getContext());
        db.addUser(data.getUsername(), data.getToken());
        session = new SessionManager(getContext());
        session.setLogin(true);
        Snackbar.make(getView().findViewById(R.id.login), "Logged in " + data.getUsername() + "  " + data.getToken(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
