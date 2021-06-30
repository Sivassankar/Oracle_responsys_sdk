package com.example.oracle_responsys_sdk;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import java.util.Map;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

import com.oracle.cx.mobilesdk.ORABaseConfigSettings;
import com.oracle.cx.mobilesdk.ORADataCollector;
import com.oracle.cx.mobilesdk.ORAEventMedia;
import com.oracle.cx.mobilesdk.ORAEventMeta;
import com.oracle.cx.mobilesdk.ORAProductMeta;
import com.oracle.cx.mobilesdk.contracts.IORAConfigSetting;
import com.oracle.cx.mobilesdk.exceptions.ORAModuleIDException;
import com.oracle.cx.mobilesdk.persistent.ORACoreDataContainer;

/**
 * OracleResponsysSdkPlugin
 */
public class OracleResponsysSdkPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    private Activity activity;
    private static String CHANNEL_NAME = "oracle_responsys_sdk";
//    private static String CUSTOM_PARAMS = "customParams";
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
    private static String CONTENT_GROUP = "contentGroup";
    private static String CONVERSION_NAME = "conversionName";
    private static String MEDIA_NAME = "mediaName";
    private static String MEDIA_TYPE = "mediaType";
    private static String MEDIA_EVENT_TYPE = "mediaEventType";
    private static String TYPE = "type";
    private static String PRODUCT_SUB_TOTAL = "productSubTotal";
    private static String PRODUCT_UNIT = "productUnit";
    private static String PRODUCT_ID = "productId";
    private static String PRODUCT_SKU = "productSku";
    private static String INVOICE_NUMBER = "invoiceNumber";
    private static String SEARCH_PHRASE = "searchPhrase";
    private static String SEARCH_RESULT = "searchResult";
    private static String ARGUMENTS = "arguments";
    private ORACoreDataContainer container;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), CHANNEL_NAME);
        channel.setMethodCallHandler(this);
        init();
    }

    private void init() {
        try {
            ORADataCollector.setApplication(activity.getApplication());
            container = new ORACoreDataContainer(activity);
            container.loadFromConfigFile();
        } catch (ORAModuleIDException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
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

    public Map<String, String> triggerApplicationStartEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerApplicationStartEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerApplicationErrorEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerApplicationErrorEvent((String) value.get(APPLICATION_NAME), (String) value.get(ERROR_MESSAGE), (Map<String, String>) value.get(CUSTOM_DATA));
    }


    public Map<String, String> triggerApplicationTerminateEvent(Map<String, Object> value) {
        return ORADataCollector.getInstance().triggerApplicationTerminateEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
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
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerAdClickEvent(eventMeta, (String) value.get(AD_NAME));
    }


    public Map<String, String> triggerAdImpressionEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerAdImpressionEvent(eventMeta, (String[]) value.get(AD_NAMES));
    }


    public Map<String, String> triggerConversionEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerConversionEvent(eventMeta, (String) value.get(CONTENT_GROUP), (String) value.get(CONVERSION_NAME));
    }


    public Map<String, String> triggerMediaEvent(Map<String, Object> value) {
        ORAEventMedia eventMedia = new ORAEventMedia((String) value.get(MEDIA_NAME), (String) value.get(MEDIA_TYPE), (String) value.get(MEDIA_EVENT_TYPE));
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerMediaEvent(eventMeta, (String) value.get(CONTENT_GROUP), eventMedia);
    }


    public Map<String, String> triggerProductEvent(Map<String, Object> value) {
        ORAProductMeta productMeta = new ORAProductMeta((String) value.get(TYPE), (String[]) value.get(PRODUCT_SUB_TOTAL), (String[])
                value.get(PRODUCT_UNIT), (String[]) value.get(PRODUCT_ID), (String[]) value.get(PRODUCT_SKU));
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerProductEvent(eventMeta, (String) value.get(CONTENT_GROUP), productMeta);
    }


    public Map<String, String> triggerCartTransactionEvent(Map<String, Object> value) {
        ORAProductMeta productMeta = new ORAProductMeta((String) value.get(TYPE), (String[]) value.get(PRODUCT_SUB_TOTAL), (String[])
                value.get(PRODUCT_UNIT), (String[]) value.get(PRODUCT_ID), (String[]) value.get(PRODUCT_SKU));
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerCartTransactionEvent(eventMeta, (String) value.get(CONTENT_GROUP), productMeta, (String) value.get(INVOICE_NUMBER));
    }


    public Map<String, String> triggerScreenViewEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerScreenViewEvent(eventMeta, (String) value.get(CONTENT_GROUP));
    }


    public Map<String, String> triggerContentViewEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerContentViewEvent(eventMeta, (String) value.get(CONTENT_GROUP));
    }


    public Map<String, String> triggerSearchEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerSearchEvent(eventMeta, (String) value.get(SEARCH_PHRASE), (String) value.get(SEARCH_RESULT));
    }


    public Map<String, String> triggerButtonClickEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerButtonClickEvent(eventMeta);
    }


    public Map<String, String> triggerCustomEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        return ORADataCollector.getInstance().triggerCustomEvent(eventMeta);
    }


    public Map<String, String> triggerVideoLoadEvent(Map<String, Object> value) {
        ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
//        return ORADataCollector.getInstance().triggerVideoLoadEvent(eventMeta);
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
