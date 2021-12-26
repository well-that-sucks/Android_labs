package com.example.lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var buttonAudio: Button
    private lateinit var buttonVideo: Button
    private lateinit var buttonWeb: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAudio = findViewById(R.id.buttonAudio)
        buttonVideo = findViewById(R.id.buttonVideo)
        buttonWeb = findViewById(R.id.buttonWeb)

        buttonAudio.setOnClickListener {
            this.startActivity(Intent(this, AudioActivity::class.java))
        }

        buttonVideo.setOnClickListener {
            this.startActivity(Intent(this, VideoActivity::class.java))
        }

        buttonWeb.setOnClickListener {
            this.startActivity(Intent(this, WebVideoActivity::class.java))
        }

    }
}