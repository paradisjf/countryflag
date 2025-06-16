package com.example.countryapp.viewmodel

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import com.jfparadis.countryflag.models.Country
import com.jfparadis.countryflag.models.Flags
import com.jfparadis.countryflag.models.Name
import com.jfparadis.countryflag.network.CountryRepository
import com.jfparadis.countryflag.viewModel.CountryViewModel

// Some unit tests to make sure the Country View Model is behaving correctly in the normal and error cases
@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var mockRepository: CountryRepository
    private lateinit var viewModel: CountryViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockRepository = mockk()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should load countries from repository`() = runTest {
        // Given
        val countriesList = listOf(
            Country(Name("France"), Flags("https://flagcdn.com/fr.png")),
            Country(Name("Germany"), Flags("https://flagcdn.com/de.png"))
        )
        coEvery { mockRepository.getCountries() } returns countriesList

        // When
        viewModel = CountryViewModel(mockRepository)
        advanceUntilIdle()

        // Then
        val result = viewModel.countries.first()
        assertThat(result).containsExactlyElementsOf(countriesList)
    }

    @Test
    fun `init should set empty list when repository throws`() = runTest {
        // Given
        coEvery { mockRepository.getCountries() } throws RuntimeException("Server error")

        // When
        viewModel = CountryViewModel(mockRepository)
        advanceUntilIdle()

        // Then
        val result = viewModel.countries.first()
        assertThat(result).isEmpty()
    }
}
