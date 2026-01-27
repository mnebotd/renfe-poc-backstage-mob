package com.core.presentation.ui.utils.interaction.model

import androidx.compose.runtime.Immutable

/**
 * Represents a set of properties that can vary depending on the interaction state of a component.
 *
 * This interface defines properties for different interaction states like:
 * - **default**: The property value when the component is in its normal, non-interacted state.
 * - **focus**: The property value when the component has focus (e.g., when navigated to with a keyboard).
 * - **hover**: The property value when the mouse cursor is hovering over the component.
 * - **press**: The property value when the component is being pressed (e.g., mouse button down or finger tap).
 * - **disabled**: The property value when the component is disabled and cannot be interacted with.
 *
 * Each property is of type [T], allowing this interface to be used with various property types like
 * colors, sizes, opacities, etc.
 *
 * @param T The type of the property that changes based on interaction state.
 */
@Immutable
interface IInteractionStateProperties<T> {
    val default: T
    val focus: T
    val hover: T
    val press: T
    val disabled: T
}