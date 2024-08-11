package ru.smartdev.tomsk.microlabrcpro1

import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.smartdev.tomsk.microlabrcpro1.composable.RootScreen
import ru.smartdev.tomsk.microlabrcpro1.composable.UiEvent
import ru.smartdev.tomsk.microlabrcpro1.model.MainScreenState
import ru.smartdev.tomsk.microlabrcpro1.ui.theme.MicrolabRCPro1Theme
import ru.smartdev.tomsk.microlabrcpro1.utils.Commands
import ru.smartdev.tomsk.microlabrcpro1.utils.IrCommand
import ru.smartdev.tomsk.microlabrcpro1.utils.NecProtocolConfigBuilder

class MainActivity : ComponentActivity() {
    private val necConfig = NecProtocolConfigBuilder().build()
    private val irCommand = IrCommand(necConfig)
    private val irManager: ConsumerIrManager by lazy {
        getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
    }
    private val _state = MutableStateFlow(MainScreenState())
    private val state = _state.asStateFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIrManager()
        drawUi()
    }

    private fun drawUi() {
        enableEdgeToEdge()
        setContent {
            val mainScreenState: MainScreenState by state.collectAsState(MainScreenState())

            MicrolabRCPro1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RootScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = mainScreenState,
                        onEvent = ::handleUiEvent,
                    )
                }
            }
        }
    }

    private fun handleUiEvent(uiEvent: UiEvent) {
        val command = when (uiEvent) {
            UiEvent.OnOff -> Commands.OnOff
            UiEvent.Btn3d -> Commands.Mode3D
            UiEvent.BalanceLeft -> Commands.BalanceLeft
            UiEvent.BalanceRight -> Commands.BalanceRight
            UiEvent.BassDec -> Commands.BassDec
            UiEvent.BassInc -> Commands.BassInc
            UiEvent.Input -> Commands.Input
            UiEvent.Mute -> Commands.Mute
            UiEvent.TrebleDec -> Commands.TrebleDec
            UiEvent.TrebleInc -> Commands.TrebleInc
            UiEvent.VolumeDec -> Commands.VolumeDec
            UiEvent.VolumeInc -> Commands.VolumeInc
        }
        transmit(command)
    }

    private fun transmit(command: Commands) {
        val pattern = irCommand.getPattern(command.address, command.command)
        irManager.transmit(CARRIER_FREQ_38000, pattern)
    }

    private fun initIrManager() {
        if (irManager.hasIrEmitter()) {
            Log.d(APP_TAG, "IR Emitter find on the device")
            _state.update { it.copy(hasIrEmitter = true) }
            val carrierFrequencies = irManager.carrierFrequencies
            Log.d(APP_TAG, "Support Freqs: $carrierFrequencies")
        } else {
            _state.update { it.copy(hasIrEmitter = false) }
            Log.e(APP_TAG, "Cannot found IR Emitter on the device")
        }
    }

    companion object {
        const val CARRIER_FREQ_38000 = 38000
        const val APP_TAG = "Microlab RC Pro1"
    }
}
