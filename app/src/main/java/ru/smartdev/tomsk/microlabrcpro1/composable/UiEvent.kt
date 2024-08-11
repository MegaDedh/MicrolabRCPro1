package ru.smartdev.tomsk.microlabrcpro1.composable

sealed interface UiEvent {
    data object OnOff : UiEvent
    data object Mute : UiEvent
    data object Input : UiEvent
    data object Btn3d : UiEvent
    data object BalanceRight : UiEvent
    data object TrebleInc : UiEvent
    data object BassInc : UiEvent
    data object BalanceLeft : UiEvent
    data object TrebleDec : UiEvent
    data object BassDec : UiEvent
    data object VolumeDec : UiEvent
    data object VolumeInc : UiEvent
}
