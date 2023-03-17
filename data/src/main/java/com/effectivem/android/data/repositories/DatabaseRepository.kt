package com.effectivem.android.data.repositories

import com.effectivem.android.data.database.ProfileDatabase
import com.effectivem.android.data.entities.ProfileDto
import com.effectivem.android.data.mappers.Mapper
import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.domain.repositories.ProfileRepository

class DatabaseRepository(
    private val database: ProfileDatabase,
    private val profileMapper: Mapper<ProfileDto, Profile>,
    private val profileDtoMapper: Mapper<Profile, ProfileDto>
): ProfileRepository {
    override suspend fun addProfile(profile: Profile) {
        database.profileDao().addProfile(profileDtoMapper.map(profile))
    }

    override suspend fun getProfile(firstName: String): Profile? {
        return database.profileDao().getProfile(firstName)?.let { profileDto ->
            profileMapper.map(profileDto)
        }
    }

    override suspend fun updateProfile(profile: Profile) {
        database.profileDao().updateProfile(profileDtoMapper.map(profile))
    }
}