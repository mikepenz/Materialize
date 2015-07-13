package com.mikepenz.materialize.view;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

/**
 * Created by mikepenz on 13.07.15.
 */
public interface IScrimInsetsLayout {
    ViewGroup getView();

    Drawable getInsetForeground();

    void setInsetForeground(Drawable mInsetForeground);

    void setInsetForeground(int mInsetForegroundColor);

    boolean isTintStatusBar();

    void setTintStatusBar(boolean mTintStatusBar);

    boolean isTintNavigationBar();

    void setTintNavigationBar(boolean mTintNavigationBar);

    void setOnInsetsCallback(OnInsetsCallback onInsetsCallback);

    OnInsetsCallback getOnInsetsCallback();
}
