package com.effectivem.android.data.mappers

import com.effectivem.android.data.entities.SaleItemDto
import com.effectivem.android.domain.entities.SaleItem

class SaleItemMapper: Mapper<SaleItemDto, SaleItem> {
    override fun map(input: SaleItemDto): SaleItem =
        SaleItem(
            input.category,
            input.name,
            input.discount,
            input.price,
            input.url
        )
}