package com.effectivem.android.data.mappers

import com.effectivem.android.data.entities.LatestItemDto
import com.effectivem.android.domain.entities.LatestItem

class LatestItemMapper: Mapper<LatestItemDto, LatestItem> {
    override fun map(input: LatestItemDto): LatestItem =
        LatestItem(
            input.category,
            input.name,
            input.price,
            input.url
        )
}