package com.denisf.domain.model

data class FinishedRace(
    val id: Int,
    val numberRace: Int,
    val date: String,
    val winner: Int,
    val horsesCount: Int
)
