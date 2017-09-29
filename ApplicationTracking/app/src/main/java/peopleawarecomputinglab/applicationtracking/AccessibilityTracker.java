package peopleawarecomputinglab.applicationtracking;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by katherinebau on 9/28/17.
 */

public class AccessibilityTracker extends AccessibilityService{

    public static String previous_app = "";
    public static String current_app_in_foreground="";

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        Log.d("log", "Service Connected");
    }

    @Override
    public void onInterrupt(){
        Log.d("log", "interrupted");
    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        final int eventType = event.getEventType();
        String packagename = (String) event.getPackageName();
        String appname = getAppName(packagename);

        switch(eventType){
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                //Log.d("log","Window State Changed");
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                //Log.d("log","Window Content Changed");
                break;
            case AccessibilityEvent.TYPE_WINDOWS_CHANGED:
                //Log.d("log", "Window Changed");
                break;
        }
        if(!previous_app.equals(appname)){
            Log.d("log", appname);
            previous_app = appname;
            current_app_in_foreground = appname;
        }
        //Log.d("log", classname);
    }

    public String getAppName(String packageName) {
        PackageManager packageManager = getPackageManager();
        ApplicationInfo applicationInfo;

        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }

        if (applicationInfo != null && packageManager.getApplicationLabel(applicationInfo) != null) {
            return (String) packageManager.getApplicationLabel(applicationInfo);
        } else {
            return "";
        }
    }


}
