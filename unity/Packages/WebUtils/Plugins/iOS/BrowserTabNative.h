#import <SafariServices/SafariServices.h>

@interface TabDelegate : NSObject <SFSafariViewControllerDelegate>
@end

@interface BrowserTab : NSObject
+(void)openUrl:(NSString*)url;
+(void)closeTab;
@end
