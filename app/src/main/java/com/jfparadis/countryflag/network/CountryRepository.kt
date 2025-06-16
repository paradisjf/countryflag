package com.jfparadis.countryflag.network

import javax.inject.Inject
import com.jfparadis.countryflag.models.Country


class CountryRepository @Inject constructor(
    private val api: CountriesApi
) {
    suspend fun getCountries(): List<Country> {
        return try {
            api.getAllCountries()
        } catch (e: Exception) {
            e.printStackTrace()
            listOf() // empty list
        }
    }

    suspend fun getCountryByName(name: String): Country? {
        return try {
            val result = api.getCountryByName(name)
            result.firstOrNull()  // Only return the first match
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}
