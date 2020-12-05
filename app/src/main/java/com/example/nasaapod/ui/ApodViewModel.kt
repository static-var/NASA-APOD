package com.example.nasaapod.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.nasaapod.data.model.ApodImage
import com.example.nasaapod.data.repository.ApodRepository
import com.example.nasaapod.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher

class ApodViewModel @ViewModelInject constructor(
    private val apodRepository: ApodRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    /**
     * This will hold a saved copy of list of [ApodImage] fetched from repository layer.
     */
    var apodImages: List<ApodImage> = listOf()
        private set

    fun setApodImages(data: List<ApodImage>) {
        apodImages = data
    }

    /**
     * Function calls [ApodRepository.getApodList] and converts its to a LiveData
     * Reason why [SafeFlow] was converted to [LiveData]
     * [reason](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
     *
     * @return LiveData of UiState which holds either List of ApodImage or an error message
     */
    fun getApodImageList() = liveData {
        emit(Loading())
        emitSource(apodRepository.getApodList().asLiveData(ioDispatcher))
    }

}