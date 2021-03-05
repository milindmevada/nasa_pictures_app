package com.nasa.picturesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nasa.picturesapp.databinding.ActivityImageListingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageListingActivity : AppCompatActivity() {
    private val imagesListingViewModel: ImageListingViewModel by viewModel()
    private lateinit var binding: ActivityImageListingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imagesListingViewModel.images.observe(this) {
            binding.tvMain.text = it.size.toString()
        }
        imagesListingViewModel.error.observe(this) {
            it?.let {
                binding.tvMain.text = it
            }
        }
    }
}