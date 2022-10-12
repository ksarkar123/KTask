package com.extraaedge.rocketapp.models

data class RocketDetails(
    var id: String,
    var name: String,
    var flicker_images: ArrayList<String>,
    var action_status: String,
    var cost_per_launch: String,
    var success_rate_pct: String,
    var description: String,
    var wikipedia: String,
    var height: String,
    var diameter: String
)
