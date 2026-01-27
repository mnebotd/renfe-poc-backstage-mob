package com.demo.presentation.viewModel

import com.core.presentation.base.viewmodel.BaseViewModel
import com.core.presentation.ui.components.form.fields.input.password.model.PasswordFieldProperties
import com.core.presentation.ui.components.form.fields.input.text.model.TextFieldProperties
import com.core.presentation.ui.components.form.validators.Validators
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor() : BaseViewModel<
        DemoContract.UiState,
        DemoContract.Event,
        DemoContract.Effect
        >() {
    override fun setInitialState() = DemoContract.UiState(
        user = TextFieldProperties(
            initial = null,
            validators = listOf(
                Validators.Required(message = null)
            ),
            label = "Usuario",
            helperText = null,
            contentDescription = null
        ),
        password = PasswordFieldProperties(
            validators = listOf(
                Validators.Required(message = null)
            ),
            label = "Contraseña",
            helperText = null,
            contentDescription = null
        )
    )

    override fun handleUiEvents(event: DemoContract.Event) {
        when (event) {
            DemoContract.Event.OnAccessClicked -> {
                setEffect { DemoContract.Effect.GoGlobalPosition  }
            }
            DemoContract.Event.OnRegisterClicked -> {
                setEffect { DemoContract.Effect.ShowFeatureNotAvailable  }
            }

            DemoContract.Event.OnLogoutClicked -> {
                setEffect { DemoContract.Effect.Logout  }
            }
        }
    }

    override fun loadInitData() {
    }
}
