package com.jerin.nasagalleryapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jerin.nasagalleryapp.R
import kotlin.properties.Delegates

class ImageDetailsActivity : AppCompatActivity() {
    private var index by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        supportActionBar?.hide()

        index = intent.getIntExtra("index", 0)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.image_details_fragment_container, ImageDetailsFragment(index))
            addToBackStack(null)
            commit()
        }
    }

}