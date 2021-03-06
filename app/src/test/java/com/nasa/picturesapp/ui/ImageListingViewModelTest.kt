package com.nasa.picturesapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nasa.picturesapp.ImagesRepository
import com.nasa.picturesapp.models.ImageModel
import com.nasa.picturesapp.utils.PageState
import com.nasa.picturesapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import java.text.SimpleDateFormat
import java.util.*


@ExperimentalCoroutinesApi
class ImageListingViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val mockImagesRepository = Mockito.mock(ImagesRepository::class.java)

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `PageState should Emit InProgress when Image fetch Request is triggered`() = runBlocking {
        Mockito.`when`(mockImagesRepository.getImages())
            .thenReturn(Result.Success(listOf(stubImageModel)))
        val imageListingViewModel = ImageListingViewModel(mockImagesRepository)
        val pageStateObserver = Observer<PageState> {}
        try {
            imageListingViewModel.pageState.observeForever(pageStateObserver)
            val latestPageState = imageListingViewModel.pageState.value
            assert(latestPageState is PageState.InProgress)
            //TODO: Need to find a way to test next value emission
        } finally {
            imageListingViewModel.pageState.removeObserver(pageStateObserver)
        }
    }


    companion object {
        val stubImageModel = ImageModel(
            copyRight = "ESA/HubbleNASA",
            date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2019-12-01"),
            explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
            hdUrl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
            mediaType = "image",
            serviceVersion = "v1",
            title = "Starburst Galaxy M94 from Hubble",
            url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
        )
    }
}