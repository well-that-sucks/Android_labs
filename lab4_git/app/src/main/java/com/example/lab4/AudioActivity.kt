package com.example.lab4

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class AudioActivity : AppCompatActivity() {
    private lateinit var buttonStart : Button
    private lateinit var buttonPause : Button
    private lateinit var buttonStop : Button
    private var mp: MediaPlayer? = null

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val uploadFileUri = data?.data
            mp = MediaPlayer.create(this, uploadFileUri)
            mp?.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        buttonStart = findViewById(R.id.buttonStart)
        buttonPause = findViewById(R.id.buttonPause)
        buttonStop = findViewById(R.id.buttonStop)

        buttonStart.setOnClickListener {
            if (mp == null) {
                val choseAudioIntent = Intent(Intent.ACTION_GET_CONTENT)
                choseAudioIntent.type = "audio/*"
                resultLauncher.launch(Intent.createChooser(choseAudioIntent, "Select audio file"))
            } else {
                mp?.start()
            }
        }

        buttonPause.setOnClickListener {
            if (mp != null) {
                mp?.pause()
            }
        }

        buttonStop.setOnClickListener {
            if (mp != null) {
                mp?.stop()
                mp?.release()
                mp = null
            }
        }
    }
}