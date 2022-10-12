package com.extraaedge.rocketapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extraaedge.rocketapp.models.RocketDetails
import com.extraaedge.rocketapp.models.Rockets
import com.extraaedge.rocketapp.repository.RecketsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RocketViewModel : ViewModel() {

    lateinit var rockRepo: RecketsRepo

    companion object {
        var progressbar = MutableLiveData<Boolean>()
        var rocketListData = MutableLiveData<ArrayList<Rockets>>()
        var rocketDetailsData = MutableLiveData<RocketDetails>()
    }

    fun getRocketList(){
        rockRepo = RecketsRepo.getInstance()

        viewModelScope.launch(Dispatchers.IO)
        {
            rockRepo.getRockets()
        }
    }

    fun getRocktDtls(id: String)
    {
        rockRepo = RecketsRepo.getInstance()

        viewModelScope.launch(Dispatchers.IO)
        {
            rockRepo.getRocketDetails(id)
        }
    }



}