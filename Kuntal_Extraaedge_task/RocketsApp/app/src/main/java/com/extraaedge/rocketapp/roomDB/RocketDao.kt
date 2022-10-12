package com.extraaedge.rocketapp.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.extraaedge.rocketapp.models.RocketDetails

@Dao
interface RocketDao {

    @Query("SELECT * FROM rocketEntity")
    fun getAll(): LiveData<List<RocketEntity>>

    @Query("SELECT * FROM rocketEntity WHERE rocket_id IN (:rocketId)")
    fun loadAllById(rocketId: String): LiveData<RocketDetails>

    @Insert
    fun insertAll(vararg users: RocketEntity)

}