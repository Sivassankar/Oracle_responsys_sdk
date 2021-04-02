
import 'dart:async';

import 'package:flutter/services.dart';

class OracleResponsysSdk {
  static const MethodChannel _channel =
      const MethodChannel('oracle_responsys_sdk');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
