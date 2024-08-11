package ru.smartdev.tomsk.microlabrcpro1.utils

data class NecProtocolConfig(
    val PREAMBLE_0: Int,
    val PREAMBLE_1: Int,
    val INTERVAL_0: Int,
    val INTERVAL_1: Int,
    val IMPULSE: Int,
    val LAST_IMPULSE: Int,
    val PAUSE_0: Int = INTERVAL_0 - IMPULSE,
    val PAUSE_1: Int = INTERVAL_1 - IMPULSE,
    val LOGICAL_0: List<Int> = listOf(IMPULSE, PAUSE_0),
    val LOGICAL_1: List<Int> = listOf(IMPULSE, PAUSE_1),
    val PREAMBLE: List<Int> = listOf(PREAMBLE_1, PREAMBLE_0)
)
