package com.mikepenz.materialize;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
        return this;
    }

    /**
     * Pass the rootView as resource of the DrawerBuilder which will be used to inflate the views in
     *
     * @param rootViewRes
     * @return
     */
    public MaterializeBuilder withRootView(@IdRes int rootViewRes) {
        if (mActivity == null) {
            throw new RuntimeException("please pass an activity first to use this call");
        }

        return withRootView((ViewGroup) mActivity.findViewById(rootViewRes));
    }

    //defines if it should add the ScrimInsetsLayout
    protected boolean mUseScrimInsetsLayout = true;

    /**
     * Defines if we should use the ScrimInsetsLayout in addition
     *
     * @param useScrimInsetsLayout defines to add the ScrimInsetsLayout
     * @return this
     */
    public MaterializeBuilder withUseScrimInsetsLayout(boolean useScrimInsetsLayout) {
        this.mUseScrimInsetsLayout = useScrimInsetsLayout;
        return this;
    }

    //the statusBar color
    protected int mStatusBarColor = 0;
    protected int mStatusBarColorRes = -1;

    /**
     * Set the statusBarColor color for this activity
     *
     * @param statusBarColor
     * @return this
     */
    public MaterializeBuilder withStatusBarColor(@ColorInt int statusBarColor) {
        this.mStatusBarColor = statusBarColor;
        return this;
    }

    /**
     * Set the statusBarColor color for this activity from a resource
     *
     * @param statusBarColorRes
     * @return
     */
    public MaterializeBuilder withStatusBarColorRes(@ColorRes int statusBarColorRes) {
        this.mStatusBarColorRes = statusBarColorRes;
        return this;
    }

    //set to true if we want a transparent statusbar
    protected boolean mTransparentStatusBar = false;

    /**
     * @param transparentStatusBar
     * @return
     */
    public MaterializeBuilder withTransparentStatusBar(boolean transparentStatusBar) {
        this.mTransparentStatusBar = transparentStatusBar;
        return this;
    }

    // set to disable the translucent statusBar Programmatically
    protected boolean mTranslucentStatusBarProgrammatically = false;

    /**
     * set this to false if you want no translucent statusBar. or
     * if you want to create this behavior only by theme.
     *
     * @param translucentStatusBarProgrammatically
     * @return
     */
    public MaterializeBuilder withTranslucentStatusBarProgrammatically(boolean translucentStatusBarProgrammatically) {
        this.mTranslucentStatusBarProgrammatically = translucentStatusBarProgrammatically;
        return this;
    }

    //set to true if we want a transparent statusBar
    protected boolean mStatusBarPadding = false;

    /**
     * @param statusBarPadding
     * @return
     */
    public MaterializeBuilder withStatusBarPadding(boolean statusBarPadding) {
        this.mStatusBarPadding = statusBarPadding;
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
        return this;
    }

    //set to true if we want a transparent navigationBar
    protected boolean mTransparentNavigationBar = false;

    /**
     * @param navigationBar
     * @return
     */
    public MaterializeBuilder withTransparentNavigationBar(boolean navigationBar) {
        this.mTransparentNavigationBar = navigationBar;
        return this;
    }

    //set to true if we want a transparent navigationBar
    protected boolean mNavigationBarPadding = false;

    /**
     * @param navigationBarPadding
     * @return
     */
    public MaterializeBuilder withNavigationBarPadding(boolean navigationBarPadding) {
        this.mNavigationBarPadding = navigationBarPadding;
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
            withTranslucentNavigationBarProgrammatically(true);
            withTintedStatusBar(false);
            withTintedNavigationBar(false);
        }

        return this;
    }


    // set to no systemUI visible mode
    protected boolean mSystemUIHidden = false;

    /**
     * Set to true if you use your app in complete fullscreen mode
     * with hidden statusBar and navigationBar
     *
     * @param systemUIHidden
     * @return
     */
    public MaterializeBuilder withSystemUIHidden(boolean systemUIHidden) {
        this.mSystemUIHidden = systemUIHidden;

        if (systemUIHidden) {
            withFullscreen(systemUIHidden);
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

        if (mUseScrimInsetsLayout) {
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
                mStatusBarColor = ContextCompat.getColor(mActivity, mStatusBarColorRes);
            } else if (mStatusBarColor == 0) {
                mStatusBarColor = UIUtils.getThemeColorFromAttrOrRes(mActivity, R.attr.colorPrimaryDark, R.color.materialize_primary_dark);
            }

            //handling statusBar / navigationBar tinting
            mScrimInsetsLayout.setInsetForeground(mStatusBarColor);
            mScrimInsetsLayout.setTintStatusBar(mTintStatusBar);
            mScrimInsetsLayout.setTintNavigationBar(mTintNavigationBar);

            //if we are fullscreen remove all insets
            mScrimInsetsLayout.setSystemUIVisible(!mFullscreen && !mSystemUIHidden);

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
        } else {
            //we need an container in this case
            if (mContainer == null) {
                throw new RuntimeException("please pass a container");
            }

            View originalContentView = mRootView.getChildAt(0);

            // remove the contentView
            mRootView.removeView(originalContentView);

            //create the layoutParams to use for the contentView
            FrameLayout.LayoutParams layoutParamsContentView = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            mContainer.addView(originalContentView, layoutParamsContentView);

            //make sure we have the correct layoutParams
            if (mContainerLayoutParams == null) {
                mContainerLayoutParams = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
            }
            //add the drawerLayout to the root
            mRootView.addView(mContainer, mContainerLayoutParams);
        }

        //set the flags if we want to hide the system ui
        if (mSystemUIHidden) {
            if (Build.VERSION.SDK_INT >= 16) {
                View decorView = this.mActivity.getWindow().getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }


        //do some magic specific to the statusBar
        if (mTranslucentStatusBarProgrammatically) {
            if (Build.VERSION.SDK_INT >= 21) {
                //this.mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                UIUtils.setTranslucentStatusFlag(mActivity, false);
            }
        }

        //do some magic specific to the navigationBar
        if (mTranslucentNavigationBarProgrammatically) {
            if (Build.VERSION.SDK_INT >= 21) {
                //this.mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                UIUtils.setTranslucentNavigationFlag(mActivity, true);
            }
        }

        if (mTransparentStatusBar || mTransparentNavigationBar) {
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            if (Build.VERSION.SDK_INT >= 21) {
                this.mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
        }

        //we do this if we want a complete transparent statusBar
        if (mTransparentStatusBar) {
            // finally change the color
            if (Build.VERSION.SDK_INT >= 21) {
                UIUtils.setTranslucentStatusFlag(mActivity, false);
                this.mActivity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }
        //we do this if we want a complete transparent navigationBar
        if (mTransparentNavigationBar) {
            // finally change the color
            if (Build.VERSION.SDK_INT >= 21) {
                UIUtils.setTranslucentNavigationFlag(mActivity, true);
                this.mActivity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            }
        }


        //do some magic specific to the statusBar
        int paddingTop = 0;
        if (mStatusBarPadding && Build.VERSION.SDK_INT >= 21) {
            paddingTop = UIUtils.getStatusBarHeight(mActivity);
        }
        int paddingBottom = 0;
        if (mNavigationBarPadding && Build.VERSION.SDK_INT >= 21) {
            paddingBottom = UIUtils.getNavigationBarHeight(mActivity);
        }
        if (mStatusBarPadding || mNavigationBarPadding && Build.VERSION.SDK_INT >= 21) {
            mScrimInsetsLayout.getView().setPadding(0, paddingTop, 0, paddingBottom);
        }

        //set activity to null as we do not need it anymore
        this.mActivity = null;

        //create a Materialize object with the builder
        return new Materialize(this);
    }


}
