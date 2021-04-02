package com.nasa.picturesapp.data

import java.util.*

class InMemoryBookMarkService : BookMarkImageService {

    private val bookMarkedImages = mutableListOf<Date>()

    override fun isImageBookMarked(date: Date): Boolean {
        return bookMarkedImages.contains(date)
    }

    override fun toggleBookMarkStatus(date: Date) {
        if (isImageBookMarked(date)) {
            bookMarkedImages.remove(date)
        } else {
            bookMarkedImages.add(date)
        }
    }
}