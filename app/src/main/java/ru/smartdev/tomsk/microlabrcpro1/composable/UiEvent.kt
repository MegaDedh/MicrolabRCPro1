package ru.smartdev.tomsk.microlabrcpro1.composable

sealed interface UiEvent {
    sealed interface RcEvent:UiEvent {
        data object OnOff : RcEvent
        data object Mute : RcEvent
        data object Input : RcEvent
        data object Btn3d : RcEvent
        data object BalanceRight : RcEvent
        data object TrebleInc : RcEvent
        data object BassInc : RcEvent
        data object BalanceLeft : RcEvent
        data object TrebleDec : RcEvent
        data object BassDec : RcEvent
        data object VolumeDec : RcEvent
        data object VolumeInc : RcEvent
    }

    data object SnackBarWasShown : UiEvent

}
