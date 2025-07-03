package com.denisf.domain.model

data class RaceHistory(
    val id: Int = 0,
    val date: String,
    val winner: Int?,
    val horsesCount: Int
)
