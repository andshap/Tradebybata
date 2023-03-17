package com.effectivem.android.domain.di

import com.effectivem.android.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    single<AddProfileUseCase> { AddProfileUseCaseImpl(get()) }
    single<GetProfileUseCase> { GetProfileUseCaseImpl(get()) }
    single<UpdateProfileUseCase> { UpdateProfileUseCaseImpl(get()) }
    single<GetLatestItemsUseCase> { GetLatestItemsUseCaseImpl(get()) }
    single<GetSaleItemsUseCase> { GetSaleItemsUseCaseImpl(get()) }
}