package com.demo.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.core.presentation.ui.components.container.ContainerComponent
import com.core.presentation.ui.components.cta.button.ButtonComponent
import com.core.presentation.ui.components.cta.button.model.ButtonContentDescription
import com.core.presentation.ui.components.cta.button.model.ButtonPrimary
import com.core.presentation.ui.components.cta.button.model.ButtonSecondary
import com.core.presentation.ui.components.cta.button.model.ButtonSize
import com.core.presentation.ui.components.form.FormState
import com.core.presentation.ui.components.form.fields.input.password.PasswordFieldComponent
import com.core.presentation.ui.components.form.fields.input.text.TextFieldComponent
import com.core.presentation.ui.theme.LocalDimensions
import com.core.presentation.ui.theme.LocalPalette
import com.core.presentation.ui.theme.LocalTypographies
import com.demo.presentation.viewModel.DemoContract

@Composable
fun DemoLoginScreen(
    state: DemoContract.UiState,
    event: (DemoContract.Event) -> Unit
) {
    val formState = remember {
        FormState(
            fields = listOf(
                state.user,
                state.password
            )
        )
    }
    ContainerComponent(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = LocalDimensions.current.dp.dp16,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(
                text = "Inicio de sesión",
                style = LocalTypographies.current.high.mEmphasis,
                color = LocalPalette.current.contentPalette.mid,
                textAlign = TextAlign.Center
            )

            TextFieldComponent(
                modifier = Modifier.fillMaxWidth(),
                properties = state.user,
                enabled = true
            )

            PasswordFieldComponent(
                modifier = Modifier.fillMaxWidth(),
                properties = state.password,
                enabled = true
            )

            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                properties = ButtonPrimary(
                    size = ButtonSize.Large,
                    text = "Acceder",
                    contentDescription = ButtonContentDescription(
                        enabledText = "Acceder",
                        disabledText = "Acceder, completa los campos obligatorios"
                    )
                ),
                enabled = formState.validate(),
                onClick = {
                    event(DemoContract.Event.OnAccessClicked)
                }
            )
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                properties = ButtonSecondary(
                    size = ButtonSize.Large,
                    text = "Resgistrarse",
                    contentDescription = ButtonContentDescription(
                        enabledText = "Resgistrarse",
                        disabledText = null
                    )
                ),
                enabled = true,
                onClick = {
                    event(DemoContract.Event.OnRegisterClicked)
                }
            )
        }
    }
}