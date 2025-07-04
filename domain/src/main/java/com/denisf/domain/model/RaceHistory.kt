package com.denisf.domain.model

data class RaceHistory(
    val id: Int = 0,
    val date: String,
    val winnerId: Int?,
    val horsesCount: Int
)