package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import android.widget.TextView
import java.io.FileInputStream
import java.io.IOException


class DisplayFileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_file)
        val textFileContainer: TextView = findViewById(R.id.textViewFileContents)
        val fileContents = FileWorker().readTextFromFile(this, "results.txt")
        if (fileContents != "") {
            textFileContainer.text = fileContents
        }
    }
}