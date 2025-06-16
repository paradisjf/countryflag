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

// Simple view Model for the Home page, where all the countries are shown
@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            try {
                _countries.value = repository.getCountries()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
