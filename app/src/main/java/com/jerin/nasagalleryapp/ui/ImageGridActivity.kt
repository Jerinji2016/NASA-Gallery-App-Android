package com.jerin.nasagalleryapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jerin.nasagalleryapp.R
import com.jerin.nasagalleryapp.providers.DataProvider
import com.jerin.nasagalleryapp.ui.adapters.RecyclerViewAdapter
import com.jerin.nasagalleryapp.utils.Util

class ImageGridActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ImageGridActivity"
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_grid)

        supportActionBar?.hide()

        DataProvider.getInstance().apply {
            loadImageData(applicationContext)

            val size = Util.getImageSize(applicationContext)
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            val recyclerViewAdapter = RecyclerViewAdapter(applicationContext, size, images)

            recyclerView.layoutManager = GridLayoutManager(this@ImageGridActivity, 3)
            recyclerView.adapter = recyclerViewAdapter
        }
    }
}