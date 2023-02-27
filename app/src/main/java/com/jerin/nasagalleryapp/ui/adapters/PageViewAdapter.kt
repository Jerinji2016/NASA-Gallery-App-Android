package com.jerin.nasagalleryapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jerin.nasagalleryapp.providers.DataProvider
import com.jerin.nasagalleryapp.ui.ImageDetailsFragment

class PageViewAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val images = DataProvider.getInstance().images

    override fun getItemCount(): Int {
        return images.size
    }

    override fun createFragment(position: Int): Fragment {
        return ImageDetailsFragment.ImageDetailsHolderFragment(position)
    }
}