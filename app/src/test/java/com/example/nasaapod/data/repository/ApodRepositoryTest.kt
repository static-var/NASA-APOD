package com.example.nasaapod.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nasaapod.data.ApodService
import com.example.nasaapod.data.model.ApodImage
import com.example.nasaapod.di.MoshiModule
import com.example.nasaapod.helpers.CoroutineTestRule
import com.example.nasaapod.helpers.getJson
import com.example.nasaapod.ui.Error
import com.example.nasaapod.ui.Success
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ApodRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = CoroutineTestRule()

    private val jsonAdapter: JsonAdapter<List<ApodImage>> by lazy {
        MoshiModule.provideApodImageListAdapter(MoshiModule.provideMoshi())
    }

    @Test
    fun `test if getApodList emits Success with expected data`() = rule.dispatcher.runBlockingTest {
        // Given
        val mockService = mock<ApodService>()

        // When
        whenever(mockService.getApodImageList()).thenReturn(
            getJson(
                this@ApodRepositoryTest,
                "apod.json"
            )
        )
        val flow = ApodRepository(mockService, jsonAdapter).getApodList()

        // Then
        flow.collect {
            val state = it
            val data = (it as Success).data
            assertThat(state).isInstanceOf(Success::class.java)
            assertThat(data).isNotEmpty()
        }
    }

    @Test
    fun `test if getApodList emits Error`() = rule.dispatcher.runBlockingTest {
        // Given
        val mockService = mock<ApodService>()

        // When
        whenever(mockService.getApodImageList()).thenReturn("null")
        val flow = ApodRepository(mockService, jsonAdapter).getApodList()

        // Then
        flow.collect {
            val state = it
            val data = (it as Error).errorMessage
            assertThat(state).isInstanceOf(Error::class.java)
            assertThat(data).isNotEmpty()
        }
    }
}