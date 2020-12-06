package com.example.nasaapod.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewGroupCompat
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaapod.R
import com.example.nasaapod.databinding.FragmentHomeBinding
import com.example.nasaapod.ui.ApodViewModel
import com.example.nasaapod.ui.Error
import com.example.nasaapod.ui.Loading
import com.example.nasaapod.ui.Success
import com.example.nasaapod.utils.autoCleared
import com.example.nasaapod.utils.viewBinding
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough

/**
 * Usage - show a scrollable grid of pictures starting with the latest images first.
 * When the user taps on an image that should open in [ApodDetailsFragment] and should show details
 */
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel by activityViewModels<ApodViewModel>()

    private val viewBinding by viewBinding(FragmentHomeBinding::bind)

    private var apodHomeAdapter by autoCleared<ApodHomeAdapter>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getApodImageList().observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is Loading -> {
                    with(viewBinding) {
                        progressBar.show()
                        apodRecyclerView.isVisible = false
                        errorText.isVisible = false
                    }
                }
                is Error -> {
                    with(viewBinding) {
                        progressBar.hide()
                        apodRecyclerView.isVisible = false
                        errorText.isVisible = true
                        errorText.text = uiState.errorMessage
                    }
                }
                is Success -> {
                    with(viewBinding) {
                        progressBar.hide()
                        apodRecyclerView.isVisible = true
                        errorText.isVisible = false
                    }
                    apodHomeAdapter.updateData(uiState.data)
                    view?.doOnPreDraw { startPostponedEnterTransition() }
                    viewModel.setApodImages(uiState.data)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        ViewGroupCompat.setTransitionGroup(viewBinding.apodRecyclerView, true)

        apodHomeAdapter = ApodHomeAdapter { clickedView, position ->
            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(R.integer.motion_duration_long).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(R.integer.motion_duration_long).toLong()
            }
            val viewTransitionName = getString(R.string.image_open_transition)
            val extras = FragmentNavigatorExtras(clickedView to viewTransitionName)
            findNavController().navigate(HomeFragmentDirections.actionHomeToApodDetails(position), extras)
        }

        apodHomeAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        viewBinding.apodRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)
        viewBinding.apodRecyclerView.adapter = apodHomeAdapter
    }
}