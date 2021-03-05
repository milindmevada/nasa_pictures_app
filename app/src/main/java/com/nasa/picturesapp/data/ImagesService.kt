package com.nasa.picturesapp.data

import com.nasa.picturesapp.models.ImageModel
import com.nasa.picturesapp.utils.Result

interface ImagesService {
    suspend fun getImages(): Result<List<ImageModel>>
}