package com.core.presentation.ui.components.form.fields.input.text.model

import androidx.compose.runtime.Immutable
import com.core.presentation.ui.utils.shadow.model.base.Shadow

/**
 * Represents the state of a TextFieldComponent.
 *
 * This data class encapsulates the visual aspects of a TextField, allowing for
 * customization of its appearance. Currently, it supports defining a shadow for
 * the text field.
 *
 * @property shadow An optional [Shadow] object that defines the shadow effect to be
 * applied to the TextField. If null, no shadow is applied.
 */
@Immutable
data class TextFieldState(
    val shadow: Shadow? = null
)