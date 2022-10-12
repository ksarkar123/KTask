package com.extraaedge.rocketapp.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rocketEntity")
data class RocketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "rocket_id") val rocket_id: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "enginecount") val engine_count: String?,
    @ColumnInfo(name = "flickrimage") val flickr_image: String?
)
