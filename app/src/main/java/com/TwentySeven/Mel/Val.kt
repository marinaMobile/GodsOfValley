package com.TwentySeven.Mel

import android.net.Uri
import android.os.Build
import android.webkit.ValueCallback
import android.webkit.WebSettings
import android.webkit.WebView

class Val {

    companion object {
        // the same for Android 5.0 methods only
        var hfgjrtgjhkh: ValueCallback<Array<Uri>>? = null
        var kgjfhdkxf: String? = null
        val af_id = "deviceID="
        val subOne = "sub_id_1="
        val adid = "ad_id="
        val sub4 = "sub_id_4="
        val sub5 = "sub_id_5="
        val sub6 = "sub_id_6="
        val naming = "naming"
        val depp = "deeporg"
        val kiokjjlikjhmkij = Build.VERSION.RELEASE
        val pack = "com.TwentySeven.Mel"
    }

    fun getSettings(webView: WebView): WebView {
        val wstwstwstwst = webView.settings
        wstwstwstwst.javaScriptEnabled = true
        wstwstwstwst.useWideViewPort = true
        wstwstwstwst.loadWithOverviewMode = true
        wstwstwstwst.allowFileAccess = true
        wstwstwstwst.domStorageEnabled = true
        wstwstwstwst.userAgentString = wstwstwstwst.userAgentString.replace("; wv", "")
        wstwstwstwst.javaScriptCanOpenWindowsAutomatically = true
        wstwstwstwst.setSupportMultipleWindows(false)
        wstwstwstwst.displayZoomControls = false
        wstwstwstwst.builtInZoomControls = true
        wstwstwstwst.setSupportZoom(true)
        wstwstwstwst.pluginState = WebSettings.PluginState.ON
        wstwstwstwst.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        wstwstwstwst.setAppCacheEnabled(true)
        wstwstwstwst.allowContentAccess = true
        return webView
    }

}