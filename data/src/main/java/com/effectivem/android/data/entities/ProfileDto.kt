package com.effectivem.android.data.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "profile")
data class ProfileDto(
    @PrimaryKey val id: UUID,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val email: String,
    val password: String,
    @ColumnInfo(name = "photo_file") val photoFileBitmap: Bitmap? = null,
    val balance: Int
)