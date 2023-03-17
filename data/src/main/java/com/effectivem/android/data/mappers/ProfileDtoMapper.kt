package com.effectivem.android.data.mappers

import com.effectivem.android.data.entities.ProfileDto
import com.effectivem.android.domain.entities.Profile

class ProfileDtoMapper: Mapper<Profile, ProfileDto> {
    override fun map(input: Profile): ProfileDto =
        ProfileDto(
            input.id,
            input.firstName,
            input.lastName,
            input.email,
            input.password,
            input.photoFileBitmap,
            input.balance,
        )
}