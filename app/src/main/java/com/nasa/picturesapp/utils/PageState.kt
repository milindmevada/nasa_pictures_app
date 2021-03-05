package com.nasa.picturesapp.utils

sealed class PageState {
    object Initial : PageState()
    object InProgress : PageState()
    object Data : PageState()
    object EmptyData : PageState()
    object Error : PageState()
}