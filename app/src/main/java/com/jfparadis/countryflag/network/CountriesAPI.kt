package com.jfparadis.countryflag.network

import retrofit2.http.GET
import retrofit2.http.Path
import com.jfparadis.countryflag.models.Country

// This is the main API interface that would include the call for the 2 screens shown
// One other way to do the same thing, would be to include all the information needed upfront
// in the getAllCountries() call, but this would download too much information, and most of this information
// would not be needed
interface CountriesApi {
    @GET("all?fields=name,flags")
    suspend fun getAllCountries(): List<Country>

    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): List<Country>
}