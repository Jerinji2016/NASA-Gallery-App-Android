package com.jerin.nasagalleryapp.providers

import android.content.Context
import android.util.Log
import androidx.annotation.VisibleForTesting
import com.jerin.nasagalleryapp.modal.ImageData
import com.jerin.nasagalleryapp.utils.Util
import org.jetbrains.annotations.TestOnly

//  Singleton class as Global Data Provider
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
        return loadImageDataAbstract(context)
    }

    @VisibleForTesting
    fun loadImagesTest() {
        loadImageDataAbstract()
    }

    private fun loadImageDataAbstract(context: Context? = null) {
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