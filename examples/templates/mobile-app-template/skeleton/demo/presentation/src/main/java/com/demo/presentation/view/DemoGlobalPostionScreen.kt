package com.demo.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.core.presentation.ui.components.container.ContainerComponent
import com.core.presentation.ui.components.cta.button.ButtonComponent
import com.core.presentation.ui.components.cta.button.model.ButtonContentDescription
import com.core.presentation.ui.components.cta.button.model.ButtonPrimary
import com.core.presentation.ui.components.cta.button.model.ButtonSize
import com.core.presentation.ui.theme.LocalDimensions
import com.core.presentation.ui.theme.LocalPalette
import com.core.presentation.ui.theme.LocalTypographies
import com.demo.presentation.viewModel.DemoContract

@Composable
fun DemoGlobalScreen(
    name: String,
    event: (DemoContract.Event) -> Unit
) {
    ContainerComponent(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        bottomBar = {
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                properties = ButtonPrimary(
                    size = ButtonSize.Large,
                    text = "Cerrar sesión",
                    contentDescription = ButtonContentDescription(
                        enabledText = "Cerrar sesión",
                        disabledText = null
                    )
                ),
                enabled = true,
                onClick = {
                    event(DemoContract.Event.OnLogoutClicked)
                }
            )
        }
    ) {
        Text(
            modifier = Modifier
                .padding(top = LocalDimensions.current.dp.dp32)
                .fillMaxWidth(),
            text = "Hola $name, bienvenido",
            style = LocalTypographies.current.high.lEmphasis,
            color = LocalPalette.current.contentPalette.accent,
            textAlign = TextAlign.Center
        )
    }
}