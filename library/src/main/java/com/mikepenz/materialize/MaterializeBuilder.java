package com.mikepenz.materialize;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mikepenz.materialize.util.UIUtils;
import com.mikepenz.materialize.view.IScrimInsetsLayout;
import com.mikepenz.materialize.view.ScrimInsetsFrameLayout;

/**
 * Created by mikepenz on 07.07.15.
 */
public class MaterializeBuilder {
    // the activity to use
    protected Activity mActivity;
    protected ViewGroup mRootView;
    protected ViewGroup mContentRoot;
    protected IScrimInsetsLayout mScrimInsetsLayout;

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

    /**
     * Pass the rootView of the MaterializeBuilder which will be used to inflate the views in
     *
     * @param rootView
     * @return
     */
    public MaterializeBuilder withRootView(ViewGroup rootView) {
        this.mRootView = rootView;

        //disable the translucent statusBar we don't need it
        withTranslucentStatusBar(false);

        return this;
    }

    /**
     * Pass the rootView as resource of the DrawerBuilder which will be used to inflate the views in
     *
     * @param rootViewRes
     * @return
     */
    public MaterializeBuilder withRootView(int rootViewRes) {
        if (mActivity == null) {
            throw new RuntimeException("please pass an activity first to use this call");
        }

        return withRootView((ViewGroup) mActivity.findViewById(rootViewRes));
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

    // set if the ScrimInsetsLayout should tint the StatusBar
    protected boolean mTintStatusBar = true;

    /**
     * set if the ScrimInsetsLayout should tint the StatusBar
     *
     * @param tintedStatusBar
     * @return
     */
    public MaterializeBuilder withTintedStatusBar(boolean tintedStatusBar) {
        this.mTintStatusBar = tintedStatusBar;
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

    // set if the ScrimInsetsLayout should tint the NavigationBar
    protected boolean mTintNavigationBar = false;

    /**
     * set if the ScrimInsetsLayout should tint the NavigationBar
     *
     * @param tintedNavigationBar
     * @return
     */
    public MaterializeBuilder withTintedNavigationBar(boolean tintedNavigationBar) {
        this.mTintNavigationBar = tintedNavigationBar;

        if (tintedNavigationBar) {
            withTranslucentNavigationBarProgrammatically(true);
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
            withTintedStatusBar(false);
            withTintedNavigationBar(false);
        }

        return this;
    }

    // a viewGroup which contains the ScrimInsetsFrameLayout as child
    protected ViewGroup mContainer = null;

    /**
     * set a container view here if you want the ScrimInsetsFrameLayout to be hosted inside another viewGroup
     *
     * @param container
     * @return
     */
    public MaterializeBuilder withContainer(ViewGroup container) {
        this.mContainer = container;
        return this;
    }

    // the layoutParams for the container
    protected ViewGroup.LayoutParams mContainerLayoutParams = null;

    /**
     * set the layout params for the container which will host the ScrimInsetsFrameLayout
     *
     * @param layoutParams
     * @return
     */
    public MaterializeBuilder withContainerLayoutParams(ViewGroup.LayoutParams layoutParams) {
        this.mContainerLayoutParams = layoutParams;
        return this;
    }

    /**
     * set the layout which will host the ScrimInsetsFrameLayout and its layoutParams
     *
     * @param container
     * @param layoutParams
     * @return
     */
    public MaterializeBuilder withContainer(ViewGroup container, ViewGroup.LayoutParams layoutParams) {
        this.mContainer = container;
        this.mContainerLayoutParams = layoutParams;
        return this;
    }


    public Materialize build() {
        //we need an activity for this
        if (mActivity == null) {
            throw new RuntimeException("please pass an activity");
        }

        // if the user has not set a drawerLayout use the default one :D
        mScrimInsetsLayout = (ScrimInsetsFrameLayout) mActivity.getLayoutInflater().inflate(R.layout.materialize, mRootView, false);

        //check if the activity was initialized correctly
        if (mRootView == null || mRootView.getChildCount() == 0) {
            throw new RuntimeException("You have to set your layout for this activity with setContentView() first. Or you build the drawer on your own with .buildView()");
        }

        //get the content view
        View originalContentView = mRootView.getChildAt(0);

        boolean alreadyInflated = originalContentView.getId() == R.id.materialize_root;

        // define the statusBarColor
        if (mStatusBarColor == 0 && mStatusBarColorRes != -1) {
            mStatusBarColor = mActivity.getResources().getColor(mStatusBarColorRes);
        } else if (mStatusBarColor == 0) {
            mStatusBarColor = UIUtils.getThemeColorFromAttrOrRes(mActivity, R.attr.colorPrimaryDark, R.color.materialize_primary_dark);
        }

        //handling statusBar / navigationBar tinting
        mScrimInsetsLayout.setInsetForeground(mStatusBarColor);
        mScrimInsetsLayout.setTintStatusBar(mTintStatusBar);
        mScrimInsetsLayout.setTintNavigationBar(mTintNavigationBar);

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
            mScrimInsetsLayout.getView().setPadding(0, UIUtils.getStatusBarHeight(mActivity), 0, 0);
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

        //only add the new layout if it wasn't done before
        if (!alreadyInflated) {
            // remove the contentView
            mRootView.removeView(originalContentView);
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
        mScrimInsetsLayout.getView().addView(originalContentView, layoutParamsContentView);

        //if we have a mContainer we use this one
        mContentRoot = mScrimInsetsLayout.getView();
        if (mContainer != null) {
            mContentRoot = mContainer;
            mContentRoot.addView(mScrimInsetsLayout.getView(), new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));
        }

        //set the id so we can check if it was already inflated
        mContentRoot.setId(R.id.materialize_root);

        //make sure we have the correct layoutParams
        if (mContainerLayoutParams == null) {
            mContainerLayoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
        }
        //add the drawerLayout to the root
        mRootView.addView(mContentRoot, mContainerLayoutParams);

        //set activity to null as we do not need it anymore
        this.mActivity = null;

        //create a Materialize object with the builder
        return new Materialize(this);
    }
}
