package com.example.oracle_responsys_sdk_example;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pushio.manager.PushIOManager;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {


    private static final String CHANNEL = "oracle_responsys_sdk";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(getFlutterEngine());

        new MethodChannel(getFlutterEngine().getDartExecutor().getBinaryMessenger(), CHANNEL).setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                        if (call.method.equals("registerAppId")) {
                            PushIOManager.getInstance(getApplicationContext()).registerApp();
                            PushIOManager.getInstance(getApplicationContext()).registerUserId("sivasankar6688@gmail.com");
                            result.success(PushIOManager.getInstance(getApplicationContext()).getRegisteredUserId() );


                        }
                    }});

    }
}
