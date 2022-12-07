package com.TwentySeven.Mel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.TwentySeven.Mel.AplClass.Companion.C1
import com.TwentySeven.Mel.AplClass.Companion.DEEPL
import com.TwentySeven.Mel.AplClass.Companion.MAIN_ID
import com.TwentySeven.Mel.AplClass.Companion.apsInitLink
import com.TwentySeven.Mel.databinding.ActivityMainBinding
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var bindMainAct: ActivityMainBinding
    var checker: String = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindMainAct = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindMainAct.root)

        GlobalScope.launch(Dispatchers.IO) {
            getAdId()
        }
            val job = GlobalScope.launch(Dispatchers.IO) {
                checker = getCheckCode()
            }

            runBlocking {
                try {
                    job.join()
                } catch (_: Exception){

                }
            }

            when (checker) {
                "1" -> {
                    AppsFlyerLib.getInstance()
                        .init("QZU7taYLBUSguapudX4Tc5", conversionDataListener, applicationContext)
                    AppsFlyerLib.getInstance().start(this)
                    afNullRecordedOrNotChecker()
                }
                "0" -> {
                    startActivity(Intent(this@MainActivity, SecondActivity::class.java))
                    finish()
                }
            }

        deePP(this)

    }

    private fun getAdId(){
        val adInfo = AdvertisingIdClient(applicationContext)
        adInfo.start()
        val adIdInfo = adInfo.info.id
        Hawk.put(MAIN_ID, adIdInfo)
    }

    private val conversionDataListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            val dataGotten = data?.get("campaign").toString()
            Hawk.put(C1, dataGotten) }
        override fun onConversionDataFail(p0: String?) {}
        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
        override fun onAttributionFailure(p0: String?) {}
    }


    fun deePP(context: Context) {
        AppLinkData.fetchDeferredAppLinkData(
            context
        ) { appLinkData: AppLinkData? ->
            appLinkData?.let {
                val params = appLinkData.targetUri.host.toString()
                Hawk.put(DEEPL, params)
            }
        }
    }

    private fun afNullRecordedOrNotChecker(): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            while (NonCancellable.isActive) {
                val hawk1: String? = Hawk.get(C1)
                if (hawk1 != null) {
                    startActivity(Intent(this@MainActivity, SecondActivity::class.java))
                    finish()
                    break
                } else {
                    delay(1500)
                }
            }
        }
    }

    private suspend fun getCheckCode(): String {
        val url = URL(apsInitLink)
        val oneStr = "1"
        val activeStrn = "0"
        val urlConnection = withContext(Dispatchers.IO) {
            url.openConnection()
        } as HttpURLConnection

        return try {
            when (urlConnection.inputStream.bufferedReader().readText()) {
                "1" -> {
                    oneStr
                }
                else -> {
                    activeStrn
                }
            }
        } finally {
            urlConnection.disconnect()
        }
    }
}

