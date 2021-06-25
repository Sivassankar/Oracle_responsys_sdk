package com.example.oracle_responsys_sdk;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import com.oracle.cx.mobilesdk.ORADataCollector;
import com.oracle.cx.mobilesdk.ORAEventMeta;

import java.util.Map;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * OracleResponsysSdkPlugin
 */
public class OracleResponsysSdkPlugin extends Activity implements FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    private Activity activity;
    private static String CHANNEL_NAME = "oracle_responsys_sdk";
    private static String CUSTOM_PARAMS = "customParams";
    private static String CUSTOM_DATA = "customData";
    private static String EVENT_PATH = "eventPath";
    private static String EVENT_TYPE = "eventType";
    private static String EVENT_DESCRIPTION = "eventDescription";
    private static String NOTIFICATION_MESSAGE = "notificationMessage";
    private static String APPLICATION_NAME = "applicationName";
    private static String ACTIVITY_NAME = "activityName";
    private static String FRAGMENT_NAME = "fragmentName";
    private static String ERROR_MESSAGE = "errorMessage";
    private static String AD_NAME = "adName";
    private static String AD_NAMES = "adNames";
    private static String ARGUMENTS = "arguments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity.getApplicationContext();
    }

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), CHANNEL_NAME);
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
//        if (call.method.equals("getPlatformVersion")) {
//            result.success("Android " + android.os.Build.VERSION.RELEASE);
//        } else if (call.method.equals("triggerCustomEvent")) {
//            result.success(triggerCustomEvent((Map<String, Object>) call.argument(ARGUMENTS)));
//        } else if (call.method.equals("triggerApplicationStartEvent")) {
//            result.success(triggerApplicationStartEvent((Map<String, Object>) call.argument(ARGUMENTS)));
//        } else if (call.method.equals("triggerAdClickEvent")) {
//            result.success(triggerAdClickEvent((Map<String, Object>) call.argument(ARGUMENTS)));
//        } else {
//            result.notImplemented();
//        }

        switch (call.method) {
            case "triggerCustomEvent": {
                result.success(triggerCustomEvent((Map<String, Object>) call.argument(ARGUMENTS)));
                break;
            }
            case "triggerApplicationStartEvent": {
                result.success(triggerApplicationStartEvent((Map<String, Object>) call.argument(ARGUMENTS)));
                break;
            }
            case "triggerAdClickEvent": {
                result.success(triggerAdClickEvent((Map<String, Object>) call.argument(ARGUMENTS)));
                break;
            }
            default: {
                result.notImplemented();
            }
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        this.activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        this.activity = null;
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        this.activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivity() {
        this.activity = null;
    }


//    public Map<String, String> triggerApplicationStartEvent(Map<String, Object> value) {
//        return ORADataCollector.getInstance().triggerApplicationStartEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
//    }

    public String triggerApplicationStartEvent(Map<String, Object> value) {
        System.out.println("Android First1="+ (String) value.get("First"));
        Map<String, String> childMap = (Map<String, String>) value.get("customKey");
        System.out.println("Android childKey="+ childMap.get("childKey"));
        return "triggerApplicationStartEvent Response";
    }


    public Map<String, String> triggerApplicationErrorEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerApplicationErrorEvent((String) value.get(APPLICATION_NAME), (String) value.get(ERROR_MESSAGE), (Map<String, String>) value.get(CUSTOM_PARAMS));
    }


    public Map<String, String> triggerApplicationTerminateEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerApplicationTerminateEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_PARAMS));
    }


    public Map<String, String> triggerActivityStartEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerActivityStartEvent((String) value.get(ACTIVITY_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerApplicationEnterBackgroundEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerApplicationEnterBackgroundEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerApplicationEnterForegroundEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerApplicationEnterForegroundEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerActivityResumeEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerActivityResumeEvent((String) value.get(ACTIVITY_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerActivityPauseEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerActivityPauseEvent((String) value.get(ACTIVITY_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerActivityStopEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerActivityStopEvent((String) value.get(ACTIVITY_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerFragmentStartEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerFragmentStartEvent((String) value.get(ACTIVITY_NAME), (String) value.get(FRAGMENT_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerFragmentResumeEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerFragmentResumeEvent((String) value.get(ACTIVITY_NAME), (String) value.get(FRAGMENT_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerFragmentPauseEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerFragmentPauseEvent((String) value.get(ACTIVITY_NAME), (String) value.get(FRAGMENT_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerFragmentStopEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerFragmentStopEvent((String) value.get(ACTIVITY_NAME), (String) value.get(FRAGMENT_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String>
    triggerAdClickEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta(EVENT_PATH, EVENT_DESCRIPTION, EVENT_TYPE, (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta(EVENT_PATH, EVENT_DESCRIPTION, (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerAdClickEvent(eventMeta, (String) value.get(AD_NAME));
    }


    public Map<String, String> triggerAdImpressionEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta = new ORAEventMeta(EVENT_PATH, EVENT_DESCRIPTION, EVENT_TYPE, (Map<String, String>) value.get(CUSTOM_DATA));
        return ORADataCollector.getInstance().triggerAdImpressionEvent(eventMeta, (String[]) value.get(AD_NAMES));
    }


    public Map<String, String> triggerConversionEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerMediaEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerProductEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerCartTransactionEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerScreenViewEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerContentViewEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerSearchEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerButtonClickEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerCustomEvent(Map<String, Object> value) {

        return null;
    }


    public Map<String, String> triggerVideoLoadEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerVideoLoadMetaEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerVideoPlayEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerVideoResumeEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerVideoPauseEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerVideoSeekEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerVideoCompletionEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerVideoVolumeChangeEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerVideoQuartileEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerVideoBeaconEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerVideoErrorEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerNotificationEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerNotificationEvent((String) value.get(ACTIVITY_NAME), (String) value.get(NOTIFICATION_MESSAGE), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerDragAndDropEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta(EVENT_PATH, EVENT_DESCRIPTION, EVENT_TYPE, (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta(EVENT_PATH, EVENT_DESCRIPTION, (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerDragAndDropEvent(eventMeta);
    }


    public void openUrl(Activity activity, String s) {

    }


    public void enableORAInWebView(WebView webView) {

    }
}
