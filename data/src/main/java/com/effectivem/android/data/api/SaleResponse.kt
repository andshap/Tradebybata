package com.effectivem.android.data.api

import com.effectivem.android.data.entities.SaleItemDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SaleResponse(
    @Json(name = "flash_sale") val saleItems: List<SaleItemDto>
)