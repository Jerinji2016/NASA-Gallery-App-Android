package com.jerin.nasagalleryapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.jerin.nasagalleryapp.R
import com.jerin.nasagalleryapp.modal.ImageData

class ImageGridViewAdapter(context: Context, private val size: Int, images: ArrayList<ImageData>) :
    ArrayAdapter<ImageData>(context, 0, images) {

    companion object {
        private const val TAG = "ImageGridViewAdapter"
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.image_card, parent, false)
        }

        val shimmer = Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.7f)
            .setFixedWidth(size)
            .setFixedHeight(size)
            .setHighlightAlpha(0.6f)
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }

        val image = getItem(position)!!
        val imageView = view!!.findViewById<ImageView>(R.id.image_view_small)
        Glide.with(context)
            .load(image.url)
            .placeholder(shimmerDrawable)
            .error(R.drawable.image_error)
            .centerCrop()
            .into(imageView)

        view.layoutParams = LinearLayout.LayoutParams(size, size)

        return view
    }
}