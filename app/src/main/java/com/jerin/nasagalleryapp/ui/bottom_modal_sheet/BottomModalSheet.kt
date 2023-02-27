package com.jerin.nasagalleryapp.ui.bottom_modal_sheet

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jerin.nasagalleryapp.R

class BottomModalSheet(private val index: Int) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false).apply {
            setBackgroundColor(Color.TRANSPARENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background = ContextCompat.getDrawable(context, R.drawable.bottom_sheet_bg)
            }
            setBackgroundResource(R.drawable.bottom_sheet_bg)
        }
    }
}