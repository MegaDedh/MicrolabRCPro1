package ru.smartdev.tomsk.microlabrcpro1.utils

internal sealed class Commands(
    val address: UByte,
    val command: UByte,
) {
    data object OnOff : Commands(0x00u, 0x00u)
    data object Mute : Commands(0x00u, 2u)
    data object Input : Commands(0x00u, 4u)
    data object Mode3D : Commands(0x00u, 6u)
    data object BalanceRight : Commands(0x00u, 8u)
    data object TrebleInc : Commands(0x00u, 9u)
    data object BassInc : Commands(0x00u, 10u)
    data object BalanceLeft : Commands(0x00u, 12u)
    data object TrebleDec : Commands(0x00u, 13u)
    data object BassDec : Commands(0x00u, 14u)
    data object VolumeDec : Commands(0x00u, 16u)
    data object VolumeInc : Commands(0x00u, 17u)
}
