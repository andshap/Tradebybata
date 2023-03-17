package com.effectivem.android.domain.repositories

import com.effectivem.android.domain.entities.Profile

interface ProfileRepository {
    suspend fun addProfile(profile: Profile)

    suspend fun getProfile(firstName: String): Profile?

    suspend fun updateProfile(profile: Profile)
}