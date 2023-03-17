package com.effectivem.android.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.effectivem.android.data.entities.ProfileDto

@Dao
interface ProfileDao {
    @Insert
    suspend fun addProfile(profileDto: ProfileDto)

    @Query("SELECT * FROM profile WHERE first_name=(:firstName)")
    suspend fun getProfile(firstName: String): ProfileDto

    @Update
    suspend fun updateProfile(profileDto: ProfileDto)
}