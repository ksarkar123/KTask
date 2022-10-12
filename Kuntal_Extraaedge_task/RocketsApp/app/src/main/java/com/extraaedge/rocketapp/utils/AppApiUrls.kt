package com.extraaedge.rocketapp.utils

class AppApiUrls {

    companion object {
        val BaseUrl: String = "https://api.spacexdata.com/v4"
        fun getRocketsUrl(): String {
            return "$BaseUrl/rockets"
        }

    }

}