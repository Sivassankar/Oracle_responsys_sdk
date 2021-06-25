import 'dart:async';

import 'package:flutter/services.dart';

class OracleResponsysSdk {
  static const String ARGUMENTS = "arguments";
  static const String CHANNEL_NAME = "oracle_responsys_sdk";
  static const MethodChannel _channel = const MethodChannel(CHANNEL_NAME);

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<Map<String, String>> triggerCustomEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerCustomEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerApplicationStartEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerApplicationStartEvent', {ARGUMENTS: arguments});
    return version;
  }
}
