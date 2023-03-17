package com.effectivem.android.domain.usecases

import com.effectivem.android.domain.entities.SaleItem
import com.effectivem.android.domain.repositories.ItemsRepository

class GetSaleItemsUseCaseImpl(private val itemsRepository: ItemsRepository): GetSaleItemsUseCase {
    override suspend fun invoke(): List<SaleItem> = itemsRepository.getSaleItems()
}