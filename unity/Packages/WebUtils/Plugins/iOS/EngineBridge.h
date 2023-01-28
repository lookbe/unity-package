@interface EngineBridge : NSObject
+(instancetype)sharedInstance;
+(void)callEngine:(NSString*)method message:(NSString*)msg objectName:(NSString*)obj;
+(UIViewController*)getEngineViewController;
@end
