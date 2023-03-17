package com.effectivem.android.domain.usecases

import com.effectivem.android.domain.entities.Profile
import com.effectivem.android.domain.repositories.ProfileRepository

class GetProfileUseCaseImpl(private val profileRepository: ProfileRepository): GetProfileUseCase {
    override suspend fun invoke(firstName: String): Profile? = profileRepository.getProfile(firstName)
}