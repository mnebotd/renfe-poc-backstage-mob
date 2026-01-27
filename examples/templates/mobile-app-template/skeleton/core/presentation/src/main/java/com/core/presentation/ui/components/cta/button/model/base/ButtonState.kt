package com.core.presentation.ui.components.cta.button.model.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.core.presentation.ui.utils.shadow.model.base.Shadow

/**
 * Represents the visual state of a ButtonComponent.
 *
 * This data class encapsulates the various style attributes that define the appearance of a button
 * in a specific state (e.g., enabled, disabled, pressed). It allows for fine-grained control
 * over the button's look and feel, including its colors, border, shadow, and text decoration.
 *
 * @property containerColor The background color of the button's container.
 * @property contentColor The color of the content within the button (e.g., text, icons).
 * @property borderStroke The border stroke to be applied around the button's container. If `null`, no border will be drawn.
 * @property shadow The shadow to be cast by the button. If `null`, no shadow will be drawn.
 * @property textDecoration The text decoration to be applied to the button's text. If `null`, no text decoration is applied.
 */
@Immutable
data class ButtonState(
    val containerColor: Color,
    val contentColor: Color,
    val borderStroke: BorderStroke? = null,
    val shadow: Shadow? = null,
    val textDecoration: TextDecoration? = null
)

@Immutable
data class ButtonSelectedState(
    val enabledState: ButtonState,
    val nonEnabledState: ButtonState
)