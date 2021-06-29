package com.natife.testtask2.ui.previewscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.natife.testtask2.R

class PreviewScreenFragment : Fragment() {
//    private lateinit var binding: FragmentS

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview_screen, container, false)
    }

}
