package com.jfparadis.countryflag.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.jfparadis.countryflag.models.Country
import com.jfparadis.countryflag.network.CountryRepository

// Simple view model for the detail screen, where a specific country information is shown
@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    private val _selectedCountry = MutableStateFlow<Country?>(null)
    val selectedCountry: StateFlow<Country?> = _selectedCountry

    fun loadCountryDetails(name: String) {
        viewModelScope.launch {
            try {
                val country = repository.getCountryByName(name)
                _selectedCountry.value = country
            } catch (e: Exception) {
                e.printStackTrace()
                _selectedCountry.value = null // Ensure fallback
            }
        }
    }
}
