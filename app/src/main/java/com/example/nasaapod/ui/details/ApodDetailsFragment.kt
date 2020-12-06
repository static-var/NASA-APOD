package com.example.nasaapod.ui.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.example.nasaapod.R
import com.example.nasaapod.databinding.FragmentApodDetailsBinding
import com.example.nasaapod.ui.ApodViewModel
import com.example.nasaapod.utils.autoCleared
import com.example.nasaapod.utils.viewBinding
import com.google.android.material.transition.MaterialContainerTransform


class ApodDetailsFragment : Fragment(R.layout.fragment_apod_details) {
    
    private val viewModel by activityViewModels<ApodViewModel>()
    private val viewBinding by viewBinding(FragmentApodDetailsBinding::bind)
    private val args by navArgs<ApodDetailsFragmentArgs>()
    private var apodDetailAdapter by autoCleared<ApodDetailAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
            drawingViewId = R.id.fragment_container
            duration = resources.getInteger(R.integer.motion_duration_long).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(viewBinding.root, getString(R.string.image_open_transition))
        apodDetailAdapter = ApodDetailAdapter(viewModel.apodImages)
        viewBinding.viewPager.orientation = ORIENTATION_HORIZONTAL
        viewBinding.viewPager.adapter = apodDetailAdapter
        viewBinding.viewPager.setCurrentItem(args.position, false)
    }
}