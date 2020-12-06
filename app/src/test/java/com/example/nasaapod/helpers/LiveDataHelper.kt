package com.example.nasaapod.helpers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Observes a [LiveData] and calls `block` every time whenever data is changed
 */
fun <T> LiveData<T>.observeForTesting(block: (T) -> Unit) {
    val observer = Observer<T> {
        block(it)
    }
    try {
        observeForever(observer)
    } finally {
        removeObserver(observer)
    }
}