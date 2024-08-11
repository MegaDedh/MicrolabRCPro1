package ru.smartdev.tomsk.microlabrcpro1.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.smartdev.tomsk.microlabrcpro1.R
import ru.smartdev.tomsk.microlabrcpro1.ui.theme.MicrolabRCPro1Theme

@Composable
fun AppSnackBar(text: String) {
    Snackbar(
        modifier = Modifier
            .height(60.dp),
        shape = RoundedCornerShape((Dimens.RoundCornerRadius)),
        containerColor = MicrolabRCPro1Theme.colors.buttonBackground
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = MicrolabRCPro1Theme.colors.buttonTextRed,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SnackBarPreview() {
    AppSnackBar(stringResource((R.string.ir_transmitter_not_found)))
}
