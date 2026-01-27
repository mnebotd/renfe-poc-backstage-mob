package com.core.presentation.ui.components.cta.icon.model.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Represents the visual state.
 *
 * This data class encapsulates the styling properties of an icon button,
 * including its border, container color, and content color. It is immutable, ensuring
 * that changes to these properties result in a new state object, which is essential
 * for efficient recomposition in Jetpack Compose.
 *
 * @property border The border to be drawn around the icon button. If null, no border is drawn.
 *                  Defaults to null.
 * @property containerColor The background color of the icon button's container.
 *                         This color fills the entire area behind the icon.
 * @property contentColor The color of the icon displayed within the button.
 *                       This is the color applied to the vector drawable or other
 *                       content inside the button.
 */
@Immutable
data class IconButtonState(
    val border: BorderStroke?,
    val containerColor: Color,
    val contentColor: Color,
)