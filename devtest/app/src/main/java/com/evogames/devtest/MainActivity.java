package com.evogames.devtest;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.util.Log;
import android.content.Intent;

import android.content.ComponentName;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.browser.customtabs.CustomTabsCallback;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {
    public static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";

    private CustomTabsSession mSession;
    private CustomTabsServiceConnection mConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        //create layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Create button
        Button btn = new Button(this);
        btn.setText("Button");
        btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonAction();
//                Toast.makeText(MainActivity.this, "Toast", Toast.LENGTH_LONG).show();
            }
        });

        layout.addView(btn);

        // Create layout param
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT);

        this.addContentView(layout, layoutParam);

        onDeeplink(getIntent());
    }

    @Override
    protected void onStart() {
        super.onStart();

        CustomTabsCallback callback = new CustomTabsCallback() {
            @Override
            public void onNavigationEvent(int navigationEvent, @Nullable Bundle extras) {
                Log.i("CustomTab", String.format("navigationEvent %d", navigationEvent));
            }

            @Override
            public void extraCallback(@NonNull String callbackName, @Nullable Bundle args) {
                Log.i("CustomTab", String.format("extraCallback %s", callbackName));
            }
        };

        mConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(@NonNull ComponentName name,
                                                     @NonNull CustomTabsClient client) {
                mSession = client.newSession(callback);
                client.warmup(0);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) { }
        };

        String packageName = CustomTabsClient.getPackageName(MainActivity.this, null);
//        if (packageName == null) {
//            Toast.makeText(this, "Can't find a Custom Tabs provider.", Toast.LENGTH_SHORT).show();
//            return;
//        }

        CustomTabsClient.bindCustomTabsService(this, packageName, mConnection);
    }

    protected void onStop() {
        super.onStop();
        if (mConnection == null) return;
        unbindService(mConnection);
        mConnection = null;
    }

    private void ButtonAction() {
//        CustomTab.OpenURL(this, "https://google.com/");
        String url = "https://supabase-login-helper.vercel.app/supabaseAuth#loginParam=eyJzdXBhYmFzZV91cmwiOiJodHRwczovL2dmeGZza3N0ZmdjY3ZvZ3lzbGxoLnN1cGFiYXNlLmNvIiwic3VwYWJhc2Vfa2V5IjoiZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBjM01pT2lKemRYQmhZbUZ6WlNJc0luSmxaaUk2SW1kbWVHWnphM04wWm1kalkzWnZaM2x6Ykd4b0lpd2ljbTlzWlNJNkltRnViMjRpTENKcFlYUWlPakUyTlRrek5qUXlOaklzSW1WNGNDSTZNVGszTkRrME1ESTJNbjAuei1qTTFxeVB4S0JGaW9IeTlPbXcwbzkzQ0pyeFRONjY2dU5lNUhBS3h3ZyIsInByb3ZpZGVyIjoiZ29vZ2xlIiwicmVkaXJlY3RVcmwiOiJhdXR1bW46Ly9zdXBhYmFzZS1sb2dpbi1oZWxwZXIudmVyY2VsLmFwcC9zdXBhYmFzZUNhbGxiYWNrIn0=";
//        customTabsIntent.launchUrl(MainActivity.this, Uri.parse(url));
        CustomTabsIntent intent = new CustomTabsIntent.Builder(mSession).build();
        intent.intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.launchUrl(MainActivity.this, Uri.parse(url));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onDeeplink(intent);
    }

    protected void onDeeplink(Intent intent) {
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String deeplink = intent.getDataString();
            Log.i("CustomTab", String.format("deeplink %s", deeplink));
        }
    }

}