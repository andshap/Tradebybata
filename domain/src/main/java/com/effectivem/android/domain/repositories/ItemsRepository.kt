package com.effectivem.android.domain.repositories

import com.effectivem.android.domain.entities.LatestItem
import com.effectivem.android.domain.entities.SaleItem

interface ItemsRepository {
    suspend fun getLatestItems(): List<LatestItem>

    suspend fun getSaleItems(): List<SaleItem>
}