package com.jerin.nasagalleryapp.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.jerin.nasagalleryapp.R
import com.jerin.nasagalleryapp.modal.ImageData


class RecyclerViewAdapter(
    private val context: Context,
    private val size: Int,
    private val images: ArrayList<ImageData>
) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.image_card, parent, false)
        return RecyclerViewHolder(view, size)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val image: ImageData = images[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class RecyclerViewHolder(itemView: View, size: Int) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView

        init {
            image = itemView.findViewById(R.id.image_view_small)
            image.layoutParams.height = size
        }

        fun bind(image: ImageData) {
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

            Glide.with(context)
                .load(image.url)
                .placeholder(shimmerDrawable)
                .error(R.drawable.image_error)
                .centerCrop()
                .into(this.image)

            itemView.setOnClickListener {
                Log.d("TAG", "bind: clicked ${image.title}")
            }
        }
    }
}