package com.nasa.picturesapp

import com.nasa.picturesapp.data.ImagesService
import com.nasa.picturesapp.models.ImageModel
import com.nasa.picturesapp.utils.Result

class ImagesRepository(private val imagesService: ImagesService) {
    suspend fun getImages(): Result<List<ImageModel>> = imagesService.getImages()
}