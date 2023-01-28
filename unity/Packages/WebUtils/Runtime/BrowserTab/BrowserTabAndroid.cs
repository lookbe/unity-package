using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Android;

namespace Evo
{
    public class BrowserTabAndroid : BrowserTabBase
    {
        public override void OpenURL(string url)
        {
#if UNITY_ANDROID
            AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
            AndroidJavaObject context = activity.Call<AndroidJavaObject>("getApplicationContext");

            AndroidJavaClass browserTab = new AndroidJavaClass("com.evogames.webutils.BrowserTab");
            browserTab.CallStatic("OpenURL", context, url);
#endif
        }
    }
}
