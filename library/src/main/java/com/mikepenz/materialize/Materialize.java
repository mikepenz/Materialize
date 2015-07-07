package com.mikepenz.materialize;

import android.app.Activity;

import com.mikepenz.materialize.util.KeyboardUtil;
import com.mikepenz.materialize.view.ScrimInsetsFrameLayout;

/**
 * Created by mikepenz on 07.07.15.
 */
public class Materialize {
    private final MaterializeBuilder mDrawerBuilder;
    private ScrimInsetsFrameLayout mContentView;
    private KeyboardUtil mKeyboardUtil = null;

    /**
     * the protected Constructor for the result
     *
     * @param drawerBuilder
     */
    protected Materialize(MaterializeBuilder drawerBuilder) {
        this.mDrawerBuilder = drawerBuilder;
    }

    /**
     * set the insetsFrameLayout to display the content in fullscreen
     * under the statusBar and navigationBar
     *
     * @param fullscreen
     */
    public void setFullscreen(boolean fullscreen) {
        if (mDrawerBuilder.mDrawerContentRoot != null) {
            mDrawerBuilder.mDrawerContentRoot.setEnabled(!fullscreen);
        }
    }

    /**
     * Set the color for the statusBar
     *
     * @param statusBarColor
     */
    public void setStatusBarColor(int statusBarColor) {
        if (mDrawerBuilder.mDrawerContentRoot != null) {
            mDrawerBuilder.mDrawerContentRoot.setInsetForeground(statusBarColor);
            mDrawerBuilder.mDrawerContentRoot.invalidate();
        }
    }

    /**
     * get the drawerContentRoot Layout (ScrimInsetsFrameLayout)
     *
     * @return
     */
    public ScrimInsetsFrameLayout getScrimInsetsFrameLayout() {
        return mDrawerBuilder.mDrawerContentRoot;
    }

    /**
     * get the drawerContentRoot Layout (ScrimInsetsFrameLayout)
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
