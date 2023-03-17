package com.effectivem.android.domain.entities

import android.graphics.Bitmap
import java.util.*

data class Profile(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val photoFileBitmap: Bitmap? = null,
    val balance: Int
)