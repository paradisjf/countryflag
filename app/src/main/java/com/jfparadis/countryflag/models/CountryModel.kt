package com.jfparadis.countryflag.models

import com.google.gson.annotations.SerializedName

// This is the main model for the Country, including only the fields needed to be used.
//
data class Country(

    // This will be using the common name of the country
    @SerializedName("name")
    val name: Name,

    // This will be using containing the URL to the PNG of the country flag
    @SerializedName("flags")
    val flags: Flags,

    // The region contains the continent where it is.
    // The field "continents" is not being used in this case. The region seems to contain the information needed.
    @SerializedName("region")
    val region: String? = null,

    @SerializedName("population")
    val population: Long? = null,

    @SerializedName("capital")
    val capital: List<String>? = null
)
