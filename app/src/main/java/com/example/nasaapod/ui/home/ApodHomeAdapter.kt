package com.example.nasaapod.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.nasaapod.R
import com.example.nasaapod.data.model.ApodImage
import com.example.nasaapod.databinding.ItemApodHomeBinding

/**
 * ApodHomeAdapter - Adapter holds [ApodImage] and shows only the [ApodImage.url] as thumbnail
 *
 * @property click
 */
class ApodHomeAdapter(
    private val click: (View, Int) -> Unit
) : RecyclerView.Adapter<ApodHomeAdapter.ViewHolder>() {
    private var apodImages: List<ApodImage> = listOf()

    inner class ViewHolder(private val binding: ItemApodHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apodImage: ApodImage, position: Int) {
            with(binding) {
                imageHolder.load(apodImage.url) {
                    transformations(
                        RoundedCornersTransformation(
                            ResourcesCompat.getFloat(
                                binding.root.context.resources,
                                R.dimen.thumbnail_image_radius
                            )
                        )
                    )

                    crossfade(true)
                }
                ViewCompat.setTransitionName(
                    binding.root,
                    binding.root.context.getString(R.string.image_view_transition, position)
                )
                binding.root.setOnClickListener {
                    click(it, apodImages.indexOf(apodImage))
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemApodHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(apodImages[position], position)
    }

    override fun getItemCount(): Int = apodImages.size

    fun updateData(data: List<ApodImage>) {
        apodImages = data
    }
}