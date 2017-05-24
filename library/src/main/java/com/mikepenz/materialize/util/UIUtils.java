package com.mikepenz.materialize.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
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
    public static int getThemeColor(Context ctx, @AttrRes int attr) {
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
    public static int getThemeColorFromAttrOrRes(Context ctx, @AttrRes int attr, @ColorRes int res) {
        int color = getThemeColor(ctx, attr);
        if (color == 0) {
            color = ContextCompat.getColor(ctx, res);
        }
        return color;
    }

    /**
     * helper method to set the background depending on the android version
     *
     * @param v
     * @param d
     */
    @Deprecated
    @SuppressLint("NewApi")
    public static void setBackground(View v, Drawable d) {
        ViewCompat.setBackground(v, d);
    }

    /**
     * helper method to set the background depending on the android version
     *
     * @param v
     * @param drawableRes
     */
    public static void setBackground(View v, @DrawableRes int drawableRes) {
        ViewCompat.setBackground(v, ContextCompat.getDrawable(v.getContext(), drawableRes));
    }

    /**
     * Returns the size in pixels of an attribute dimension
     *
     * @param context the context to get the resources from
     * @param attr    is the attribute dimension we want to know the size from
     * @return the size in pixels of an attribute dimension
     */
    public static int getThemeAttributeDimensionSize(Context context, @AttrRes int attr) {
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
        int actionBarHeight = UIUtils.getThemeAttributeDimensionSize(context, R.attr.actionBarSize);
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

    /**
     * helper to create a stateListDrawable for the icon
     *
     * @param icon
     * @param selectedIcon
     * @return
     */
    public static StateListDrawable getIconStateList(Drawable icon, Drawable selectedIcon) {
        StateListDrawable iconStateListDrawable = new StateListDrawable();
        iconStateListDrawable.addState(new int[]{android.R.attr.state_selected}, selectedIcon);
        iconStateListDrawable.addState(new int[]{}, icon);
        return iconStateListDrawable;
    }


    /**
     * helper to get the system default selectable background inclusive an active state
     *
     * @param ctx            the context
     * @param selected_color the selected color
     * @param animate        true if you want to fade over the states (only animates if API newer than Build.VERSION_CODES.HONEYCOMB)
     * @return the StateListDrawable
     */
    public static StateListDrawable getSelectableBackground(Context ctx, int selected_color, boolean animate) {
        StateListDrawable states = new StateListDrawable();

        ColorDrawable clrActive = new ColorDrawable(selected_color);
        states.addState(new int[]{android.R.attr.state_selected}, clrActive);

        states.addState(new int[]{}, getSelectableBackground(ctx));
        
        //if possible and wanted we enable animating across states
        if (animate) {
            int duration = ctx.getResources().getInteger(android.R.integer.config_shortAnimTime);
            states.setEnterFadeDuration(duration);
            states.setExitFadeDuration(duration);
        }
        
        return states;
    }

    /**
     * helper to get the system default selectable background inclusive an active and pressed state
     *
     * @param ctx            the context
     * @param selected_color the selected color
     * @param pressed_alpha  0-255
     * @param animate        true if you want to fade over the states (only animates if API newer than Build.VERSION_CODES.HONEYCOMB)
     * @return the StateListDrawable
     */
    public static StateListDrawable getSelectablePressedBackground(Context ctx, int selected_color, int pressed_alpha, boolean animate) {
        StateListDrawable states = getSelectableBackground(ctx, selected_color, animate);
        ColorDrawable clrPressed = new ColorDrawable(adjustAlpha(selected_color, pressed_alpha));
        states.addState(new int[]{android.R.attr.state_pressed}, clrPressed);
        return states;
    }

    /**
     * adjusts the alpha of a color
     *
     * @param color the color
     * @param alpha the alpha value we want to set 0-255
     * @return the adjusted color
     */
    public static int adjustAlpha(int color, int alpha) {
        return (alpha << 24) | (color & 0x00ffffff);
    }

    /**
     * helper to get the system default selectable background res
     *
     * @param ctx
     * @return
     */
    public static int getSelectableBackgroundRes(Context ctx) {
        TypedValue outValue = new TypedValue();
        //it is important here to not use the android.R because this wouldn't add the latest drawable
        ctx.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
        return outValue.resourceId;
    }


    /**
     * helper to get the system default selectable background
     *
     * @param ctx
     * @return
     */
    public static Drawable getSelectableBackground(Context ctx) {
        int selectableBackgroundRes = getSelectableBackgroundRes(ctx);
        return ContextCompat.getDrawable(ctx, selectableBackgroundRes);
    }


    /**
     * Returns the screen width in pixels
     *
     * @param context is the context to get the resources
     * @return the screen width in pixels
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }


    /**
     * Returns the screen height in pixels
     *
     * @param context is the context to get the resources
     * @return the screen height in pixels
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
}
