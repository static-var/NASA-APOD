package com.example.nasaapod.data

import android.content.Context
import com.example.nasaapod.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApodService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getApodImageList() = context.resources.openRawResource(R.raw.apod).use { inputStream ->
        inputStream.bufferedReader().readText()
    }
}