package com.mota.tribal.protsahan.Profile.View;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.mota.tribal.protsahan.Profile.Model.Data.Profile;
import com.mota.tribal.protsahan.Profile.Model.MockProfileProvider;
import com.mota.tribal.protsahan.Profile.Presenter.ProfilePresenter;
import com.mota.tribal.protsahan.Profile.Presenter.ProfilePresenterImpl;
import com.mota.tribal.protsahan.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    private EditText name, tribe, description, address, aadhar, phoneNo;
    private Button myVideos, myImages, myDocs, save;
    private RadioButton Male, Female, genderOther;

    private ProfilePresenter presenter;
    private CircleImageView profilePic;
    private String gender;
    private String id;
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        tribe = findViewById(R.id.tribe);
        description = findViewById(R.id.description);
        address = findViewById(R.id.address);
        aadhar = findViewById(R.id.aadhar);
        phoneNo = findViewById(R.id.phone_no);

        profilePic = findViewById(R.id.profile_pic);

        Male = findViewById(R.id.radioButton2);
        Female = findViewById(R.id.radioButton3);
        genderOther = findViewById(R.id.radioButton);

        myVideos = findViewById(R.id.my_videos);
        myImages = findViewById(R.id.my_images);
        myDocs = findViewById(R.id.my_docs);
        save = findViewById(R.id.save);

        pBar = findViewById(R.id.progress_bar);

        name.setEnabled(false);
        tribe.setEnabled(false);
        description.setEnabled(false);
        address.setEnabled(false);
        aadhar.setEnabled(false);
        phoneNo.setEnabled(false);
        Male.setEnabled(false);
        Female.setEnabled(false);
        genderOther.setEnabled(false);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO:Need to get Id from shared preference and store it in the variable "id"


        presenter = new ProfilePresenterImpl(this, new MockProfileProvider(), this);
        presenter.getProfile("101");
        //        presenter = new ProfilePresenterImpl(this, new RetrofitProfileProvider(), this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.edit) {
            Log.d("abhi", "in the edit field");
            allowEdit();
        }
        return true;
    }

    private void allowEdit() {
        save.setVisibility(View.VISIBLE);

        name.setEnabled(true);
        tribe.setEnabled(true);
        description.setEnabled(true);
        address.setEnabled(true);
        aadhar.setEnabled(true);
        phoneNo.setEnabled(true);

        myDocs.setVisibility(View.GONE);
        myImages.setVisibility(View.GONE);
        myVideos.setVisibility(View.GONE);

        showMessage("You can now edit your profile!\nPress the save button to save the changes");
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.my_profile_relLayout), message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void showProgressBar(boolean b) {
        if (b)
            pBar.setVisibility(View.VISIBLE);
        else
            pBar.setVisibility(View.GONE);
    }

    @Override
    public void onProfilePosted() {
        name.setEnabled(false);
        tribe.setEnabled(false);
        description.setEnabled(false);
        address.setEnabled(false);
        aadhar.setEnabled(false);
        phoneNo.setEnabled(false);
        save.setVisibility(View.GONE);

        myDocs.setVisibility(View.VISIBLE);
        myImages.setVisibility(View.VISIBLE);
        myVideos.setVisibility(View.VISIBLE);

        showMessage("Changes Saved!");
    }

    @Override
    public void onProfileGet(Profile profile) {
        name.setText(profile.getName());
        tribe.setText(profile.getTribe());
        description.setText(profile.getDescription());
        aadhar.setText(profile.getAadhar());
        phoneNo.setText(profile.getPhone());
        address.setText(profile.getAddress());
        gender = profile.getGender();
        if (gender.equals("Male")) {
            Male.setChecked(true);
            Male.setEnabled(true);
            Female.setChecked(false);
            genderOther.setChecked(false);
            gender = "Male";
        } else if (gender.equals("Female")) {
            Female.setChecked(true);
            Female.setEnabled(true);
            Male.setChecked(false);
            genderOther.setChecked(false);
            gender = "Female";
        } else {
            genderOther.setChecked(true);
            genderOther.setEnabled(true);
            Male.setChecked(false);
            Female.setChecked(false);
            gender = "Other";
        }
        Picasso.with(this).load(profile.getImg()).placeholder(R.drawable.mario_black).into(profilePic);
    }


    public void save(View v) {
        if (phoneNo.getText().toString().trim().length() != 10)
            showMessage("Incorrect mobile number");
        else if (aadhar.getText().toString().trim().length() != 12)
            showMessage("Incorrect aadhar number");
        else if (name.getText().toString().equals("") || description.getText().toString().equals("")
                || tribe.getText().toString().equals("") || address.getText().toString().equals(""))
            showMessage("Please Enter all the details");
        else if (gender == null)
            showMessage("Please select gender");
        else {
            Profile profile = new Profile(id, name.getText().toString(), description.getText().toString(),
                    tribe.getText().toString(), address.getText().toString(), aadhar.getText().toString(),
                    phoneNo.getText().toString(), gender, "");
            presenter.postProfile(profile);
        }

    }

    public void GenderSelect(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButton3:
                if (checked)
                    gender = "Female";
                break;
            case R.id.radioButton2:
                if (checked)
                    gender = "Male";
                break;
            case R.id.radioButton:
                if (checked)
                    gender = "Other";
                break;
        }
    }
}
