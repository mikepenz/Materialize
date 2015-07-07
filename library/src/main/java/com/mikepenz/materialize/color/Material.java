package com.mikepenz.materialize.color;

import android.graphics.Color;

import com.mikepenz.materialize.R;

/**
 * Created by mikepenz on 07.07.15.
 */
public class Material {
    public enum Red implements IColor {
        _50("#FFEBEE", R.color.md_red_50),
        _100("#FFCDD2", R.color.md_red_100),
        _200("#EF9A9A", R.color.md_red_200),
        _300("#E57373", R.color.md_red_300),
        _400("#EF5350", R.color.md_red_400),
        _500("#F44336", R.color.md_red_500),
        _600("#E53935", R.color.md_red_600),
        _700("#D32F2F", R.color.md_red_700),
        _800("#C62828", R.color.md_red_800),
        _900("#B71C1C", R.color.md_red_900),
        _A100("#FF8A80", R.color.md_red_A100),
        _A200("#FF5252", R.color.md_red_A200),
        _A400("#FF1744", R.color.md_red_A400),
        _A700("#D50000", R.color.md_red_A700);

        String color;
        int resource;

        Red(String color, int resource) {
            this.color = color;
            this.resource = resource;
        }

        @Override
        public String getAsString() {
            return color;
        }

        @Override
        public int getAsColor() {
            return Color.parseColor(color);
        }

        @Override
        public int getAsResource() {
            return resource;
        }
    }

    //TODO finialize for all other colors
}
