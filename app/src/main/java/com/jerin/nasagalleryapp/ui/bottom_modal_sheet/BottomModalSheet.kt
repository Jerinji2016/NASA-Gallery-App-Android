package com.jerin.nasagalleryapp.ui.bottom_modal_sheet

import android.graphics.Color
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jerin.nasagalleryapp.R
import com.jerin.nasagalleryapp.providers.DataProvider
import org.w3c.dom.Text

class BottomModalSheet(private val index: Int) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_layout, container, false).apply {
            setBackgroundColor(Color.TRANSPARENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                background = ContextCompat.getDrawable(context, R.drawable.bottom_sheet_bg)
            }
            setBackgroundResource(R.drawable.bottom_sheet_bg)
        }

        val image = DataProvider.getInstance().images.elementAt(index)

        val title = view.findViewById<TextView>(R.id.image_title)
        title.text = image.title

        val explanation = view.findViewById<TextView>(R.id.image_explanation)
        explanation.text = image.explanation

        val date = view.findViewById<TextView>(R.id.image_date_text)
        date.text = image.formattedDate

        //  copyright can be null in the image data.. so let's hide the row
        if(image.copyright != null) {
            val copyright = view.findViewById<TextView>(R.id.image_copyright_text)
            copyright.text = image.copyright
        } else {
            val copyRightRow = view.findViewById<LinearLayout>(R.id.image_copyright)
            copyRightRow.visibility = GONE
        }

        return view
    }
}