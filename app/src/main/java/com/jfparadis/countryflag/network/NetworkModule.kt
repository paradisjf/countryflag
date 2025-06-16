package com.jfparadis.countryflag.network

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// This is the main networking module using Retrofit.
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Hardcoded here, but this could be made a constant / or modified to use different environment base URL
    // Like: LOCAL, DEBUG, DEV, STAGING, PROD, etc.
    @Provides
    fun provideBaseUrl() = "https://restcountries.com/v3.1/"

    // This is the Retrofit singleton
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // This is the countries API
    @Provides
    @Singleton
    fun provideCountriesApi(retrofit: Retrofit): CountriesApi =
        retrofit.create(CountriesApi::class.java)
}
