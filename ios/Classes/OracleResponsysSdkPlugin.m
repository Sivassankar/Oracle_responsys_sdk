#import "OracleResponsysSdkPlugin.h"
#if __has_include(<oracle_responsys_sdk/oracle_responsys_sdk-Swift.h>)
#import <oracle_responsys_sdk/oracle_responsys_sdk-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "oracle_responsys_sdk-Swift.h"
#endif

@implementation OracleResponsysSdkPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftOracleResponsysSdkPlugin registerWithRegistrar:registrar];
}
@end
