package com.example.lab4

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class WebVideoActivity : AppCompatActivity() {
    private lateinit var address : EditText
    private lateinit var buttonSearch : Button
    private lateinit var player : VideoView
    private lateinit var mediaController : MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_video)

        address = findViewById(R.id.addressField)
        buttonSearch = findViewById(R.id.buttonSearch)
        player = findViewById(R.id.videoPlayer)
        mediaController = MediaController(this)

        buttonSearch.setOnClickListener {
            player.setVideoURI(Uri.parse(address.text.toString().trim()))
            //player.setVideoPath(address.text.toString().trim())
            player.setMediaController(mediaController)
            mediaController.setMediaPlayer(player)
            player.start()
        }
    }
}