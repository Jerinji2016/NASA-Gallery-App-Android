package com.jerin.nasagalleryapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jerin.nasagalleryapp.R
import com.jerin.nasagalleryapp.providers.DataProvider
import com.jerin.nasagalleryapp.utils.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*

class ImageGridActivity : AppCompatActivity(), Observer {
    companion object {
        private const val TAG = "ImageGridActivity"
    }

    private val provider = DataProvider(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_grid)
        provider.addObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {
        Log.d(TAG, "update: ")
    }
}