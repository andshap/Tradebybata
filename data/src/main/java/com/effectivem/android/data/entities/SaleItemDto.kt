package com.effectivem.android.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SaleItemDto(
    val category: String,
    val name: String,
    val discount: String,
    val price: String,
    @Json(name = "image_url") val url: String
)