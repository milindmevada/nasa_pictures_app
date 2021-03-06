package com.nasa.picturesapp.data

import android.content.Context
import com.nasa.picturesapp.models.ImageModel
import com.nasa.picturesapp.utils.FileUtils
import com.nasa.picturesapp.utils.Result
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import java.text.SimpleDateFormat
import java.util.*

class AssetImageServiceTest {

    private val mockContext = Mockito.mock(Context::class.java)
    private val mockFileUtils = Mockito.mock(FileUtils::class.java)
    private val assetImageService = AssetImageService(mockContext, mockFileUtils)

    @Test
    fun `If File Content has valid JSON then Success Result should be returned`() =
        runBlocking<Unit> {
            Mockito.`when`(mockFileUtils.loadDataFromAsset(mockContext, "data.json"))
                .thenReturn(imageModelJson)
            val result = assetImageService.getImages()
            assert(result is Result.Success)
        }

    @Test
    fun `If File Content has valid JSON then Success Result should be returned with Valid ImageModel`() =
        runBlocking<Unit> {
            Mockito.`when`(mockFileUtils.loadDataFromAsset(mockContext, "data.json"))
                .thenReturn(imageModelJson)
            val result = assetImageService.getImages()
            if (result is Result.Success) {
                assert(result.data[0] == stubImageModel)
            }
        }

    @Test
    fun `Error Result Returned if file contents doesn't have valid JSON`() = runBlocking<Unit> {
        Mockito.`when`(mockFileUtils.loadDataFromAsset(mockContext, "data.json")).thenReturn("787")
        val result = assetImageService.getImages()
        assert(result is Result.Error)
    }

    companion object {
        val imageModelJson: String = """ 
            [{
    "copyright": "ESA/HubbleNASA",
    "date": "2019-12-01",
    "explanation": "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
    "hdurl": "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
    "media_type": "image",
    "service_version": "v1",
    "title": "Starburst Galaxy M94 from Hubble",
    "url": "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
  }]
        """.trimIndent()

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