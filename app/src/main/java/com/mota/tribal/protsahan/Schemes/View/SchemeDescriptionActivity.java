package com.mota.tribal.protsahan.Schemes.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mota.tribal.protsahan.R;

public class SchemeDescriptionActivity extends AppCompatActivity {

    private TextView textView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_description);

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("item");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("item");
        }
        textView = findViewById(R.id.description);
        textView.setText(newString);
    }
}
