package com.example.nasaapod.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nasaapod.data.model.ApodImage
import com.example.nasaapod.data.repository.ApodRepository
import com.example.nasaapod.helpers.CoroutineTestRule
import com.example.nasaapod.helpers.observeForTesting
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ApodViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = CoroutineTestRule()

    private val repository: ApodRepository = mock()

    private val apodImages = listOf(
        ApodImage("t1"),
        ApodImage("t2")
    )

    @Test
    fun `check if apodImages data is equal to date sent in setApodImages`() {
        // Given
        val viewModel = ApodViewModel(repository, rule.dispatcher)

        // When
        viewModel.setApodImages(apodImages)

        // Then
        assertThat(viewModel.apodImages).isNotEmpty()
        assertThat(viewModel.apodImages).isEqualTo(apodImages)
    }

    @Test
    fun `receive data from apodRepository for apodImage`() = runBlockingTest(rule.dispatcher) {
        // Given
        val viewModel = ApodViewModel(repository, rule.dispatcher)
        val data: MutableList<UiState<List<ApodImage>>> = mutableListOf()

        // When
        whenever(repository.getApodList()).thenReturn(flowOf(Success(apodImages)))

        // Then
        val job = launch {
            viewModel.getApodImageList().observeForTesting {
                data.add(it)
            }
        }

        delay(100)
        job.cancelAndJoin()

        assertThat(data).hasSize(2)
        assertThat(data[0]).isInstanceOf(Loading::class.java)
        assertThat(data[1]).isInstanceOf(Success::class.java)
        assertThat((data[1] as Success).data).isEqualTo(apodImages)
    }
}