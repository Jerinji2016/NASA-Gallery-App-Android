package com.jerin.nasagalleryapp.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.jerin.nasagalleryapp.R
import com.jerin.nasagalleryapp.modal.ImageData
import com.jerin.nasagalleryapp.providers.DataProvider
import com.jerin.nasagalleryapp.ui.adapters.PageViewAdapter
import com.jerin.nasagalleryapp.ui.bottom_modal_sheet.BottomModalSheet

class ImageDetailsFragment(private val index: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.image_details_fragment, container, false)

        view.findViewById<ViewPager2>(R.id.image_details_pager).apply {
            val pageViewAdapter = PageViewAdapter(this@ImageDetailsFragment)
            Handler().postDelayed({
                setCurrentItem(index, false)
            }, 100)
            setCurrentItem(index, false)
            adapter = pageViewAdapter
        }

        view.findViewById<MaterialButton>(R.id.image_details_back_button).apply {
            setOnClickListener {
                activity?.finish()
            }
        }

        return view
    }

    open class ImageDetailsHolderFragment(private val index: Int) : Fragment() {
        private val image = DataProvider.getInstance().images.elementAt(index)

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.image_details_holder_fragment, container, false)

            initUI(view, image)

            val showMoreButton = view.findViewById<MaterialButton>(R.id.show_more_button)
            showMoreButton.setOnClickListener {
                val bottomModalSheet = BottomModalSheet(index)
                bottomModalSheet.show(childFragmentManager, "BottomModalSheet")
            }
            return view
        }

        private fun initUI(view: View, image: ImageData) {
            view.apply {
                val title = findViewById<TextView>(R.id.image_detail_title)
                title.text = image.title

                val date = findViewById<TextView>(R.id.image_detail_date)
                date.text = image.formattedDate

                val imageView = findViewById<ImageView>(R.id.image_detail_view)
                Glide.with(requireContext())
                    .load(image.url)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .into(imageView)

            }
        }
    }
}