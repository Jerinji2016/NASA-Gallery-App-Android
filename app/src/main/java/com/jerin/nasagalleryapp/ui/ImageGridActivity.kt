package com.jerin.nasagalleryapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.jerin.nasagalleryapp.R
import com.jerin.nasagalleryapp.providers.DataProvider
import com.jerin.nasagalleryapp.ui.adapters.ImageGridViewAdapter
import com.jerin.nasagalleryapp.utils.Util

class ImageGridActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ImageGridActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_grid)

        supportActionBar?.hide()

        DataProvider.getInstance().apply {
            loadImageData(applicationContext)

            val size = Util.getImageSize(applicationContext)
            val gridView = findViewById<GridView>(R.id.image_grid_view)
            gridView.adapter = ImageGridViewAdapter(applicationContext, size, images)
        }

        Log.d(TAG, "onCreate: complete")
    }
}