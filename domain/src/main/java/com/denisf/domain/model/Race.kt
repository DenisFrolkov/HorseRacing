package com.denisf.domain.model

data class Race(
    val horses: List<Horse>,
    val trackLength: Int,
    val isRunning: Boolean = false,
    val isFinished: Boolean = false,
    val startTime: Long? = null,
    val endTime: Long? = null,
    val winnerId: Int? = null
)