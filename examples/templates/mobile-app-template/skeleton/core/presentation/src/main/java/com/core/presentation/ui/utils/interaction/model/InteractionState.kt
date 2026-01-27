package com.core.presentation.ui.utils.interaction.model

import androidx.compose.runtime.Immutable

/**
 * Represents the interaction state of a UI element, such as a button or text field.
 *
 * This class encapsulates the various states an interactive element can be in,
 * such as whether it's enabled, hovered over by the mouse, pressed, or has focus.
 * These states are typically used to adjust the element's appearance and behavior
 * to provide feedback to the user.
 *
 * @property enabled Indicates whether the element is currently enabled for interaction.
 *                   If `false`, the element is typically grayed out and does not
 *                   respond to user input. Defaults to `false`.
 * @property hovered Indicates whether the mouse cursor is currently hovering over the element.
 *                   Typically used to highlight the element visually. Defaults to `false`.
 * @property pressed Indicates whether the element is currently being pressed (e.g., by a
 *                  mouse click or a touch). Often used to create a visual "pressed" effect.
 *                  Defaults to `false`.
 * @property focused Indicates whether the element currently has input focus. This is
 *                  typically used to visually highlight the element when it is ready
 *                  to receive text input or other commands. Defaults to `false`.
 */
@Immutable
data class InteractionState(
    val enabled: Boolean = false,
    val hovered: Boolean = false,
    val pressed: Boolean = false,
    val focused: Boolean = false
)