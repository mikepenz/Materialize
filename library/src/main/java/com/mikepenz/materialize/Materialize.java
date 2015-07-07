package com.mikepenz.materialize;

import android.app.Activity;

import com.mikepenz.materialize.util.KeyboardUtil;
import com.mikepenz.materialize.view.ScrimInsetsFrameLayout;

/**
 * Created by mikepenz on 07.07.15.
 */
public class Materialize {
    private final MaterializeBuilder mBuilder;
    private KeyboardUtil mKeyboardUtil = null;

    /**
     * the protected Constructor for the result
     *
     * @param materializeBuilder
     */
    protected Materialize(MaterializeBuilder materializeBuilder) {
        this.mBuilder = materializeBuilder;
    }

    /**
     * set the insetsFrameLayout to display the content in fullscreen
     * under the statusBar and navigationBar
     *
     * @param fullscreen
     */
    public void setFullscreen(boolean fullscreen) {
        if (mBuilder.mContentRoot != null) {
            mBuilder.mContentRoot.setEnabled(!fullscreen);
        }
    }

    /**
     * Set the color for the statusBar
     *
     * @param statusBarColor
     */
    public void setStatusBarColor(int statusBarColor) {
        if (mBuilder.mContentRoot != null) {
            mBuilder.mContentRoot.setInsetForeground(statusBarColor);
            mBuilder.mContentRoot.invalidate();
        }
    }

    /**
     * get the materializeContentRoot Layout (ScrimInsetsFrameLayout)
     *
     * @return
     */
    public ScrimInsetsFrameLayout getScrimInsetsFrameLayout() {
        return mBuilder.mContentRoot;
    }

    /**
     * get the materializeContentRoot Layout (ScrimInsetsFrameLayout)
     *
     * @return
     */
    public ScrimInsetsFrameLayout getContent() {
        return getScrimInsetsFrameLayout();
    }


    /**
     * a helper method to enable the keyboardUtil for a specific activity
     * or disable it. note this will cause some frame drops because of the
     * listener.
     *
     * @param activity
     * @param enable
     */
    public void keyboardSupportEnabled(Activity activity, boolean enable) {
        if (getContent() != null && getContent().getChildCount() > 0) {
            if (mKeyboardUtil == null) {
                mKeyboardUtil = new KeyboardUtil(activity, getContent().getChildAt(0));
                mKeyboardUtil.disable();
            }

            if (enable) {
                mKeyboardUtil.enable();
            } else {
                mKeyboardUtil.disable();
            }
        }
    }


}
