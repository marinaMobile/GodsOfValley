package com.TwentySeven.Mel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.TwentySeven.Mel.databinding.ActivityFogodBinding
import kotlin.random.Random


class Fogod : AppCompatActivity() {
    lateinit var gameOfGod: ActivityFogodBinding
    var counter: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameOfGod = ActivityFogodBinding.inflate(layoutInflater)
        setContentView(gameOfGod.root)
        vibrate()

        gameOfGod.giftOfgod.setOnClickListener{
            counter += 1
            gameOfGod.progressGift.progress = counter

            if (counter==100) {
                startActivity(Intent(this@Fogod, AchiveAct::class.java))
                finish()
            } else if (counter == 50) {
                gameOfGod.textText.text = "Keep going!"
            }
        }

    }

    fun randomPosition() = Random.nextInt(-500, 500).toFloat()

    fun vibrate() {
        gameOfGod.giftOfgod.animate()
            .translationX(randomPosition())
            .translationY(randomPosition())
            .withEndAction(::vibrate)
            .start()
    }
}