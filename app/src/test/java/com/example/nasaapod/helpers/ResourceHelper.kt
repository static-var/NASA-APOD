package com.example.nasaapod.helpers

import com.example.nasaapod.data.repository.ApodRepositoryTest
import java.io.File

fun getJson(clazz: Any, fileName: String): String {
    val uri = clazz::class.java.classLoader?.getResource(fileName)
        ?: error("Unable to parse JSON")
    val file = File(uri.path)
    return String(file.readBytes())
}