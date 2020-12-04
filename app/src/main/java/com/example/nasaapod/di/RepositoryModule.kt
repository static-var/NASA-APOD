package com.example.nasaapod.di

import android.content.Context
import com.example.nasaapod.data.model.ApodImage
import com.example.nasaapod.data.repository.ApodRepository
import com.squareup.moshi.JsonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideApodRepository(
        @ApplicationContext context: Context,
        jsonAdapter: JsonAdapter<List<ApodImage>>
    ): ApodRepository {
        return ApodRepository(context, jsonAdapter)
    }
}