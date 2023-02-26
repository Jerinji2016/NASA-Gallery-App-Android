package com.jerin.nasagalleryapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jerin.nasagalleryapp.R
import com.jerin.nasagalleryapp.modal.ImageData
import com.jerin.nasagalleryapp.providers.DataProvider

class ImageDetailsActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val TAG = "ImageDetailsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)

        supportActionBar?.hide()

        DataProvider.getInstance().apply {
            loadImageData(applicationContext)
            val image = images.first()
            initUI(image)
        }
    }

    private fun initUI(image: ImageData) {
        val title = findViewById<TextView>(R.id.image_detail_title)
        title.text = image.title

        val date = findViewById<TextView>(R.id.image_detail_date)
        date.text = image.formattedDate

        val imageView = findViewById<ImageView>(R.id.image_detail_view)

        Glide.with(applicationContext)
            .load(image.url)
            .error(R.drawable.image_error)
            .centerCrop()
            .into(imageView)
    }

    override fun onClick(view: View?) {
        Log.d(TAG, "onClick: ")
        if (view == null) return

        if (view.id == R.id.image_details_back_button) {
            finish()
            return
        }
    }
}