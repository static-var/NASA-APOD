package com.example.nasaapod.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.example.nasaapod.R
import com.example.nasaapod.databinding.FragmentApodDetailsBinding
import com.example.nasaapod.ui.ApodViewModel
import com.example.nasaapod.utils.autoCleared
import com.example.nasaapod.utils.viewBinding


class ApodDetailsFragment : Fragment(R.layout.fragment_apod_details) {
    
    private val viewModel by activityViewModels<ApodViewModel>()
    private val viewBinding by viewBinding(FragmentApodDetailsBinding::bind)
    private val args by navArgs<ApodDetailsFragmentArgs>()
    private var apodDetailAdapter by autoCleared<ApodDetailAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apodDetailAdapter = ApodDetailAdapter(viewModel.apodImages)
        viewBinding.viewPager.orientation = ORIENTATION_HORIZONTAL
        viewBinding.viewPager.adapter = apodDetailAdapter
        viewBinding.viewPager.setCurrentItem(args.position, false)
    }
}