package com.effectivem.android.domain.usecases

import com.effectivem.android.domain.entities.Profile

interface GetProfileUseCase {
    suspend operator fun invoke(firstName: String): Profile?
}