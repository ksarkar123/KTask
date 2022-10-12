package com.extraaedge.rocketapp.roomDB

import androidx.room.RoomDatabase
import com.extraaedge.rocketapp.roomDB.RocketDao

abstract class AppDatabase: RoomDatabase() {
    abstract fun rocketDao(): RocketDao
}