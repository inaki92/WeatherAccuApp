package com.example.weatherappcat25.network

import com.example.weatherappcat25.domain.DomainZipCode
import com.example.weatherappcat25.utils.ResponseState
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeoutException

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {

    private val mockApiService: WeatherApi = mockk(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var testObject: WeatherRepository

    @Before
    fun startup() {
        Dispatchers.setMain(testDispatcher)
        testObject = WeatherRepositoryImpl(mockApiService)
    }

    @After
    fun shutdown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `get location key by the zipcode when trying to get location key returns loading state`() {
        //ASSIGN
        val zipCode = "30339"

        val mJob = testScope.launch(testDispatcher) {
            testObject.getLocationKeyByZipCode(zipCode).collect {
                assertThat(it).isInstanceOf(ResponseState.LOADING::class.java)
            }
        }

        mJob.cancel()
    }

    @Test
    fun `get location key by the zipcode when body response is null returns error state`() {
        //ASSIGN
        val zipCode = "30339"
        coEvery { mockApiService.getLocationByZipCode(zipCode) } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns null
        }
        val states = mutableListOf<ResponseState>()

        // ACTION
        val mJob = testScope.launch(testDispatcher) {
            testObject.getLocationKeyByZipCode(zipCode).collect {
                states.add(it)
            }
        }

        //ASSERTIONS
        assertThat(states[0]).isInstanceOf(ResponseState.LOADING::class.java)
        assertThat(states[1]).isInstanceOf(ResponseState.ERROR::class.java)
        assertThat(
            (states[1] as ResponseState.ERROR)
                .error.localizedMessage).isEqualTo("RESPONSE IS NULL")

        mJob.cancel()
    }

    @Test
    fun `get location key by the zipcode when response is failing returns error state`() {
        //ASSIGN
        val zipCode = "30339"
        coEvery { mockApiService.getLocationByZipCode(zipCode) } returns mockk {
            every { isSuccessful } returns false
            every { errorBody() } returns mockk {
                every { string() } returns "ERROR"
            }
        }
        val states = mutableListOf<ResponseState>()

        // ACTION
        val mJob = testScope.launch(testDispatcher) {
            testObject.getLocationKeyByZipCode(zipCode).collect {
                states.add(it)
            }
        }

        //ASSERTIONS
        assertThat(states[0]).isInstanceOf(ResponseState.LOADING::class.java)
        assertThat(states[1]).isInstanceOf(ResponseState.ERROR::class.java)
        assertThat(
            (states[1] as ResponseState.ERROR)
                .error.localizedMessage).isEqualTo("ERROR")

        mJob.cancel()
    }

    @Test
    fun `get location key by the zipcode when any exception happen returns error state`() {
        //ASSIGN
        val zipCode = "30339"
        coEvery { mockApiService.getLocationByZipCode(zipCode) } throws TimeoutException("ERROR")
        val states = mutableListOf<ResponseState>()

        // ACTION
        val mJob = testScope.launch(testDispatcher) {
            testObject.getLocationKeyByZipCode(zipCode).collect {
                states.add(it)
            }
        }

        //ASSERTIONS
        assertThat(states[0]).isInstanceOf(ResponseState.LOADING::class.java)
        assertThat(states[1]).isInstanceOf(ResponseState.ERROR::class.java)
        assertThat(
            (states[1] as ResponseState.ERROR)
                .error.localizedMessage).isEqualTo("ERROR")

        mJob.cancel()
    }

    @Test
    fun `get location key by the zipcode when location key retrieved returns success state`() {
        //ASSIGN
        val zipCode = "30339"
        coEvery { mockApiService.getLocationByZipCode(zipCode) } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns listOf(
                mockk(relaxed = true),
                mockk(relaxed = true)
            )
        }

        val states = mutableListOf<ResponseState>()

        // ACTION
        val mJob = testScope.launch(testDispatcher) {
            testObject.getLocationKeyByZipCode(zipCode).collect {
                states.add(it)
            }
        }

        //ASSERTIONS
        assertThat(states[0]).isInstanceOf(ResponseState.LOADING::class.java)
        assertThat(states[1]).isInstanceOf(ResponseState.SUCCESS::class.java)
        assertThat(
            (states[1] as ResponseState.SUCCESS<List<DomainZipCode>>)
                .response).hasSize(2)

        coVerify(exactly = 1) { mockApiService.getLocationByZipCode(zipCode) }

        mJob.cancel()
    }
}