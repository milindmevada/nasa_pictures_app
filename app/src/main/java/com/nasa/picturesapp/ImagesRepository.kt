package com.nasa.picturesapp

import com.nasa.picturesapp.data.BookMarkImageService
import com.nasa.picturesapp.data.ImagesService
import com.nasa.picturesapp.models.ImageModel
import com.nasa.picturesapp.utils.Result

class ImagesRepository(
    private val imagesService: ImagesService,
    private val bookMarkImageService: BookMarkImageService
) {
    suspend fun getImages(): Result<List<ImageModel>> {
        return when (val imagesResult = imagesService.getImages()) {
            is Result.Error -> imagesResult
            is Result.Success -> {
                val imagesWithBookMarkStatus = imagesResult.data.map {
                    it.copy(isBookMarked = bookMarkImageService.isImageBookMarked(it.date))
                }
                Result.Success(imagesWithBookMarkStatus)
            }
        }
    }
}