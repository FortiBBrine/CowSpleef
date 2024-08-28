package me.fortibrine.cowspleef.api

data class Player(
    val username: String,
    var wins: Int = 0,
    var losses: Int = 0
)
