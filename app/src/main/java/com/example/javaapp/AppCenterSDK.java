package com.example.javaapp;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.crashes.model.TestCrashException;
import com.microsoft.appcenter.distribute.Distribute;

import java.util.HashMap;

public class AppCenterSDK {

    public static void start(android.app.Application app) {
        String secret = "${AC_SECRET}";
        //noinspection ConstantConditions
        if(secret == null || secret.equals("") || secret.trim().equals("")) {
            throw new RuntimeException("App Center secret is missing");
        }
        else {
            AppCenter.start(
                    app,
                    "${AC_SECRET}",
                    Analytics.class,
                    Crashes.class,
                    Distribute.class
            );
            Crashes.setEnabled(true);
        }
    }

    public static void trackEvent(String eventName, String title) {
        HashMap<String, String> eventProps = new HashMap<>();
        eventProps.put("Title", title);
        Analytics.trackEvent(eventName, eventProps);
    }

    public static void checkUpdate() {
        Distribute.checkForUpdate();
    }

    public static void trackCrash(Exception exception) {
        Crashes.trackError(exception);
    }

}
