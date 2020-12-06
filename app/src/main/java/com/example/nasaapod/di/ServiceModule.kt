package com.example.nasaapod.di

import android.content.Context
import com.example.nasaapod.data.ApodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideApodService(@ApplicationContext context: Context): ApodService {
        return ApodService(context)
    }
}