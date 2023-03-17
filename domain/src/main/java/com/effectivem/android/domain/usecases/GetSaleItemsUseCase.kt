package com.effectivem.android.domain.usecases

import com.effectivem.android.domain.entities.SaleItem


interface GetSaleItemsUseCase {
    suspend operator fun invoke(): List<SaleItem>
}