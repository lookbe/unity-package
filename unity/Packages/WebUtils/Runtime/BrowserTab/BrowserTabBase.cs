using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Evo
{
    public class BrowserTabBase : MonoBehaviour
    {
        public virtual void OpenURL(string url)
        {
            Application.OpenURL(url);
        }
    }
}
