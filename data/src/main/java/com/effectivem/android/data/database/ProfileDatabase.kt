package com.effectivem.android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.effectivem.android.data.entities.ProfileDto

@Database(entities = [ ProfileDto::class ], version = 1, exportSchema = false)
@TypeConverters(ProfileTypesConverters::class)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}