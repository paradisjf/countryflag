package com.jfparadis.countryapp.viewmodel

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.jfparadis.countryflag.models.Country
import com.jfparadis.countryflag.models.Flags
import com.jfparadis.countryflag.models.Name
import com.jfparadis.countryflag.network.CountryRepository
import com.jfparadis.countryflag.viewModel.CountryDetailViewModel

// Some unit tests to make sure the Country Detail View Model is behaving correctly in the normal and error cases
@OptIn(ExperimentalCoroutinesApi::class)
class CountryDetailViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val mockRepository = mockk<CountryRepository>()

    private lateinit var viewModel: CountryDetailViewModel

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CountryDetailViewModel(mockRepository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadCountryDetails should update state with country data on success`() = runTest {
        // Given
        val mockCountry = Country(
            name = Name("Canada"),
            flags = Flags("https://flagcdn.com/ca.png"),
            region = "Americas",
            population = 41987664,
            capital = listOf("Ottawa")
        )

        coEvery { mockRepository.getCountryByName("Canada") } returns mockCountry

        // When
        viewModel.loadCountryDetails("Canada")
        advanceUntilIdle()

        // Then
        assertThat(viewModel.selectedCountry.value).isEqualTo(mockCountry)
        assertThat(viewModel.selectedCountry.value?.population ?: 0).isEqualTo(41987664)
    }

    @Test
    fun `loadCountryDetails should set null when repository returns null`() = runTest {
        // Given
        coEvery { mockRepository.getCountryByName("NowhereLand") } returns null

        // When
        viewModel.loadCountryDetails("NowhereLand")
        advanceUntilIdle()

        // Then
        assertThat(viewModel.selectedCountry.value).isNull()
    }

    @Test
    fun `loadCountryDetails should set null when repository throws exception`() = runTest {
        // Given
        coEvery { mockRepository.getCountryByName("Canada") } throws RuntimeException("Network error")

        // When
        viewModel.loadCountryDetails("Canada")
        advanceUntilIdle()

        // Then
        assertThat(viewModel.selectedCountry.value).isNull()
    }
}
