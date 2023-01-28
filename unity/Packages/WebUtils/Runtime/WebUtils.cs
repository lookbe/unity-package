using System.Collections;
using System.Collections.Generic;
using System.Threading.Tasks;
using UnityEngine;
using UnityEngine.SceneManagement;

namespace Evo
{
    [DefaultExecutionOrder(1000)]
    public class WebUtils : MonoBehaviour
    {
        public const string EDITOR_DEEPLINK = @"com.unity3d.kharma:select-object/WebUtils";

        private static WebUtils _instance;
        public static System.Action<string> onDeepLink;

        private BrowserTabBase browserTab;

        public static WebUtils GetInstance()
        {
            return _instance;
        }

        private void Awake()
        {
            if (_instance != null && _instance != this)
            {
                Destroy(gameObject);
                return;
            }

            _instance = this;
            DontDestroyOnLoad(gameObject);

            Application.deepLinkActivated += OnDeepLink;
            if (!string.IsNullOrEmpty(Application.absoluteURL))
            {
                OnDeepLink(Application.absoluteURL);
            }
#if UNITY_EDITOR
            browserTab = gameObject.AddComponent<BrowserTabBase>();
#elif UNITY_ANDROID
            browserTab = gameObject.AddComponent<BrowserTabAndroid>();
#elif UNITY_IOS
            browserTab = gameObject.AddComponent<BrowserTabIOS>();
#endif
        }

        private void OnDeepLink(string url)
        {
            onDeepLink?.Invoke(url);
        }

        public void OpenURL(string url)
        {
            browserTab.OpenURL(url);
        }
    }
}
