package com.effectivem.android.domain.usecases

import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.domain.repositories.ProfileRepository

class AddProfileUseCaseImpl(private val profileRepository: ProfileRepository): AddProfileUseCase {
    override suspend fun invoke(profile: Profile) = profileRepository.addProfile(profile)
}