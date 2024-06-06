package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.R
import android.content.pm.ActivityInfo
import java.lang.Thread
import java.lang.Runnable
import java.lang.InterruptedException
import android.content.Intent
import com.example.room.MainActivity

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spalchscreen)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Thread {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val gotomain = Intent(this@splashscreen, MainActivity::class.java)
            startActivity(gotomain)
            finish()
        }.start()
    }
}