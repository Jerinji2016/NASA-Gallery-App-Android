package com.jerin.nasagalleryapp.providers

import android.content.Context
import android.util.Log
import com.jerin.nasagalleryapp.modal.ImageData
import com.jerin.nasagalleryapp.utils.Util

class DataProvider private constructor() {
    companion object {
        private const val TAG = "DataProvider"

        private var mInstance: DataProvider? = null

        fun getInstance(): DataProvider {
            if (mInstance == null) {
                mInstance = DataProvider()
            }
            return mInstance!!
        }
    }

    var images = ArrayList<ImageData>()
        private set

    fun loadImageData(context: Context) {
        try {
            images.apply {
                clear()
                addAll(Util.loadData(context))
            }
        } catch (e: Exception) {
            Log.e(TAG, "loadImageData: ", e)
        }
    }
}