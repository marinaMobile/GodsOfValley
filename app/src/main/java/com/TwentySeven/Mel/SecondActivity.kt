package com.TwentySeven.Mel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        GlobalScope.launch {
            job
        }
    }

    private val job: Job = GlobalScope.launch(Dispatchers.IO) {
        val countyCode: String = getData().toString()
        val countriesPool = getDataDev().toString()
        val deeplink: String? = Hawk.get(AplClass.DEEPL, "null") //здесь нужен дефолтный ноль

        val appsCh = Hawk.get(AplClass.appsCheck, "null")
        val naming: String? = Hawk.get(AplClass.C1)
        if (appsCh == "1") {
                    //вот тут добавил диплинк в or
                    if (naming!!.contains("tdb2") || countriesPool.contains(countyCode) || deeplink!!.contains("tdb2")) {
                        Log.d("Apps Checker", "naming: $naming")
                        startActivity(Intent(this@SecondActivity, Valley::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@SecondActivity, Randam::class.java))
                        finish()
                    }
        } else if (countriesPool.contains(countyCode)) {
            startActivity(Intent(this@SecondActivity, Valley::class.java))
            finish()
        } else {
            startActivity(Intent(this@SecondActivity, Randam::class.java))
            finish()
        }
    }

    //Data API
    private suspend fun getData(): String? {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://pro.ip-api.com/")
            .build()
            .create(ApiInterface::class.java)
        return retrofitBuilder.getData().body()?.countryCode
    }

    //Data Hosting
    private suspend fun getDataDev(): String? {
        val retroBuildTwo = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://godsofvalley.xyz/")
            .build()
            .create(ApiInterface::class.java)

        val linkView = retroBuildTwo.getDataDev().body()?.view
        val appsChecker = retroBuildTwo.getDataDev().body()?.appsChecker
        val trChecker = retroBuildTwo.getDataDev().body()?.trChecker
        Hawk.put(AplClass.appsCheck, appsChecker)
        Hawk.put(AplClass.link, linkView)
        Hawk.put(AplClass.trCheck, trChecker)
        return retroBuildTwo.getDataDev().body()?.geo
    }
}