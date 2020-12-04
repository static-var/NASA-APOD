package com.example.nasaapod.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nasaapod.R
import com.example.nasaapod.databinding.FragmentHomeBinding
import com.example.nasaapod.ui.ApodViewModel
import com.example.nasaapod.utils.viewBinding

/**
 * Usage - show a scrollable grid of pictures starting with the latest images first.
 * When the user taps on an image that should open the image detail screen.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {
    val viewModel by activityViewModels<ApodViewModel>()

    val viewBinding by viewBinding(FragmentHomeBinding::bind)
}