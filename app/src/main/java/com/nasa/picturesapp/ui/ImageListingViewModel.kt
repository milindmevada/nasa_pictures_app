package com.nasa.picturesapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasa.picturesapp.ImagesRepository
import com.nasa.picturesapp.models.ImageModel
import com.nasa.picturesapp.utils.Result
import kotlinx.coroutines.launch

class ImageListingViewModel(private val imagesRepository: ImagesRepository) : ViewModel() {
    init {
        fetchImages()
    }

    private val _images = MutableLiveData<List<ImageModel>>(listOf())
    val images: LiveData<List<ImageModel>>
        get() = _images

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?>
        get() = _error

    private fun fetchImages() {
        viewModelScope.launch {
            when (val result = imagesRepository.getImages()) {
                is Result.Error -> _error.postValue(result.exception.message)
                is Result.Success -> result.data.let { _images.postValue(it) }
            }
        }
    }
}