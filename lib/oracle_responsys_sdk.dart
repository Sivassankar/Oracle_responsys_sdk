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

  static Future<Map<String, String>> triggerApplicationErrorEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerApplicationErrorEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerApplicationTerminateEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel.invokeMethod(
        'triggerApplicationTerminateEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerActivityStartEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel.invokeMethod(
        'triggerApplicationTerminateEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerApplicationEnterBackgroundEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel.invokeMethod(
        'triggerApplicationEnterBackgroundEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerApplicationEnterForegroundEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel.invokeMethod(
        'triggerApplicationEnterForegroundEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerActivityResumeEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerActivityResumeEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerActivityPauseEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerActivityPauseEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerActivityStopEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerActivityStopEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerFragmentStartEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerActivityStopEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerFragmentResumeEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerFragmentResumeEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerFragmentPauseEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerFragmentPauseEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerFragmentStopEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerFragmentStopEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerAdClickEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerAdClickEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerAdImpressionEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerAdImpressionEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerConversionEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerConversionEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerMediaEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerMediaEvent', {ARGUMENTS: arguments});
    return version;
  }

  static Future<Map<String, String>> triggerProductEvent(
      Map<String, Object> arguments) async {
    final Map<String, String> version = await _channel
        .invokeMethod('triggerProductEvent', {ARGUMENTS: arguments});
    return version;
  }
}
