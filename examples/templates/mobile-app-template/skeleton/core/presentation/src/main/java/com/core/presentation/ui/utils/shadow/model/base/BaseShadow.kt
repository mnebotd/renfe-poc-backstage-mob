package com.core.presentation.ui.utils.shadow.model.base

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset

/**
 * Represents the base properties of a shadow effect that can be applied to a composable.
 *
 * This class encapsulates the essential attributes required to define a shadow's appearance:
 * its color, blur radius, offset from the composable, and the spread distance.
 *
 * @property color The color of the shadow.
 * @property blur The blur radius of the shadow. A higher value results in a softer, more diffuse shadow.
 *   A value of [Dp.Zero] results in a sharp, non-blurred shadow.
 * @property offset The offset of the shadow relative to the composable. This determines the direction and distance
 *   the shadow is shifted. Positive values on the x-axis move the shadow to the right, and positive values on the
 *   y-axis move it downwards.
 * @property spread The spread distance of the shadow. This determines how much the shadow expands beyond the
 *   bounds of the composable. A value of [Dp.Zero] means the shadow starts directly at the edge. Positive values will expand the shadow, while negative ones will contract the shadow.
 *
 * @Immutable indicates that instances of this class are immutable and thread-safe.
 */
@Immutable
data class BaseShadow(
    val color: Color,
    val blur: Dp,
    val offset: DpOffset,
    val spread: Dp
)