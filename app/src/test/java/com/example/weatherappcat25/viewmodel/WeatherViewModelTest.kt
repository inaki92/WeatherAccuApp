package com.example.weatherappcat25.viewmodel

import com.example.weatherappcat25.location.LastKnownLocationUseCase
import com.example.weatherappcat25.network.WeatherRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

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
        every { mockLocation.location } returns MutableStateFlow(mockk {
            every { postalCode } returns "postalCode"
        })
        testObject.zipCode.observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEqualTo("postalCode")
        }

        // ACTION
        testObject.checkLastKnownLocation()

    }
}