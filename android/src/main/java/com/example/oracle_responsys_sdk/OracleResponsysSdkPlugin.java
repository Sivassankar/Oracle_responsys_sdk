package com.example.oracle_responsys_sdk;

import android.app.Activity;

import androidx.annotation.NonNull;



import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

import com.oracle.cx.mobilesdk.*;

/** OracleResponsysSdkPlugin */
public class OracleResponsysSdkPlugin extends Activity implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "oracle_responsys_sdk");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {


      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else {
      result.notImplemented();
    }
 if (call.method.equals("getUserRegisterId")) {

   PushIOManager.getInstance(this).registerApp();
   PushIOManager.getInstance(this).registerUserId("sivasankar6688@gmail.com");
//     PushIOManager.getInstance(getApplicationContext()).setOracleCXAccountId("YOUR_ACCOUNT_GUID");
     result.success( PushIOManager.getInstance(this).getRegisteredUserId());
    } else {
      result.notImplemented();
    }

  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
