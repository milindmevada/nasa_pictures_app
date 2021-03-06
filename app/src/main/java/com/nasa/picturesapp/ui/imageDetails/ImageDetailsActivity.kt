package com.nasa.picturesapp.ui.imageDetails

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.nasa.picturesapp.databinding.ActivityImageDetailsBinding
import com.nasa.picturesapp.ui.ImageListingViewModel
import com.nasa.picturesapp.utils.PageState
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageDetailsBinding
    private val imagesListingViewModel: ImageListingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenToPageStateChanges()
    }

    private fun listenToPageStateChanges() {
        imagesListingViewModel.pageState.observe(this) {
            when (it) {
                PageState.Data -> {
                    imagesListingViewModel.images.observe(this) {
                        binding.imagesViewPager.adapter = VPImageDetailsAdapter(it)
                        binding.imagesViewPager.setCurrentItem(
                            intent.getIntExtra(
                                keyInitialIndex,
                                0
                            ), false
                        )
                        binding.imagesViewPager.visibility = View.VISIBLE
                    }
                }
                else -> {
                }
            }
        }
    }

    companion object {
        private const val keyInitialIndex = "initial_index"
        fun start(activity: Activity, initialIndex: Int) {
            val intent = Intent(activity, ImageDetailsActivity::class.java)
            intent.putExtra(keyInitialIndex, initialIndex)
            activity.startActivity(intent)
        }
    }
}