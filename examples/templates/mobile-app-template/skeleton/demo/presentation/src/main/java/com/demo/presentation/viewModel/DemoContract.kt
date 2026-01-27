package com.demo.presentation.viewModel

import com.core.presentation.base.viewmodel.IBaseContract
import com.core.presentation.ui.components.form.fields.input.password.model.PasswordFieldProperties
import com.core.presentation.ui.components.form.fields.input.text.model.TextFieldProperties

class DemoContract {
    data class UiState(
        val user: TextFieldProperties,
        val password: PasswordFieldProperties
    ) : IBaseContract.ViewState

    sealed class Event : IBaseContract.ViewEvent {
        data object OnAccessClicked : Event()
        data object OnRegisterClicked : Event()
        data object OnLogoutClicked : Event()
    }

    sealed class Effect : IBaseContract.ViewEffect {
        data object GoGlobalPosition : Effect()
        data object ShowFeatureNotAvailable : Effect()
        data object Logout : Effect()
    }
}