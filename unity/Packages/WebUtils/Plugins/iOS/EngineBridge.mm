#import "EngineBridge.h"

extern UIViewController *UnityGetGLViewController();

@implementation EngineBridge
+ (instancetype)sharedInstance {
    static dispatch_once_t once;
    static id sharedInstance;
    dispatch_once(&once, ^{
        sharedInstance = [[self alloc] init];
    });
    return sharedInstance;
}

+(void)callEngine:(NSString*)method message:(NSString*)msg objectName:(NSString*)obj
{
    UnitySendMessage(obj.UTF8String, method.UTF8String, msg.UTF8String);
}
+(UIViewController*)getEngineViewController
{
    return UnityGetGLViewController();
}
@end
