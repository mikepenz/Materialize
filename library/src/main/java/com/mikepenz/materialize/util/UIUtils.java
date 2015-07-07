package com.mikepenz.materialize.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mikepenz.materialize.R;

/**
 * Created by mikepenz on 15.03.14.
 */
@SuppressLint("InlinedApi")
public class UIUtils {
    /**
     * a helper method to get the color from an attribute
     *
     * @param ctx
     * @param attr
     * @return
     */
    public static int getThemeColor(Context ctx, int attr) {
        TypedValue tv = new TypedValue();
        if (ctx.getTheme().resolveAttribute(attr, tv, true)) {
            return tv.data;
        }
        return 0;
    }

    /**
     * helper method to get the color by attr (which is defined in the style) or by resource.
     *
     * @param ctx
     * @param attr
     * @param res
     * @return
     */
    public static int getThemeColorFromAttrOrRes(Context ctx, int attr, int res) {
        int color = getThemeColor(ctx, attr);
        if (color == 0) {
            color = ctx.getResources().getColor(res);
        }
        return color;
    }

    /**
     * helper method to set the background depending on the android version
     *
     * @param v
     * @param d
     */
    @SuppressLint("NewApi")
    public static void setBackground(View v, Drawable d) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackgroundDrawable(d);
        } else {
            v.setBackground(d);
        }
    }

    /**
     * helper method to set the background depending on the android version
     *
     * @param v
     * @param drawableRes
     */
    public static void setBackground(View v, int drawableRes) {
        setBackground(v, getCompatDrawable(v.getContext(), drawableRes));
    }

    /**
     * helper method to get the drawable by its resource. specific to the correct android version
     *
     * @param c
     * @param drawableRes
     * @return
     */
    public static Drawable getCompatDrawable(Context c, int drawableRes) {
        Drawable d = null;
        try {
            if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                d = c.getResources().getDrawable(drawableRes);
            } else {
                d = c.getResources().getDrawable(drawableRes, c.getTheme());
            }
        } catch (Exception ex) {
        }
        return d;
    }

    /**
     * Returns the size in pixels of an attribute dimension
     *
     * @param context the context to get the resources from
     * @param attr    is the attribute dimension we want to know the size from
     * @return the size in pixels of an attribute dimension
     */
    public static int getThemeAttributeDimensionSize(Context context, int attr) {
        TypedArray a = null;
        try {
            a = context.getTheme().obtainStyledAttributes(new int[]{attr});
            return a.getDimensionPixelSize(0, 0);
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
    }

    /**
     * helper to calculate the navigationBar height
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
        if (id > 0) {
            return resources.getDimensionPixelSize(id);
        }
        return 0;
    }

    /**
     * helper to calculate the actionBar height
     *
     * @param context
     * @return
     */
    public static int getActionBarHeight(Context context) {
        int actionBarHeight = UIUtils.getThemeAttributeDimensionSize(context, android.R.attr.actionBarSize);
        if (actionBarHeight == 0) {
            actionBarHeight = context.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material);
        }
        return actionBarHeight;
    }

    /**
     * helper to calculate the statusBar height
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        return getStatusBarHeight(context, false);
    }

    /**
     * helper to calculate the statusBar height
     *
     * @param context
     * @param force   pass true to get the height even if the device has no translucent statusBar
     * @return
     */
    public static int getStatusBarHeight(Context context, boolean force) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        int dimenResult = context.getResources().getDimensionPixelSize(R.dimen.tool_bar_top_padding);
        //if our dimension is 0 return 0 because on those devices we don't need the height
        if (dimenResult == 0 && !force) {
            return 0;
        } else {
            //if our dimens is > 0 && the result == 0 use the dimenResult else the result;
            return result == 0 ? dimenResult : result;
        }
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    /**
     * helper method to set the TranslucentStatusFlag
     *
     * @param on
     */
    public static void setTranslucentStatusFlag(Activity activity, boolean on) {
        if (Build.VERSION.SDK_INT >= 19) {
            setFlag(activity, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, on);
        }
    }

    /**
     * helper method to set the TranslucentNavigationFlag
     *
     * @param on
     */
    public static void setTranslucentNavigationFlag(Activity activity, boolean on) {
        if (Build.VERSION.SDK_INT >= 19) {
            setFlag(activity, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, on);
        }
    }

    /**
     * helper method to activate or deactivate a specific flag
     *
     * @param bits
     * @param on
     */
    public static void setFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
