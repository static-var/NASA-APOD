package com.example.nasaapod.di

import com.example.nasaapod.data.model.ApodImage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoshiModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApodImageListAdapter(moshi: Moshi): JsonAdapter<List<ApodImage>> {
        val typeOfApodImageList = Types.newParameterizedType(List::class.java, ApodImage::class.java)
        return moshi.adapter<List<ApodImage>>(typeOfApodImageList)
    }
}