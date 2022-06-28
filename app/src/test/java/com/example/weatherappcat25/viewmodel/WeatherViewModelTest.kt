package com.example.weatherappcat25.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherappcat25.domain.DomainForecast
import com.example.weatherappcat25.domain.DomainZipCode
import com.example.weatherappcat25.location.LastKnownLocationUseCase
import com.example.weatherappcat25.network.WeatherRepository
import com.example.weatherappcat25.utils.ResponseState
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.internal.matchers.Null
import java.util.*
import kotlin.math.max

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    @get:Rule val mainRule = InstantTaskExecutorRule()

    private val testIODispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testIODispatcher)

    private val mockRepository = mockk<WeatherRepository>()
    private val mockLocation = mockk<LastKnownLocationUseCase>()

    private lateinit var testObject: WeatherViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testIODispatcher)
        testObject = WeatherViewModel(mockRepository, testIODispatcher, mockLocation)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `check last known location when locations is retrieved and not null should return an event on live data`() {
        // ASSIGN
        every { mockLocation.getLastKnownLocation() } returns Unit
        every { mockLocation.location } returns MutableStateFlow(mockk {
            every { postalCode } returns "postalCode"
        })
        testObject.zipCode.observeForever {

            // ASSERTIONS
            assertThat(it).isNotNull()
            assertThat(it).isEqualTo("postalCode")
        }

        // ACTION
        testObject.checkLastKnownLocation()
    }

    @Test
    fun `check last known location when locations is retrieved and not null should return null value`() {
        // ASSIGN
        every { mockLocation.getLastKnownLocation() } returns Unit
        every { mockLocation.location } returns MutableStateFlow(null)
        testObject.zipCode.observeForever {
            // ASSERTIONS
            assertThat(it).isNull()
        }

        // ACTION
        testObject.checkLastKnownLocation()
    }

    @Test
    fun `get daily forecast when zipcode is available returns loading state`() {
        // ASSIGN
        testObject.setManualZipCode("30339")
        every { mockRepository.getLocationKeyByZipCode("30339") } returns flowOf(
            ResponseState.LOADING
        )
        val state = mutableListOf<ResponseState>()
        testObject.forecast.observeForever {
            state.add(it)
        }

        //ACTION
        testObject.getDailyForecast()

        // ASSERT
        assertThat(state).hasSize(2)
        assertThat(state[0]).isInstanceOf(ResponseState.LOADING::class.java)
        assertThat(state[1]).isInstanceOf(ResponseState.LOADING::class.java)
    }

    @Test
    fun `get daily forecast when zipcode is available returns success state`() {
        // ASSIGN
        testObject.setManualZipCode("30339")
        every { mockRepository.getLocationKeyByZipCode("30339") } returns flowOf(
            ResponseState.SUCCESS<List<DomainZipCode>>(listOf(mockk {
                every { locationKey } returns "locationKey"
            }))
        )
        every { mockRepository.getDailyForecast("locationKey") } returns flowOf(
            ResponseState.SUCCESS<List<DomainForecast>>(listOf(
                mockk {
                    every { maxTemp } returns "321"
                },
                mockk {
                    every { maxTemp } returns "123"
                }
            ))
        )
        val state = mutableListOf<ResponseState>()
        testObject.forecast.observeForever {
            state.add(it)
        }

        //ACTION
        testObject.getDailyForecast()

        // ASSERT
        assertThat(state).hasSize(2)
        assertThat(state[0]).isInstanceOf(ResponseState.LOADING::class.java)
        assertThat(state[1]).isInstanceOf(ResponseState.SUCCESS::class.java)
        assertThat((state[1] as ResponseState.SUCCESS<List<DomainForecast>>).response).hasSize(2)
        assertThat((state[1] as ResponseState.SUCCESS<List<DomainForecast>>).response[0].maxTemp).isEqualTo("321")
        assertThat((state[1] as ResponseState.SUCCESS<List<DomainForecast>>).response[1].maxTemp).isEqualTo("123")
    }
}