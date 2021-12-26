package com.example.lab4

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.result.contract.ActivityResultContracts

class VideoActivity : AppCompatActivity() {
    private lateinit var buttonBrowse : Button
    private lateinit var videoPlayer : VideoView
    private lateinit var mediaController : MediaController

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val uploadFileUri = data?.data
            videoPlayer.setVideoURI(uploadFileUri)
            videoPlayer.setMediaController(mediaController)
            mediaController.setMediaPlayer(videoPlayer)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        buttonBrowse = findViewById(R.id.buttonBrowse)
        videoPlayer = findViewById(R.id.videoViewPlayer)
        mediaController = MediaController(this)

        buttonBrowse.setOnClickListener {
            mediaController.hide()
            videoPlayer.stopPlayback()
            val chooseVideoIntent = Intent(Intent.ACTION_GET_CONTENT)
            chooseVideoIntent.type = "video/*"
            resultLauncher.launch(Intent.createChooser(chooseVideoIntent, "Select video file"))
        }
    }

}