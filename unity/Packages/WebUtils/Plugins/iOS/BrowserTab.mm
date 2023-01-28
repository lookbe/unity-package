#import "BrowserTabNative.h"

extern "C"
{
    void BrowserTab_openURL(const char* url)
    {
        [BrowserTab openUrl:[NSString stringWithUTF8String:url]];
    }

    void BrowserTab_closeTab()
    {
        [BrowserTab closeTab];
    }
}
