package com.extraaedge.rocketapp.roomDB

import androidx.lifecycle.LiveData
import com.extraaedge.rocketapp.models.Rockets

class RocketRepository(private val rocketDAO: RocketDao) {

    val getAllRockets: LiveData<List<RocketEntity>> = rocketDAO.getAll()

    suspend fun addRockets(rocketEntity: RocketEntity){
        rocketDAO.insertAll(rocketEntity)
    }

}