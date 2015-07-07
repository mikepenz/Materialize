package com.mikepenz.materialize;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mikepenz.materialize.util.UIUtils;
import com.mikepenz.materialize.view.ScrimInsetsFrameLayout;

/**
 * Created by mikepenz on 07.07.15.
 */
public class MaterializeBuilder {

    // some internal vars
    // variable to check if a builder is only used once
    protected boolean mUsed = false;

    // the activity to use
    protected Activity mActivity;
    protected ViewGroup mRootView;
    protected ScrimInsetsFrameLayout mDrawerContentRoot;


    /**
     * default constructor
     */
    public MaterializeBuilder() {

    }

    /**
     * constructor with activity instead of
     *
     * @param activity
     */
    public MaterializeBuilder(Activity activity) {
        this.mRootView = (ViewGroup) activity.findViewById(android.R.id.content);
        this.mActivity = activity;
    }

    /**
     * Pass the activity you use the drawer in ;)
     * This is required if you want to set any values by resource
     *
     * @param activity
     * @return
     */
    public MaterializeBuilder withActivity(Activity activity) {
        this.mRootView = (ViewGroup) activity.findViewById(android.R.id.content);
        this.mActivity = activity;
        return this;
    }

    //the statusBar color
    protected int mStatusBarColor = 0;
    protected int mStatusBarColorRes = -1;

    /**
     * Set the statusBarColor color for this activity
     *
     * @param statusBarColor
     * @return
     */
    public MaterializeBuilder withStatusBarColor(int statusBarColor) {
        this.mStatusBarColor = statusBarColor;
        return this;
    }

    /**
     * Set the statusBarColor color for this activity from a resource
     *
     * @param statusBarColorRes
     * @return
     */
    public MaterializeBuilder withStatusBarColorRes(int statusBarColorRes) {
        this.mStatusBarColorRes = statusBarColorRes;
        return this;
    }


    // set actionbar Compatibility mode
    protected boolean mTranslucentActionBarCompatibility = false;

    /**
     * Set this to true to use a translucent StatusBar in an activity with a good old
     * ActionBar. Should be a rare scenario.
     *
     * @param translucentActionBarCompatibility
     * @return
     */
    public MaterializeBuilder withTranslucentActionBarCompatibility(boolean translucentActionBarCompatibility) {
        this.mTranslucentActionBarCompatibility = translucentActionBarCompatibility;
        return this;
    }

    /**
     * Set this to true if you want your drawer to be displayed below the toolbar.
     * Note this will add a margin above the drawer
     *
     * @param displayBelowToolbar
     * @return
     */
    public MaterializeBuilder withDisplayBelowToolbar(boolean displayBelowToolbar) {
        this.mTranslucentActionBarCompatibility = displayBelowToolbar;
        return this;
    }

    // set non translucent statusBar mode
    protected boolean mTranslucentStatusBar = true;

    /**
     * Set to false to disable the use of a translucent statusBar
     *
     * @param translucentStatusBar
     * @return
     */
    public MaterializeBuilder withTranslucentStatusBar(boolean translucentStatusBar) {
        this.mTranslucentStatusBar = translucentStatusBar;

        //if we disable the translucentStatusBar it should be disabled at all
        if (!translucentStatusBar) {
            this.mTranslucentStatusBarProgrammatically = false;
        }
        return this;
    }


    // set to disable the translucent statusBar Programmatically
    protected boolean mTranslucentStatusBarProgrammatically = true;

    /**
     * set this to false if you want no translucent statusBar. or
     * if you want to create this behavior only by theme.
     *
     * @param translucentStatusBarProgrammatically
     * @return
     */
    public MaterializeBuilder withTranslucentStatusBarProgrammatically(boolean translucentStatusBarProgrammatically) {
        this.mTranslucentStatusBarProgrammatically = translucentStatusBarProgrammatically;
        //if we enable the programmatically translucent statusBar we want also the normal statusBar behavior
        if (translucentStatusBarProgrammatically) {
            this.mTranslucentStatusBar = true;
        }
        return this;
    }

    // defines if we want the statusBarShadow to be used in the Drawer
    protected Boolean mTranslucentStatusBarShadow = null;

    /**
     * set this to true or false if you want activate or deactivate this
     * set it to null if you want the default behavior (activated for lollipop and up)
     *
     * @param translucentStatusBarShadow
     * @return
     */
    public MaterializeBuilder withTranslucentStatusBarShadow(Boolean translucentStatusBarShadow) {
        this.mTranslucentStatusBarShadow = translucentStatusBarShadow;
        return this;
    }

    // set non translucent NavigationBar mode
    protected boolean mTranslucentNavigationBar = false;

    /**
     * Set to true if you use a translucent NavigationBar
     *
     * @param translucentNavigationBar
     * @return
     */
    public MaterializeBuilder withTranslucentNavigationBar(boolean translucentNavigationBar) {
        this.mTranslucentNavigationBar = translucentNavigationBar;

        //if we disable the translucentNavigationBar it should be disabled at all
        if (!translucentNavigationBar) {
            this.mTranslucentNavigationBarProgrammatically = false;
        }

        return this;
    }

    // set to disable the translucent statusBar Programmatically
    protected boolean mTranslucentNavigationBarProgrammatically = false;

    /**
     * set this to true if you want a translucent navigation bar.
     *
     * @param translucentNavigationBarProgrammatically
     * @return
     */
    public MaterializeBuilder withTranslucentNavigationBarProgrammatically(boolean translucentNavigationBarProgrammatically) {
        this.mTranslucentNavigationBarProgrammatically = translucentNavigationBarProgrammatically;
        //if we enable the programmatically translucent navigationBar we want also the normal navigationBar behavior
        if (translucentNavigationBarProgrammatically) {
            this.mTranslucentNavigationBar = true;
        }
        return this;
    }


    // set non translucent NavigationBar mode
    protected boolean mFullscreen = false;

    /**
     * Set to true if the used theme has a translucent statusBar
     * and navigationBar and you want to manage the padding on your own.
     *
     * @param fullscreen
     * @return
     */
    public MaterializeBuilder withFullscreen(boolean fullscreen) {
        this.mFullscreen = fullscreen;

        if (fullscreen) {
            withTranslucentStatusBar(false);
            withTranslucentNavigationBarProgrammatically(true);
        }

        return this;
    }


    public Materialize build() {
        if (mUsed) {
            throw new RuntimeException("you must not reuse a DrawerBuilder builder");
        }
        if (mActivity == null) {
            throw new RuntimeException("please pass an activity");
        }

        //set that this builder was used. now you have to create a new one
        mUsed = true;

        // if the user has not set a drawerLayout use the default one :D
        mDrawerContentRoot = (ScrimInsetsFrameLayout) mActivity.getLayoutInflater().inflate(R.layout.materialize, mRootView, false);

        //check if the activity was initialized correctly
        if (mRootView == null || mRootView.getChildCount() == 0) {
            throw new RuntimeException("You have to set your layout for this activity with setContentView() first. Or you build the drawer on your own with .buildView()");
        }

        //get the content view
        View contentView = mRootView.getChildAt(0);
        boolean alreadyInflated = contentView instanceof ScrimInsetsFrameLayout;

        //do some magic specific to the statusBar
        if (!alreadyInflated && mTranslucentStatusBar) {
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
                UIUtils.setTranslucentStatusFlag(mActivity, true);
            }
            if (Build.VERSION.SDK_INT >= 19) {
                if (mTranslucentStatusBarProgrammatically) {
                    mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                UIUtils.setTranslucentStatusFlag(mActivity, false);
                if (mTranslucentStatusBarProgrammatically) {
                    mActivity.getWindow().setStatusBarColor(Color.TRANSPARENT);
                }
            }
            mDrawerContentRoot.setPadding(0, UIUtils.getStatusBarHeight(mActivity), 0, 0);

            // define the statusBarColor
            if (mStatusBarColor == 0 && mStatusBarColorRes != -1) {
                mStatusBarColor = mActivity.getResources().getColor(mStatusBarColorRes);
            } else if (mStatusBarColor == 0) {
                mStatusBarColor = UIUtils.getThemeColorFromAttrOrRes(mActivity, R.attr.colorPrimaryDark, R.color.material_drawer_primary_dark);
            }
            mDrawerContentRoot.setInsetForeground(mStatusBarColor);
        }

        //do some magic specific to the navigationBar
        if (!alreadyInflated && mTranslucentNavigationBar) {
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
                UIUtils.setTranslucentNavigationFlag(mActivity, true);
            }
            if (Build.VERSION.SDK_INT >= 19) {
                if (mTranslucentNavigationBarProgrammatically) {
                    mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                    UIUtils.setTranslucentNavigationFlag(mActivity, true);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (mTranslucentNavigationBarProgrammatically) {
                    mActivity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
                }
            }
        }

        //if we are fullscreen disable the ScrimInsetsLayout
        if (mFullscreen && Build.VERSION.SDK_INT >= 19) {
            mDrawerContentRoot.setEnabled(false);
        }

        //only add the new layout if it wasn't done before
        if (!alreadyInflated) {
            // remove the contentView
            mRootView.removeView(contentView);
        } else {
            //if it was already inflated we have to clean up again
            mRootView.removeAllViews();
        }

        //create the layoutParams to use for the contentView
        FrameLayout.LayoutParams layoutParamsContentView = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        //if we have a translucent navigation bar set the bottom margin
        if (!mFullscreen && mTranslucentNavigationBar && Build.VERSION.SDK_INT >= 19) {
            layoutParamsContentView.bottomMargin = UIUtils.getNavigationBarHeight(mActivity);
        }

        //add the contentView to the drawer content frameLayout
        mDrawerContentRoot.addView(contentView, layoutParamsContentView);

        //add the drawerLayout to the root
        mRootView.addView(mDrawerContentRoot, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        return new Materialize(this);
    }
}
