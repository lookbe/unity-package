#import "BrowserTabNative.h"
#import "EngineBridge.h"

@implementation TabDelegate
+ (instancetype)sharedInstance {
    static dispatch_once_t once;
    static id sharedInstance;
    dispatch_once(&once, ^{
        sharedInstance = [[self alloc] init];
    });
    return sharedInstance;
}
- (void)safariViewControllerDidFinish:(SFSafariViewController *)controller
{
    NSLog(@"safariViewControllerDidFinish");
}
- (void)safariViewController:(SFSafariViewController *)controller didCompleteInitialLoad:(BOOL)didLoadSuccessfully
{
    NSLog(@"safariViewController didCompleteInitialLoad");
}
- (void)safariViewController:(SFSafariViewController *)controller initialLoadDidRedirectToURL:(NSURL *)URL
{
    // [EngineBridge callEngine:@"OnURL" message:[URL absoluteString] objectName:@"WebUtils"];
}
@end

@implementation BrowserTab
+(void)openUrl:(NSString*)url
{
    SFSafariViewController *safariVC = [[SFSafariViewController alloc]initWithURL:[NSURL URLWithString:url] entersReaderIfAvailable:NO];
    safariVC.delegate = [TabDelegate sharedInstance];
    [[EngineBridge getEngineViewController] presentViewController:safariVC animated:YES completion:nil];
}
+(void)closeTab
{
    [[EngineBridge getEngineViewController] dismissViewControllerAnimated:YES completion:nil];
}
@end
