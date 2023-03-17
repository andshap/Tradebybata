package com.effectivem.android.data.mappers

import com.effectivem.android.data.entities.ProfileDto
import com.effectivem.android.domain.entities.Profile

class ProfileMapper: Mapper<ProfileDto, Profile> {
    override fun map(input: ProfileDto): Profile =
        Profile(
            input.id,
            input.firstName,
            input.lastName,
            input.password,
            input.email,
            input.photoFileBitmap,
            input.balance
        )

}