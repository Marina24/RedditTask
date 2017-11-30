package com.reddittask.android.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;

import com.reddittask.android.R;

/**
 * Created by marinaracu on 30.11.2017.
 */

public class UiUtil {

    public static CustomTabsIntent buildCustomTabsIntent(Context context) {
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

        // Show the title
        intentBuilder.setShowTitle(true);

        // Set the color of Toolbar
        intentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));

        // Display custom back button
        intentBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_arrow_left_white));

        // Add custom menu items
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "Check out this website: " + "https://developer.chrome.com/multidevice/android/customtabs");
        PendingIntent menuIntent = PendingIntent.getActivity(context, 0, shareIntent, 0);
        intentBuilder.addMenuItem(context.getString(R.string.activity_custom_tab_share_website), menuIntent);

        return intentBuilder.build();
    }
}
