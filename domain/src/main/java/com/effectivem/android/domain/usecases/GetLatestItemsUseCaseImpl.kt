package com.effectivem.android.domain.usecases

import com.effectivem.android.domain.entities.LatestItem
import com.effectivem.android.domain.repositories.ItemsRepository

class GetLatestItemsUseCaseImpl(private val itemsRepository: ItemsRepository): GetLatestItemsUseCase {
    override suspend fun invoke(): List<LatestItem> = itemsRepository.getLatestItems()
}