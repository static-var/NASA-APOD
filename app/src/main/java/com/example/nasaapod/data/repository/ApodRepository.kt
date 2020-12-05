package com.example.nasaapod.data.repository

import android.content.Context
import com.example.nasaapod.R
import com.example.nasaapod.data.model.ApodImage
import com.example.nasaapod.ui.Error
import com.example.nasaapod.ui.Success
import com.example.nasaapod.ui.UiState
import com.squareup.moshi.JsonAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class ApodRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apodImageListAdapter: JsonAdapter<List<ApodImage>>
) {

    /**
     * Flow which read data from the raw file, convert it into a [List] of [ApodImage]
     * and emit result in [Success], if in case any error occurs in the process, it emits [Error]
     *
     * Warning suppressed as the Flow will be running on a dispatcher provided by [ApodViewModel.getApodImageList]
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    fun getApodList() = flow<UiState<List<ApodImage>>> {
        context.resources.openRawResource(R.raw.apod).use { inputStream ->
            apodImageListAdapter.fromJson(inputStream.bufferedReader().readText())
                ?.sortedByDescending { LocalDate.parse(it.date, DateTimeFormatter.ISO_DATE) }
        }?.let {
            emit(Success(it))
        } ?: emit(Error("Unable to parse JSON"))
    }
}