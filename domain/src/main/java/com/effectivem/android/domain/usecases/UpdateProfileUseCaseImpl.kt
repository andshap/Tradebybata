package com.effectivem.android.domain.usecases

import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.domain.repositories.ProfileRepository

class UpdateProfileUseCaseImpl(private val profileRepository: ProfileRepository): UpdateProfileUseCase {
    override suspend fun invoke(profile: Profile) = profileRepository.updateProfile(profile)
}