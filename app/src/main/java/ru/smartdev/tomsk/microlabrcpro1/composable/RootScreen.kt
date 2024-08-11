package ru.smartdev.tomsk.microlabrcpro1.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.smartdev.tomsk.microlabrcpro1.R
import ru.smartdev.tomsk.microlabrcpro1.model.MainScreenState
import ru.smartdev.tomsk.microlabrcpro1.ui.theme.MicrolabRCPro1Theme


@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
    state: MainScreenState,
    onEvent: (event: UiEvent) -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.hasIrEmitter) {
        if (state.hasIrEmitter == false) {
            launch {
                snackBarHostState.showSnackbar(message = "")
            }
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MicrolabRCPro1Theme.colors.darkGreyBackground)
                .padding(
                    horizontal = 16.dp,
                    vertical = 72.dp,
                ),
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RcButton(
                    textRes = R.string.btn_on_off,
                    RcButtonType.ON_OFF,
                    onClick = { onEvent(UiEvent.OnOff) }
                )
                RcButton(
                    textRes = R.string.btn_input,
                    RcButtonType.NORMAL,
                    onClick = { onEvent(UiEvent.Input) }
                )
                RcButton(
                    textRes = R.string.btn_3d,
                    RcButtonType.NORMAL,
                    onClick = { onEvent(UiEvent.Btn3d) }
                )
            }

            Spacer(modifier = Modifier.height(Dimens.SpaceBetweenRaw))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RcButton(
                    textRes = R.string.btn_treble_inc,
                    RcButtonType.NORMAL,
                    onClick = { onEvent(UiEvent.TrebleInc) }
                )
                RcButton(
                    textRes = R.string.btn_volume_inc,
                    RcButtonType.BIG,
                    onClick = { onEvent(UiEvent.VolumeInc) }
                )
                RcButton(
                    textRes = R.string.btn_bass_inc,
                    RcButtonType.NORMAL,
                    onClick = { onEvent(UiEvent.BassInc) }
                )
            }

            Spacer(modifier = Modifier.height(Dimens.SpaceBetweenRaw))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                RcButton(
                    textRes = R.string.btn_treble_dec,
                    RcButtonType.NORMAL,
                    onClick = { onEvent(UiEvent.TrebleDec) }
                )
                RcButton(
                    textRes = R.string.btn_volume_dec,
                    RcButtonType.BIG,
                    onClick = { onEvent(UiEvent.VolumeDec) }
                )
                RcButton(
                    textRes = R.string.btn_bass_dec,
                    RcButtonType.NORMAL,
                    onClick = { onEvent(UiEvent.BassDec) }
                )
            }

            Spacer(modifier = Modifier.height(Dimens.SpaceBetweenRaw))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RcButton(
                    textRes = R.string.btn_balance_left,
                    RcButtonType.NORMAL,
                    onClick = { onEvent(UiEvent.BalanceLeft) }
                )
                RcButton(
                    textRes = R.string.btn_mute,
                    RcButtonType.NORMAL,
                    onClick = { onEvent(UiEvent.Mute) }
                )
                RcButton(
                    textRes = R.string.btn_balance_right,
                    RcButtonType.NORMAL,
                    onClick = { onEvent(UiEvent.BalanceRight) }
                )
            }
        }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 72.dp,
                ),
        ) {
            AppSnackBar(text = stringResource(R.string.ir_transmitter_not_found))
        }
    }
}

@Composable
private fun RcButton(
    @StringRes textRes: Int,
    buttonType: RcButtonType,
    onClick: () -> Unit
) {
    val size = if (buttonType == RcButtonType.BIG) Dimens.ButtonSizeBig else Dimens.ButtonSizeNormal
    val textColor =
        if (buttonType == RcButtonType.ON_OFF)
            MicrolabRCPro1Theme.colors.buttonTextRed
        else MicrolabRCPro1Theme.colors.buttonTextNormal

    Button(
        modifier = Modifier.size(size),
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = MicrolabRCPro1Theme.colors.buttonBackground
        ),
        shape = RoundedCornerShape(Dimens.RoundCornerRadius),
        onClick = { onClick() }) {
        Text(
            text = stringResource(textRes),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
    }
}

private enum class RcButtonType {
    NORMAL, BIG, ON_OFF
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MicrolabRCPro1Theme {
        RootScreen(
            state = MainScreenState(),
            onEvent = {},
        )
    }
}
