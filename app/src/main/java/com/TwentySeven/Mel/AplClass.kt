package com.TwentySeven.Mel

import android.app.Application
import android.util.Log
import com.my.tracker.MyTracker
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import java.util.*

class AplClass: Application() {
    companion object {
        const val oneSIgn = "a55bf774-7034-4f29-8ec6-cc832be079d4"
        const val tmy = "26906711506010849150"
        var appsCheck = "appsChecker"
        var C1: String? = "c11"
        var link = "link"
        var MAIN_ID: String? = ""
        var DEEPL: String? = "d11"
        var myID: String? = "myID"
        var instId: String? = "instID"
        var trCheck: String? = "trChecker"
        var initApps: String? = "initApps"
        val apsInitLink: String = "http://godsofvalley.xyz/apps.txt"
    }

    override fun onCreate() {
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(oneSIgn)
        Hawk.init(this).build()

        val settings = getSharedPreferences("PREFS_NAME", 0)

        val trackerParams = MyTracker.getTrackerParams()
        val trackerConfig = MyTracker.getTrackerConfig()
        val instID = MyTracker.getInstanceId(this)
        trackerConfig.isTrackingLaunchEnabled = true
        if (settings.getBoolean("my_first_time", true)) {
            val IDIN = UUID.randomUUID().toString()
            trackerParams.setCustomUserId(IDIN)
            Hawk.put(myID, IDIN)
            Hawk.put(instId, instID)
            settings.edit().putBoolean("my_first_time", false).apply()
        } else {
            val IDIN = Hawk.get(myID, "null")
            trackerParams.setCustomUserId(IDIN)
        }
        MyTracker.initTracker(tmy, this)
    }
}