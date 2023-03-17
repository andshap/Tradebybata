package com.effectivem.android.domain.usecases

import com.effectivem.android.domain.entities.Profile

interface AddProfileUseCase {
    suspend operator fun invoke(profile: Profile)
}