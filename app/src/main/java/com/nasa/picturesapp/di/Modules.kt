package com.nasa.picturesapp.di

import com.nasa.picturesapp.ImagesRepository
import com.nasa.picturesapp.data.AssetImageService
import com.nasa.picturesapp.data.BookMarkImageService
import com.nasa.picturesapp.data.ImagesService
import com.nasa.picturesapp.data.InMemoryBookMarkService
import com.nasa.picturesapp.ui.ImageListingViewModel
import com.nasa.picturesapp.utils.FileUtils
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    //Application Level dependencies goes here
    single {
        FileUtils()
    }
}

val serviceModules = module {
    //Service Layer dependencies goes here
    single<ImagesService> {
        AssetImageService(get(), get())
    }
    single<BookMarkImageService> {
        InMemoryBookMarkService()
    }
}

val repositoryModules = module {
    //Repositories dependencies goes here
    single {
        ImagesRepository(get(), get())
    }
}

val viewModelModules = module {
    //ViewModels dependencies goes here
    viewModel {
        ImageListingViewModel(get())
    }
}