package com.core.presentation.ui.utils.interaction.utils

import androidx.compose.runtime.Stable
import com.core.presentation.ui.utils.interaction.model.IInteractionStateProperties
import com.core.presentation.ui.utils.interaction.model.InteractionState

/**
 * Determines the appropriate state value based on the current [InteractionState] and whether the component is enabled.
 *
 * This function maps the current interaction state (focused, hovered, pressed) to a corresponding state value of type [T].
 * If the component is disabled, it returns the disabled state value.
 *
 * @param enabled `true` if the component is enabled, `false` otherwise.
 * @param properties An object implementing [IInteractionStateProperties] that provides the state values for different interaction states.
 * @return The state value corresponding to the current interaction state if the component is enabled, or the disabled state value if it's disabled.
 *
 * @see IInteractionStateProperties
 * @see InteractionState
 */
@Stable
internal fun <T> InteractionState.state(
    enabled: Boolean,
    properties: IInteractionStateProperties<T>
): T {
    return when {
        focused -> {
            properties.focus
        }

        hovered -> {
            properties.hover
        }

        pressed -> {
            properties.press
        }

        else -> {
            if (enabled) {
                properties.default
            } else {
                properties.disabled
            }
        }
    }
}