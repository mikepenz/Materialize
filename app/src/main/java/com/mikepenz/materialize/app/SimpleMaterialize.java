package com.mikepenz.materialize.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.ui.LibsSupportFragment;
import com.mikepenz.materialize.MaterializeBuilder;

public class SimpleMaterialize extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new MaterializeBuilder().withActivity(this).build();
        //new MaterializeBuilder().withActivity(this).withFullscreen(true).withTranslucentStatusBar(true).withTintedStatusBar(true).build();

        //init and show about libraries :D
        LibsSupportFragment fragment = new LibsBuilder().withFields(R.string.class.getFields()).withVersionShown(true).withLicenseShown(true).supportFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
