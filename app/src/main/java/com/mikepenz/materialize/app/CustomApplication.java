package com.mikepenz.materialize.app;

import android.app.Application;
import android.view.View;

import com.mikepenz.aboutlibraries.LibsConfiguration;

/**
 * Created by mikepenz on 27.03.15.
 */
public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LibsConfiguration.getInstance().setUiListener(new LibsConfiguration.LibsUIListener() {
            @Override
            public View preOnCreateView(View view) {
                return view;
            }

            @Override
            public View postOnCreateView(View view) {
                //View cardListView = view.findViewById(R.id.cardListView);
                //cardListView.setPadding(cardListView.getPaddingLeft(), cardListView.getPaddingTop(), cardListView.getPaddingRight(), cardListView.getPaddingBottom() + UIUtils.getNavigationBarHeight(view.getContext()));
                return view;
            }
        });
    }
}
