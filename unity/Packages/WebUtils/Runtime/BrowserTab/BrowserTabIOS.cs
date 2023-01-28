using System.Collections;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

namespace Evo
{
    public class BrowserTabIOS : BrowserTabBase
    {
#if UNITY_IOS
        [DllImport("__Internal")] 
        private static extern void BrowserTab_openURL(string url);
        [DllImport("__Internal")] 
        private static extern void BrowserTab_closeTab();

        private void Awake()
        {
            WebUtils.onDeepLink += onDeepLink;
        }
        public override void OpenURL(string url)
        {
            BrowserTab_openURL(url);
        }
        private void onDeepLink(string url)
        {
            BrowserTab_closeTab();
        }
#endif
    }
}
