package com.effectivem.android.data.api

import com.effectivem.android.data.entities.LatestItemDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatestResponse(
    @Json(name = "latest") val latestItems: List<LatestItemDto>
)