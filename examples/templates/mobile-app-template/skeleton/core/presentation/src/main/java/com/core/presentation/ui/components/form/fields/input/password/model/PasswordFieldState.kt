package com.core.presentation.ui.components.form.fields.input.password.model

import androidx.compose.runtime.Immutable
import com.core.presentation.ui.utils.shadow.model.base.Shadow

/**
 * Represents the state of a password field component.
 *
 * This data class holds the visual and interactive states that can be applied
 * to a password field, specifically the shadow effect. It is designed to be
 * immutable, ensuring that any changes to the state result in a new state object,
 * promoting predictable and efficient recomposition in UI frameworks like Compose.
 *
 * @property shadow The shadow effect applied to the password field.
 *                  If `null`, no shadow is applied. Defaults to `null`.
 */
@Immutable
data class PasswordFieldState(
    val shadow: Shadow? = null
)