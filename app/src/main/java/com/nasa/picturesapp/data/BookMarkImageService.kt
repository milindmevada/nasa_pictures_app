package com.nasa.picturesapp.data

import java.util.*

interface BookMarkImageService {
    fun isImageBookMarked(date: Date): Boolean
    fun toggleBookMarkStatus(date: Date)
}