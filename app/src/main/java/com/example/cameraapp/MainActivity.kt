package com.example.cameraapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

private const val REQUEST_IMAGE_CAPTURE = 1

class MainActivity : AppCompatActivity() {
    private lateinit var cameraBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraBtn = findViewById(R.id.btnTakePicture)
        cameraBtn.setOnClickListener {
            dispatchTakePictureIntent()
        }

    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            findViewById<ImageView>(R.id.imageView).setImageBitmap(imageBitmap)
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}