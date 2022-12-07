package com.TwentySeven.Mel

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.TwentySeven.Mel.databinding.ActivityAchiveBinding

class AchiveAct : AppCompatActivity() {
    lateinit var binAchive: ActivityAchiveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binAchive = ActivityAchiveBinding.inflate(layoutInflater)
        setContentView(binAchive.root)
        val firtTime = getSharedPreferences("PREFS", 0)

        if (firtTime.getBoolean("my_first", true)) {
            opsdfbegrnDialog()
            firtTime.edit().putBoolean("my_first", false).apply()
        }

        binAchive.tropIm.setOnClickListener{
            startActivity(Intent(this@AchiveAct, Randam::class.java))
            finish()
        }
    }
    private fun opsdfbegrnDialog() {
        val wfhgewg4tg = Dialog(this@AchiveAct)
        wfhgewg4tg.setContentView(R.layout.dial_wind)
        wfhgewg4tg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        wfhgewg4tg.show()
        val sxcetghnu: ImageView = wfhgewg4tg.findViewById(R.id.closeBut)
        sxcetghnu.setOnClickListener{
            wfhgewg4tg.dismiss()
        }
    }
}


