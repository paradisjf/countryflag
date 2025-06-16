package com.jfparadis.countryflag.models

import com.google.gson.annotations.SerializedName

data class Flags(
    @SerializedName("png")
    val png: String,
)
