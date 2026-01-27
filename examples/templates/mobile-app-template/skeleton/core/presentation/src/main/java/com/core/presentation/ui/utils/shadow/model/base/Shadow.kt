package com.core.presentation.ui.utils.shadow.model.base

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape

/**
 * Represents a shadow configuration for a composable element.
 *
 * A `Shadow` instance defines the shape of the element and the list of shadows
 * applied to it. Shadows can have different properties like offset, blur radius, and color.
 *
 * This class is designed to be extended to create specific types of shadow configurations.
 *
 * @property shape The shape of the element to which the shadow is applied. This defines
 *                  the outline of the element that will cast the shadow.
 * @property shadows A list of [BaseShadow] objects, each representing a single shadow layer.
 *                   Multiple shadows can be combined to create complex effects.
 *
 */
@Immutable
abstract class Shadow(
    open val shape: Shape,
    open val shadows: List<BaseShadow>
)