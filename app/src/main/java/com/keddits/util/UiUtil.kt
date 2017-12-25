package com.keddits.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import com.keddits.R

/**
 * Created by marinaracu on 04.12.2017.
 */
class UiUtil{
    companion object {
        fun buildCustomTabsIntent(context : Context) : CustomTabsIntent{
            val intentBuilder = CustomTabsIntent.Builder()
            // Show the title
            intentBuilder.setShowTitle(true)
            // Set the color of Toolbar
            intentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            // Display custom back button
            intentBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(context.resources,
                    R.drawable.ic_arrow_left_white))
            // Add custom menu items
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    "Check out this website: " + "https://developer.chrome.com/multidevice/android/customtabs")
            val menuIntent = PendingIntent.getActivity(context, 0, shareIntent, 0)
            intentBuilder.addMenuItem(context.getString(R.string.activity_custom_tab_share_website), menuIntent)

            return intentBuilder.build()
        }
    }
}