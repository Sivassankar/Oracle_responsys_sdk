package com.example.oracle_responsys_sdk;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.NonNull;


import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

import com.oracle.cx.mobilesdk.*;
import com.oracle.cx.mobilesdk.ORAEventMeta;
import com.oracle.cx.mobilesdk.ORADataCollector;
import com.oracle.cx.mobilesdk.ORAEventMeta;
import com.oracle.cx.mobilesdk.contracts.IORABaseDataCollector;
import com.oracle.cx.mobilesdk.contracts.IORADataCollector;

import java.util.HashMap;
import java.util.Map;

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
    private static String EVENT_PATH = "eventPath";
    private static String EVENT_DESCRIPTION = "eventDescription";
    private static String APPLICATION_NAME = "applicationName";

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
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("triggerApplicationStartEvent")) {
            result.success(triggerCustomEvent((Map<String, Object>) call.argument("value")));
        } else if (call.method.equals("triggerApplicationStartEvent")) {
            result.success(triggerApplicationStartEvent((Map<String, Object>) call.argument("value")));
        }
        else {
            result.notImplemented();
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


    public Map<String, String> triggerApplicationStartEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerApplicationStartEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_PARAMS));
    }


    public Map<String, String> triggerApplicationErrorEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerApplicationTerminateEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerActivityStartEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerApplicationEnterBackgroundEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerApplicationEnterForegroundEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerActivityResumeEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerActivityPauseEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerActivityStopEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerFragmentStartEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerFragmentResumeEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerFragmentPauseEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerFragmentStopEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerAdClickEvent(Map<String, Object> value) {
        return null;
    }


    public Map<String, String> triggerAdImpressionEvent(Map<String, Object> value) {
        return null;
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
        return null;
    }


    public Map<String, String> triggerDragAndDropEvent(Map<String, Object> value) {
        return null;
    }


    public void openUrl(Activity activity, String s) {

    }


    public void enableORAInWebView(WebView webView) {

    }

//    Map<String, String> triggerApplicationStartEvent(Map<String, Object> value) {
//        final ORAEventMeta eventMeta = new ORAEventMeta((String)value.get(EVENT_PATH), (String)value.get(EVENT_DESCRIPTION),  (Map<String, String>)value.get(CUSTOM_PARAMS));
//        return ORADataCollector.getInstance().triggerCustomEvent(eventMeta);
//    }
//
//    Map<String, String> triggerCustomEvent(Map<String, Object> value) {
//        final ORAEventMeta eventMeta = new ORAEventMeta((String)value.get(EVENT_PATH), (String)value.get(EVENT_DESCRIPTION),  (Map<String, String>)value.get(CUSTOM_PARAMS));
//        return ORADataCollector.getInstance().triggerCustomEvent(eventMeta);
//    }


}
