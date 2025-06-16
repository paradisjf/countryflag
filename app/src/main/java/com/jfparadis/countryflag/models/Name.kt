package com.jfparadis.countryflag.models

import com.google.gson.annotations.SerializedName

data class Name(
    // The name is taken from the common field
    @SerializedName("common")
    val common: String
)
