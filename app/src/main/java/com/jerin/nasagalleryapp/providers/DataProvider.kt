package com.jerin.nasagalleryapp.providers

import android.content.Context
import android.util.Log
import com.jerin.nasagalleryapp.modal.ImageData
import com.jerin.nasagalleryapp.utils.Util
import java.util.*

class DataProvider(private val context: Context) : Observable() {
    companion object {
        private const val TAG = "DataProvider"
    }

    private val images = ArrayList<ImageData>()

    init {
        loadImageData()
    }

    var hasFailedToLoadData: Boolean = false
        private set

    private fun loadImageData() {
        try {
            images.apply {
                clear()
                addAll(Util.loadData(context))
                hasFailedToLoadData = false
            }
        } catch (e: Exception) {
            Log.e(TAG, "loadImageData: ", e)
            hasFailedToLoadData = true
        } finally {
            notifyObservers()
        }
    }
}