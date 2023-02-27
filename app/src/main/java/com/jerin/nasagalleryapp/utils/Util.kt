package com.jerin.nasagalleryapp.utils

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.WindowManager
import com.jerin.nasagalleryapp.R
import com.jerin.nasagalleryapp.modal.ImageData
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter

object Util {
    private const val TAG = "Util"

    /**
     *  Fetches all the data for the app to run from [R.raw.data]
     */
    fun loadData(context: Context?): ArrayList<ImageData> {
        try {
            val inputStream = context?.resources?.openRawResource(R.raw.data)
                ?: javaClass.classLoader?.getResourceAsStream("data.json")

            val reader = BufferedReader(InputStreamReader(inputStream, "utf-8"))

            val writer = StringWriter()
            val buffer = CharArray(1024)
            reader.use { it ->
                var n: Int
                while (it.read(buffer).also { n = it } != -1) {
                    writer.write(buffer, 0, n)
                }
            }
            val jsonEncodedData = writer.toString()
            val jsonList = JSONArray(jsonEncodedData)

            val images = ArrayList<ImageData>()
            for (i in 0 until jsonList.length()) {
                val image = ImageData(jsonList.getJSONObject(i))
                images.add(image)
            }
            Log.d(TAG, "loadData: Fetched ${images.size} image data")
            return images
        } catch (e: Exception) {
            Log.e(TAG, "loadData: ", e)
            throw Exception("Failed to load data")
        }
    }

    fun getImageSize(context: Context): Int {
        val windowWidth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.currentWindowMetrics.bounds.right
        } else {
            val displayMetrics = context.resources.displayMetrics
            displayMetrics.widthPixels
        }

        return (windowWidth - 10) / 3
    }
}