package com.core.presentation.ui.utils.interaction

import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.core.presentation.ui.utils.interaction.model.InteractionState
import kotlinx.coroutines.delay

private const val KEEP_PRESSED_STATE_DELAY_MILLIS = 100L

/**
 * A composable function that remembers and provides the current interaction state of a component.
 *
 * This function observes a [MutableInteractionSource] and updates the [InteractionState]
 * based on the interactions emitted by the source (hover, focus, press). It also takes into account the enabled
 * state of the component.
 *
 * @param enabled Whether the component is enabled. If false, the interaction state will always be reset to default
 * (all interaction states false).
 * @param interactionSource The [MutableInteractionSource] to observe for interactions.
 * @return A [State] object holding the current [InteractionState].
 *
 * The [InteractionState] object contains the following properties:
 *  - enabled: Indicates if the component is in the enabled state.
 *  - hovered: Indicates if the component is currently being hovered over.
 *  - pressed: Indicates if the component is currently being pressed.
 *  - focused: Indicates if the component currently has focus.
 *
 * The function use a delay [KEEP_PRESSED_STATE_DELAY_MILLIS] to clear the pressed state
 * for [PressInteraction.Release] and [PressInteraction.Cancel] interactions.
 *
 */
@Composable
fun rememberInteractionState(
    enabled: Boolean,
    interactionSource: MutableInteractionSource,
): State<InteractionState> {
    val interactionState = remember { mutableStateOf(InteractionState()) }
    val interactions = remember { mutableStateListOf<Interaction>() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is HoverInteraction.Enter -> {
                    interactions.add(interaction)
                }
                is HoverInteraction.Exit -> {
                    interactions.remove(interaction.enter)
                }
                is FocusInteraction.Focus -> {
                    interactions.add(interaction)
                }
                is FocusInteraction.Unfocus -> {
                    interactions.remove(interaction.focus)
                }
                is PressInteraction.Press -> {
                    interactions.add(interaction)
                }
                is PressInteraction.Release -> {
                    delay(KEEP_PRESSED_STATE_DELAY_MILLIS)
                    interactions.remove(interaction.press)
                }
                is PressInteraction.Cancel -> {
                    delay(KEEP_PRESSED_STATE_DELAY_MILLIS)
                    interactions.remove(interaction.press)
                }
            }
        }
    }

    if (!enabled) {
        interactionState.value = InteractionState()
    } else {
        val isFocused = interactions.any { it is FocusInteraction.Focus }
        val isPressed = interactions.any { it is PressInteraction.Press }
        val isHovered = interactions.any { it is HoverInteraction.Enter }
        interactionState.value = InteractionState(
            enabled = true,
            hovered = isHovered,
            pressed = isPressed,
            focused = isFocused,
        )
    }

    return interactionState
}