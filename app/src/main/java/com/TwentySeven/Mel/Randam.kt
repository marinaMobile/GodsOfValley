package com.TwentySeven.Mel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.TwentySeven.Mel.databinding.ActivityRandamBinding

class Randam : AppCompatActivity() {
    lateinit var bindRandam: ActivityRandamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindRandam = ActivityRandamBinding.inflate(layoutInflater)
        setContentView(bindRandam.root)

        bindRandam.gift.startAnimation(AnimationUtils.loadAnimation(this,R.anim.shake))

        bindRandam.gift.setOnClickListener{
            startActivity(Intent(this, Fogod::class.java))
            finish()
        }
    }
}