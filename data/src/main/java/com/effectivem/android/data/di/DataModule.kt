package com.effectivem.android.data.di

import android.content.Context
import androidx.room.Room
import com.effectivem.android.data.api.LatestApi
import com.effectivem.android.data.api.SaleApi
import com.effectivem.android.data.database.ProfileDatabase
import com.effectivem.android.data.entities.LatestItemDto
import com.effectivem.android.data.entities.ProfileDto
import com.effectivem.android.data.entities.SaleItemDto
import com.effectivem.android.data.mappers.*
import com.effectivem.android.data.repositories.ApiRepository
import com.effectivem.android.data.repositories.DatabaseRepository
import com.effectivem.android.domain.entities.LatestItem
import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.domain.entities.SaleItem
import com.effectivem.android.domain.repositories.ItemsRepository
import com.effectivem.android.domain.repositories.ProfileRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val DATABASE_NAME = "profile-database"

val dataModule = module {
    single { provideProfileDatabase(androidContext()) }

    single<Mapper<Profile, ProfileDto>>(named("profile_dto_map")) { ProfileDtoMapper() }

    single<Mapper<ProfileDto, Profile>>(named("profile_map")) { ProfileMapper() }

    single<ProfileRepository> { DatabaseRepository(
        get(),
        get(named("profile_map")),
        get(named("profile_dto_map"))
    ) }

    single { provideRetrofit() }

    single { provideLatestApi(get()) }

    single { provideSaleApi(get()) }

    single<Mapper<LatestItemDto, LatestItem>>(named("latest_item_map")) { LatestItemMapper() }

    single<Mapper<SaleItemDto, SaleItem>>(named("sale_item_map")) { SaleItemMapper() }

    single<ItemsRepository> { ApiRepository(
        get(),
        get(named("latest_item_map")),
        get(),
        get(named("sale_item_map"))
    ) }
}

private fun provideProfileDatabase(context: Context): ProfileDatabase = Room.databaseBuilder(
    context,
    ProfileDatabase::class.java,
    DATABASE_NAME
)
    .build()

private fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://run.mocky.io/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

private fun provideLatestApi(retrofit: Retrofit): LatestApi =
    retrofit.create(LatestApi::class.java)

private fun provideSaleApi(retrofit: Retrofit): SaleApi =
    retrofit.create(SaleApi::class.java)