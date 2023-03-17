package com.effectivem.android.data.repositories

import com.effectivem.android.data.api.LatestApi
import com.effectivem.android.data.api.SaleApi
import com.effectivem.android.data.entities.LatestItemDto
import com.effectivem.android.data.entities.SaleItemDto
import com.effectivem.android.data.mappers.Mapper
import com.effectivem.android.domain.entities.LatestItem
import com.effectivem.android.domain.entities.SaleItem
import com.effectivem.android.domain.repositories.ItemsRepository

class ApiRepository(
    private val latestApi: LatestApi,
    private val latestItemMapper: Mapper<LatestItemDto, LatestItem>,
    private val saleApi: SaleApi,
    private val saleItemMapper: Mapper<SaleItemDto, SaleItem>
): ItemsRepository {
    override suspend fun getLatestItems(): List<LatestItem> {
        return latestApi.fetch().latestItems.map { latestItemDto ->
            latestItemMapper.map(latestItemDto)
        }
    }

    override suspend fun getSaleItems(): List<SaleItem> {
        return saleApi.fetch().saleItems.map { saleItemDto ->
            saleItemMapper.map(saleItemDto)
        }
    }
}