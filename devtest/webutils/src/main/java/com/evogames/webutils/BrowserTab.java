package com.evogames.webutils;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsIntent;


public class BrowserTab {
    public static void OpenURL(Context context, String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
