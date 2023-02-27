package com.jerin.nasagalleryapp.providers

import android.content.Context
import android.util.Log
import com.jerin.nasagalleryapp.modal.ImageData
import com.jerin.nasagalleryapp.utils.Util

//  Simple single instance provider class
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
                val images = Util.loadData(context).apply {
                    sortByDescending {
                        it.date
                    }
                }
                addAll(images)
            }
        } catch (e: Exception) {
            Log.e(TAG, "loadImageData: ", e)
        }
    }
}