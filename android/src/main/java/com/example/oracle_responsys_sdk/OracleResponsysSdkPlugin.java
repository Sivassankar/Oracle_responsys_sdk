package com.example.oracle_responsys_sdk;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import com.oracle.cx.mobilesdk.ORAVideoMeta;
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
    private static String CLIP_NAME = "clipName";
    private static String CLIP_TYPE = "clipType";
    private static String CLIP_TITLE = "clipTitle";
    private static String CLIP_DURATION_IN_SECONDS = "clipDurationInSeconds";
    private static String CLIP_DURATION_IN_MINUTES = "clipDurationInMinutes";
    private static String CLIP_CURRENT_DURATION_IN_SECONDS = "clipCurrentDurationInSeconds";
    private static String CLIP_CURRENT_DURATION_IN_MINUTES = "clipCurrentDurationInSeconds";
    private static String CLIP_PERCENTAGE = "clipPercentage";
    private static String CLIP_EXTENSION = "clipExtension";
    private static String CLIP_PROVIDER = "clipProvider";
    private static String CLIP_SOURCE = "clipSource";
    private static String CLIP_VOLUME = "clipVolume";
    private static String PLAY_RESOLUTION = "playerResolution";
    private static String CLIP_RESOLUTION = "clipResolution";
    private static String CLIP_SCREEN_MODE = "clipScreenMode";
    private static String CLIP_DURATION_BASED_ON_BINS = "clipDurationBasedOnBins";
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
    private static final String URL = "URL";
    private static final String KEY = "key";
    private static final String VALUE = "value";
    private static final String PERSIST = "persist";// Boolean
    private ORACoreDataContainer container;
    private ORADataCollector mORADataCollector;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), CHANNEL_NAME);
        channel.setMethodCallHandler(this);
        init();
    }

    private void init() {
        mORADataCollector = ORADataCollector.getInstance();
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        switch (call.method) {
            case "triggerApplicationStartEvent": {
                triggerApplicationStartEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerApplicationErrorEvent": {
                triggerApplicationErrorEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerApplicationTerminateEvent": {
                triggerApplicationTerminateEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerActivityStartEvent": {
                triggerActivityStartEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerApplicationEnterBackgroundEvent": {
                triggerApplicationEnterBackgroundEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerApplicationEnterForegroundEvent": {
                triggerApplicationEnterForegroundEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerActivityResumeEvent": {
                triggerActivityResumeEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerActivityPauseEvent": {
                triggerActivityPauseEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerActivityStopEvent": {
                triggerActivityStopEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerFragmentStartEvent": {
                triggerFragmentStartEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerFragmentResumeEvent": {
                triggerFragmentResumeEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerFragmentPauseEvent": {
                triggerFragmentPauseEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerFragmentStopEvent": {
                triggerFragmentStopEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerAdClickEvent": {
                triggerAdClickEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerAdImpressionEvent": {
                triggerAdImpressionEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerConversionEvent": {
                triggerConversionEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerMediaEvent": {
                triggerMediaEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerProductEvent": {
                triggerProductEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerCartTransactionEvent": {
                triggerCartTransactionEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerScreenViewEvent": {
                triggerScreenViewEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerContentViewEvent": {
                triggerContentViewEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerSearchEvent": {
                triggerSearchEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerButtonClickEvent": {
                triggerButtonClickEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerCustomEvent": {
                triggerCustomEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoLoadEvent": {
                triggerVideoLoadEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoLoadMetaEvent": {
                triggerVideoLoadMetaEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoPlayEvent": {
                triggerVideoPlayEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoResumeEvent": {
                triggerVideoResumeEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoPauseEvent": {
                triggerVideoPauseEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoSeekEvent": {
                triggerVideoSeekEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoCompletionEvent": {
                triggerVideoCompletionEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoVolumeChangeEvent": {
                triggerVideoVolumeChangeEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoQuartileEvent": {
                triggerVideoQuartileEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoBeaconEvent": {
                triggerVideoBeaconEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerVideoErrorEvent": {
                triggerVideoErrorEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerNotificationEvent": {
                triggerNotificationEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "triggerDragAndDropEvent": {
                triggerDragAndDropEvent((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "openUrl": {
                openUrl((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "scheduleSend": {
                scheduleSend(result);
                break;
            }
            case "scheduleSendAll": {
                scheduleSendAll(result);
                break;
            }
            case "pauseEventTransmission": {
                pauseEventTransmission(result);
                break;
            }
            case "resumeEventTransmission": {
                resumeEventTransmission(result);
                break;
            }
            case "getEventsCount": {
                getEventsCount(result);
                break;
            }
            case "isRunning": {
                isRunning(result);
                break;
            }
            case "setSetting": {
                setSetting((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "appendSessionParamsToUrlAsync": {
                appendSessionParamsToUrlAsync((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "appendSessionParamsToUrl": {
                appendSessionParamsToUrl((Map<String, Object>) call.argument(ARGUMENTS), result);
                break;
            }
            case "setSessionInfo": {
                setSessionInfo(result);
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

    public void triggerApplicationStartEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerApplicationStartEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            result.success(future.get());
        } catch (InterruptedException e) {
            result.error(null, e.getMessage(), null);
        } catch (ExecutionException e) {
            result.error(null, e.getMessage(), null);
        }
    }

    public void triggerApplicationErrorEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerApplicationErrorEvent((String) value.get(APPLICATION_NAME), (String) value.get(ERROR_MESSAGE), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            result.success(future.get());
        } catch (InterruptedException e) {
            result.error(null, e.getMessage(), null);
        } catch (ExecutionException e) {
            result.error(null, e.getMessage(), null);
        }
    }

    public void triggerApplicationTerminateEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerApplicationTerminateEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            result.success(future.get());
        } catch (InterruptedException e) {
            result.error(null, e.getMessage(), null);
        } catch (ExecutionException e) {
            result.error(null, e.getMessage(), null);
        }
    }

    public void triggerActivityStartEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerActivityStartEvent((String) value.get(ACTIVITY_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            result.success(future.get());
        } catch (InterruptedException e) {
            result.error(null, e.getMessage(), e.getStackTrace());
        } catch (ExecutionException e) {
            result.error(null, e.getMessage(), null);
        }
    }

    public Map<String, String> triggerApplicationEnterBackgroundEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerApplicationEnterBackgroundEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerApplicationEnterForegroundEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerApplicationEnterForegroundEvent((String) value.get(APPLICATION_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerActivityResumeEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerActivityResumeEvent((String) value.get(ACTIVITY_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerActivityPauseEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerActivityPauseEvent((String) value.get(ACTIVITY_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerActivityStopEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerActivityStopEvent((String) value.get(ACTIVITY_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerFragmentStartEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerFragmentStartEvent((String) value.get(ACTIVITY_NAME), (String) value.get(FRAGMENT_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerFragmentResumeEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerFragmentResumeEvent((String) value.get(ACTIVITY_NAME), (String) value.get(FRAGMENT_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerFragmentPauseEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerFragmentPauseEvent((String) value.get(ACTIVITY_NAME), (String) value.get(FRAGMENT_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerFragmentStopEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerFragmentStopEvent((String) value.get(ACTIVITY_NAME), (String) value.get(FRAGMENT_NAME), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerAdClickEvent(final Map<String, Object> value, Result result) {
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerAdClickEvent(eventMeta, (String) value.get(AD_NAME));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerAdImpressionEvent(final Map<String, Object> value, Result result) {
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerAdImpressionEvent(eventMeta, (String[]) value.get(AD_NAMES));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerConversionEvent(final Map<String, Object> value, Result result) {
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerConversionEvent(eventMeta, (String) value.get(CONTENT_GROUP), (String) value.get(CONVERSION_NAME));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerMediaEvent(final Map<String, Object> value, Result result) {
        final ORAEventMedia eventMedia = new ORAEventMedia((String) value.get(MEDIA_NAME), (String) value.get(MEDIA_TYPE), (String) value.get(MEDIA_EVENT_TYPE));
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerMediaEvent(eventMeta, (String) value.get(CONTENT_GROUP), eventMedia);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerProductEvent(final Map<String, Object> value, Result result) {
        final ORAProductMeta productMeta = new ORAProductMeta((String) value.get(TYPE), (String[]) value.get(PRODUCT_SUB_TOTAL), (String[])
                value.get(PRODUCT_UNIT), (String[]) value.get(PRODUCT_ID), (String[]) value.get(PRODUCT_SKU));

        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerProductEvent(eventMeta, (String) value.get(CONTENT_GROUP), productMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerCartTransactionEvent(final Map<String, Object> value, Result result) {
        final ORAProductMeta productMeta = new ORAProductMeta((String) value.get(TYPE), (String[]) value.get(PRODUCT_SUB_TOTAL), (String[])
                value.get(PRODUCT_UNIT), (String[]) value.get(PRODUCT_ID), (String[]) value.get(PRODUCT_SKU));
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerCartTransactionEvent(eventMeta, (String) value.get(CONTENT_GROUP), productMeta, (String) value.get(INVOICE_NUMBER));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerScreenViewEvent(final Map<String, Object> value, Result result) {
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerScreenViewEvent(eventMeta, (String) value.get(CONTENT_GROUP));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerContentViewEvent(final Map<String, Object> value, Result result) {
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerContentViewEvent(eventMeta, (String) value.get(CONTENT_GROUP));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerSearchEvent(final Map<String, Object> value, Result result) {
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerSearchEvent(eventMeta, (String) value.get(SEARCH_PHRASE), (String) value.get(SEARCH_RESULT));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerButtonClickEvent(final Map<String, Object> value, Result result) {
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerButtonClickEvent(eventMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerCustomEvent(final Map<String, Object> value, Result result) {
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerCustomEvent(eventMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerVideoLoadEvent(final Map<String, Object> value, Result result) {
         final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoLoadEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Map<String, String> triggerVideoLoadMetaEvent(Map<String, Object> value, Result result) {
        final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoLoadMetaEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerVideoPlayEvent(Map<String, Object> value, Result result) {
        final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoPlayEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerVideoResumeEvent(Map<String, Object> value, Result result) {
        final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoResumeEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerVideoPauseEvent(Map<String, Object> value, Result result) {
        final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoPauseEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerVideoSeekEvent(Map<String, Object> value, Result result) {
        final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoSeekEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerVideoCompletionEvent(Map<String, Object> value, Result result) {
        final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoCompletionEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerVideoVolumeChangeEvent(Map<String, Object> value, Result result) {
        final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoVolumeChangeEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerVideoQuartileEvent(Map<String, Object> value, Result result) {
        final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoQuartileEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerVideoBeaconEvent(Map<String, Object> value, Result result) {
        final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoBeaconEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerVideoErrorEvent(Map<String, Object> value, Result result) {
        final ORAVideoMeta videoMeta = buildVideoMeta(value);
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerVideoErrorEvent(eventMeta, videoMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerNotificationEvent(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerNotificationEvent((String) value.get(ACTIVITY_NAME), (String) value.get(NOTIFICATION_MESSAGE), (Map<String, String>) value.get(CUSTOM_DATA));
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> triggerDragAndDropEvent(final Map<String, Object> value, Result result) {
        final ORAEventMeta eventMeta;
        if (value.containsKey(EVENT_TYPE)) {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (String) value.get(EVENT_TYPE), (Map<String, String>) value.get(CUSTOM_DATA));
        } else {
            eventMeta = new ORAEventMeta((String) value.get(EVENT_PATH), (String) value.get(EVENT_DESCRIPTION), (Map<String, String>) value.get(CUSTOM_DATA));
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<String, String>> future = executor.submit(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return mORADataCollector.triggerDragAndDropEvent(eventMeta);
            }
        });
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void openUrl(Map<String, Object> value, Result result) {
        mORADataCollector.openUrl(activity, (String) value.get(URL));
        result.success(null);
    }

    public void enableORAInWebView(WebView webView) {
    }

    public void scheduleSend(Result result) {
        mORADataCollector.scheduleSend();
        result.success(null);
    }

    public void scheduleSendAll(Result result) {
        mORADataCollector.scheduleSendAll();
        result.success(null);
    }

    public void pauseEventTransmission(Result result) {
        mORADataCollector.pauseEventTransmission();
        result.success(null);
    }

    public void resumeEventTransmission(Result result) {
        mORADataCollector.pauseEventTransmission();
        result.success(null);
    }

    public void getEventsCount(Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return mORADataCollector.getEventsCount();
            }
        });
        try {
            result.success(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
            result.success(null);
        } catch (ExecutionException e) {
            e.printStackTrace();
            result.success(null);
        }
    }

    public void isRunning(Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return mORADataCollector.isRunning();
            }
        });
        try {
            result.success((future.get()) ? "True" : "False");
        } catch (InterruptedException e) {
            e.printStackTrace();
            result.success(null);
        } catch (ExecutionException e) {
            e.printStackTrace();
            result.success(null);
        }
    }

    public void setSetting(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return mORADataCollector.setSetting((String) value.get(KEY), (String) value.get(KEY), (Boolean) value.get(PERSIST));
            }
        });
        try {
            result.success((future.get()) ? "True" : "False");
        } catch (InterruptedException e) {
            e.printStackTrace();
            result.success(null);
        } catch (ExecutionException e) {
            e.printStackTrace();
            result.success(null);
        }
    }

    public void appendSessionParamsToUrlAsync(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Future<String>> future = executor.submit(new Callable<Future<String>>() {
            @Override
            public Future<String> call() throws Exception {
                return mORADataCollector.appendSessionParamsToUrlAsync((String) value.get(URL));
            }
        });
        try {
            result.success(future.get().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
            result.success(null);
        } catch (ExecutionException e) {
            e.printStackTrace();
            result.success(null);
        }
    }

    public void appendSessionParamsToUrl(final Map<String, Object> value, Result result) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return mORADataCollector.appendSessionParamsToUrl((String) value.get(VALUE));
            }
        });
        try {
            result.success(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
            result.success(null);
        } catch (ExecutionException e) {
            e.printStackTrace();
            result.success(null);
        }
    }

    public void setSessionInfo(Result result) {
        mORADataCollector.setSessionInfo(activity);
        result.success(null);
    }


    ORAVideoMeta buildVideoMeta(Map<String, Object> value) {
        ORAVideoMeta.ORAVideoMetaBuilder videoMeta;
        if (value.containsKey(CLIP_NAME) && value.containsKey(CLIP_TYPE)) {
            videoMeta = new ORAVideoMeta.ORAVideoMetaBuilder((String) value.get(CLIP_NAME), (String) value.get(CLIP_TYPE));
            if (value.containsKey(CLIP_TITLE)) {
                videoMeta.clipTitle((String) value.get(CLIP_TITLE));
            }
            if (value.containsKey(CLIP_DURATION_IN_SECONDS)) {
                videoMeta.clipDurationInSeconds((String) value.get(CLIP_DURATION_IN_SECONDS));
            }
            if (value.containsKey(CLIP_DURATION_IN_MINUTES)) {
                videoMeta.clipDurationInMinutes((String) value.get(CLIP_DURATION_IN_MINUTES));
            }
            if (value.containsKey(CLIP_CURRENT_DURATION_IN_SECONDS)) {
                videoMeta.clipCurrentDurationInSeconds((String) value.get(CLIP_CURRENT_DURATION_IN_SECONDS));
            }
            if (value.containsKey(CLIP_CURRENT_DURATION_IN_MINUTES)) {
                videoMeta.clipCurrentDurationInMinutes((String) value.get(CLIP_CURRENT_DURATION_IN_MINUTES));
            }
            if (value.containsKey(CLIP_PERCENTAGE)) {
                videoMeta.clipPercentage((String) value.get(CLIP_PERCENTAGE));
            }
            if (value.containsKey(CLIP_EXTENSION)) {
                videoMeta.clipExtension((String) value.get(CLIP_EXTENSION));
            }
            if (value.containsKey(CLIP_PROVIDER)) {
                videoMeta.clipProvider((String) value.get(CLIP_PROVIDER));
            }
            if (value.containsKey(CLIP_SOURCE)) {
                videoMeta.clipSource((String) value.get(CLIP_SOURCE));
            }
            if (value.containsKey(CLIP_SOURCE)) {
                videoMeta.clipSource((String) value.get(CLIP_SOURCE));
            }
            if (value.containsKey(CLIP_VOLUME)) {
                videoMeta.clipVolume((String) value.get(CLIP_VOLUME));
            }
            if (value.containsKey(PLAY_RESOLUTION)) {
                videoMeta.playerResolution((String) value.get(PLAY_RESOLUTION));
            }
            if (value.containsKey(CLIP_RESOLUTION)) {
                videoMeta.clipResolution((String) value.get(CLIP_RESOLUTION));
            }
            if (value.containsKey(CLIP_SCREEN_MODE)) {
                videoMeta.clipScreenMode((String) value.get(CLIP_SCREEN_MODE));
            }
            if (value.containsKey(CLIP_DURATION_BASED_ON_BINS)) {
                videoMeta.clipDurationBasedOnBins((String) value.get(CLIP_DURATION_BASED_ON_BINS));
            }
            return videoMeta.build();
        }
        return null;
    }
}
