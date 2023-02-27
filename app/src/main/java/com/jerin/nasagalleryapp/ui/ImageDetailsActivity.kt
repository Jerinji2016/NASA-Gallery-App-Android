package com.jerin.nasagalleryapp.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.jerin.nasagalleryapp.R
import com.jerin.nasagalleryapp.modal.ImageData
import com.jerin.nasagalleryapp.providers.DataProvider
import com.jerin.nasagalleryapp.ui.bottom_modal_sheet.BottomModalSheet
import kotlin.properties.Delegates

class ImageDetailsActivity : AppCompatActivity() {
    private var index by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)

        supportActionBar?.hide()

        index = intent.getIntExtra("index", 0)

        DataProvider.getInstance().apply {
            val image = images.elementAt(index)
            initUI(image)
        }

        registerClickListeners()
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

    private fun registerClickListeners() {
        val backButton = findViewById<MaterialButton>(R.id.image_details_back_button)
        backButton.setOnClickListener {
            finish()
        }

        val showMoreButton = findViewById<MaterialButton>(R.id.show_more_button)
        showMoreButton.setOnClickListener {
            val bottomModalSheet = BottomModalSheet(index)
            bottomModalSheet.show(supportFragmentManager, "BottomModalSheet")
        }
    }
}