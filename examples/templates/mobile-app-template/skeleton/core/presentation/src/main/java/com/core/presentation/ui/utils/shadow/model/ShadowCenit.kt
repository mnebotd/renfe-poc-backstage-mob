package com.core.presentation.ui.utils.shadow.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpOffset
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.shadow.model.base.BaseShadow
import com.core.presentation.ui.utils.shadow.model.base.Shadow

/**
 * Represents a shadow configuration that is centered (cenit).
 *
 * This class defines a set of shadows that are applied to a given [shape].
 * The shadows are designed to create a "centered" effect, where the shadow appears
 * to emanate from the center of the shape. It provides different sizes of shadows.
 *
 * @property shape The shape to which the shadows will be applied.
 * @property shadows A list of [BaseShadow] objects defining the shadow effects.
 */
@Immutable
sealed class ShadowCenit(
    override val shape: Shape,
    override val shadows: List<BaseShadow>
): Shadow(
    shape = shape,
    shadows = shadows
) {

    /**
     * Represents a small shadow effect for a given shape.
     *
     * This class provides a predefined set of shadows to create a subtle, small shadow effect
     * that can be applied to UI elements. The effect consists of two layers:
     *  - A soft, light shadow with a slight blur and low opacity.
     *  - A thin, crisp shadow to define the edges.
     *
     * The `Small` shadow preset is designed for UI elements where a minimal shadow
     * is desired to provide depth without being overly prominent.
     *
     * @property shape The shape to which the shadow should conform. This determines the outline
     *                 of the shadow. It is passed directly to the `ShadowCenit` parent class.
     * @see ShadowCenit
     * @see BaseShadow
     * @see Shape
     * @see ColorsPalette
     * @see Dimensions
     */
    data class Small(
        override val shape: Shape
    ) : ShadowCenit(
        shape = shape,
        shadows = listOf(
            BaseShadow(
                color = ColorsPalette().backgroundPalette.accent.copy(
                    alpha = 0.04F
                ),
                blur = Dimensions().dp.dp8,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = Dimensions().dp.dp0
                ),
                spread = Dimensions().dp.dp0
            ),
            BaseShadow(
                color = ColorsPalette().backgroundPalette.tertiary,
                blur = Dimensions().dp.dp1,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = Dimensions().dp.dp0
                ),
                spread = Dimensions().dp.dp0
            )
        )
    )

    /**
     * Medium shadow preset for ShadowCenit.
     *
     * This class defines a medium-level shadow effect, composed of two distinct shadows:
     *
     * 1. **Soft, diffuse shadow:**
     *    - Color: `backgroundPalette.accent` with 8% opacity (alpha = 0.08).
     *    - Blur radius: `dp16` from `Dimensions().dp`.
     *    - Offset: (0, 0) - No displacement from the element's edge.
     *    - Spread: 0 - No expansion beyond the element's boundary.
     *    - This provides a subtle, ambient shadow that gives the element a sense of elevation.
     *
     * 2. **Sharp, inner shadow:**
     *    - Color: `backgroundPalette.tertiary`.
     *    - Blur radius: `dp1` from `Dimensions().dp`.
     *    - Offset: (0, 0) - No displacement.
     *    - Spread: 0 - No expansion.
     *    - This creates a very faint line just inside the shape, adding definition and depth.
     *
     * The combination of these two shadows results in a "medium" shadow effect, suitable for
     * elements that require a noticeable but not overly pronounced lift.
     *
     * @property shape The shape of the element that this shadow will be applied to.
     * @constructor Creates a Medium shadow instance with the specified shape.
     */
    data class Medium(
        override val shape: Shape
    ) : ShadowCenit(
        shape = shape,
        shadows = listOf(
            BaseShadow(
                color = ColorsPalette().backgroundPalette.accent.copy(
                    alpha = 0.08F
                ),
                blur = Dimensions().dp.dp16,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = Dimensions().dp.dp0
                ),
                spread = Dimensions().dp.dp0
            ),
            BaseShadow(
                color = ColorsPalette().backgroundPalette.tertiary,
                blur = Dimensions().dp.dp1,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = Dimensions().dp.dp0
                ),
                spread = Dimensions().dp.dp0
            )
        )
    )

    /**
     * Represents a large shadow style for a UI element.
     *
     * This class defines a set of shadows designed to create a "large" visual effect.
     * It uses two `BaseShadow` objects:
     *
     * 1. A diffuse shadow with `backgroundPalette.accent` color and 12% opacity, 24dp blur, and no offset or spread.
     *    This shadow provides a soft, ambient glow around the element.
     * 2. A subtle shadow with backgroundPalette.tertiary color, 1dp blur, and no offset or spread. This adds a very subtle,
     *    crisp definition to the edges of the element.
     *
     * The combination of these two shadows results in a distinct "large" shadow effect.
     *
     * @property shape The shape of the element to which the shadows will be applied.
     * @constructor Creates a new instance of Large shadow style.
     */
    data class Large(
        override val shape: Shape
    ) : ShadowCenit(
        shape = shape,
        shadows = listOf(
            BaseShadow(
                color = ColorsPalette().backgroundPalette.accent.copy(
                    alpha = 0.12F
                ),
                blur = Dimensions().dp.dp24,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = Dimensions().dp.dp0
                ),
                spread = Dimensions().dp.dp0
            ),
            BaseShadow(
                color = ColorsPalette().backgroundPalette.tertiary,
                blur = Dimensions().dp.dp1,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = Dimensions().dp.dp0
                ),
                spread = Dimensions().dp.dp0
            )
        )
    )
}