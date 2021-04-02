import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:oracle_responsys_sdk/oracle_responsys_sdk.dart';

void main() {
  const MethodChannel channel = MethodChannel('oracle_responsys_sdk');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await OracleResponsysSdk.platformVersion, '42');
  });
}
