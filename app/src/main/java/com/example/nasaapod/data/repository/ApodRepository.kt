package com.example.nasaapod.data.repository

import com.example.nasaapod.data.ApodService
import com.example.nasaapod.data.model.ApodImage
import com.example.nasaapod.ui.Error
import com.example.nasaapod.ui.Success
import com.example.nasaapod.ui.UiState
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.flow.flow
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApodRepository @Inject constructor(
    private val apodService: ApodService,
    private val apodImageListAdapter: JsonAdapter<List<ApodImage>>
) {

    /**
     * Flow which receives data from service, converts it into a [List] of [ApodImage]
     * and emit result in [Success], if in case any error occurs in the process, it emits [Error]
     *
     * Warning suppressed as the Flow will be running on a dispatcher provided by [ApodViewModel.getApodImageList]
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    fun getApodList() = flow<UiState<List<ApodImage>>> {
        apodImageListAdapter.fromJson(apodService.getApodImageList())
            ?.sortedByDescending { LocalDate.parse(it.date, DateTimeFormatter.ISO_DATE) }
            ?.let {
                emit(Success(it))
            } ?: emit(Error("Unable to parse JSON"))
    }
}