package com.example.lab3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class FileWorker {
    fun readTextFromFile(activity: AppCompatActivity, filename: String): String {
        // Doesn't work - the app crashes
        //var text: String = ""
        //activity.openFileInput(filename).use { fis ->
        //    val bytes = ByteArray(fis.available())
        //    fis.read(bytes)
        //    text = String(bytes)
        //}
        //return text
        var fin: FileInputStream? = null
        var text: String = ""
        try {
            fin = activity.openFileInput(filename)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            text = String(bytes)
        } catch (ex: IOException) {
            Toast.makeText(activity, "Can't open the file", Toast.LENGTH_SHORT).show()
        } finally {
            try {
                fin?.close()
            } catch (ex: IOException) {
                Toast.makeText(activity, ex.message.toString(), Toast.LENGTH_SHORT).show()
            }
            return text
        }
    }

    fun saveText(activity: AppCompatActivity, filename: String, text: String) {
        // Try-with-resources in Kotlin is broken
        //activity.openFileOutput(filename, Context.MODE_PRIVATE or Context.MODE_APPEND).use { fos ->
        //    fos.write(text.toByteArray())
        //    Toast.makeText(activity.applicationContext, "File has been saved!", Toast.LENGTH_SHORT).show()
        //}
        var fos: FileOutputStream? = null
        try {
            fos = (activity as MainActivity).openFileOutput(filename, Context.MODE_PRIVATE or Context.MODE_APPEND)
            fos.write(text.toByteArray())
            Toast.makeText(activity.applicationContext, "File has been saved!", Toast.LENGTH_SHORT).show()
        } catch (ex: IOException) {
            Toast.makeText(activity.applicationContext, ex.message.toString(), Toast.LENGTH_SHORT).show()
        } finally {
            try {
                fos?.close()
            } catch (ex: IOException) {
                Toast.makeText(activity.applicationContext, ex.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}