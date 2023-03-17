package com.effectivem.android.domain.usecases

import com.effectivem.android.domain.entities.LatestItem


interface GetLatestItemsUseCase {
    suspend operator fun invoke(): List<LatestItem>
}