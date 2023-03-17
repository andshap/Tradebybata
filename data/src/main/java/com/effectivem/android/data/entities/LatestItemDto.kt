package com.effectivem.android.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatestItemDto(
        val category: String,
        val name: String,
        val price: String,
        @Json(name = "image_url") val url: String
)