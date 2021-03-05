package com.nasa.picturesapp.di

import com.nasa.picturesapp.ImagesRepository
import com.nasa.picturesapp.data.AssetImageService
import com.nasa.picturesapp.data.ImagesService
import com.nasa.picturesapp.ui.ImageListingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    //Application Level dependencies goes here
}

val serviceModules = module {
    //Service Layer dependencies goes here
    single<ImagesService> {
        AssetImageService(get())
    }
}

val repositoryModules = module {
    //Repositories dependencies goes here
    single {
        ImagesRepository(get())
    }
}

val viewModelModules = module {
    //ViewModels dependencies goes here
    viewModel {
        ImageListingViewModel(get())
    }
}