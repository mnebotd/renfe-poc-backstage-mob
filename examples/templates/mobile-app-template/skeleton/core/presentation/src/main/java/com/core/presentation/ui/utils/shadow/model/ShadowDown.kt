package com.core.presentation.ui.utils.shadow.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpOffset
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.shadow.model.base.BaseShadow
import com.core.presentation.ui.utils.shadow.model.base.Shadow

/**
 * Represents a set of shadows that are cast downwards from a shape.
 *
 * This sealed class defines different sizes of downward shadows: [Small], [Medium], and [Large].
 * Each size corresponds to a different visual effect achieved by varying the blur, offset, and
 * color alpha of the shadows. These shadows are typically used to create a sense of depth
 * and hierarchy in a UI.
 *
 * @property shape The shape to which the shadows are applied.
 * @property shadows A list of [BaseShadow] objects that define the properties of each shadow.
 */
@Immutable
sealed class ShadowDown(
    override val shape: Shape,
    override val shadows: List<BaseShadow>
): Shadow(
    shape = shape,
    shadows = shadows
) {

    /**
     * Represents a small shadow configuration for a UI element.
     *
     * This class defines a set of shadows designed to create a subtle,
     * "lifted" effect, suitable for smaller UI elements like cards or buttons.
     * It inherits from [ShadowDown], providing a structured way to define
     * multiple shadows with varying properties.
     *
     * The small shadow configuration consists of two distinct shadows:
     *
     * 1. **Outer Shadow:**
     *    - Color: A slightly transparent shade of `backgroundPalette.accent` (with 4% opacity).
     *    - Blur: 8dp, creating a soft, diffused edge.
     *    - Offset: 4dp downwards, simulating a light source from above.
     *    - Spread: 0dp, maintaining the shadow's original size.
     *
     * 2. **Inner Shadow:**
     *    - Color: `backgroundPalette.tertiary`, a solid, light gray.
     *    - Blur: 1dp, creating a very subtle edge definition.
     *    - Offset: 0dp in both x and y directions, ensuring the shadow is centered.
     *    - Spread: 0dp, maintaining the shadow's original size.
     *
     * @param shape The shape of the UI element to which the shadow will be applied.
     * It defines the outline and boundary of the shadow effect.
     * @see ShadowDown
     * @see BaseShadow
     * @see Shape
     * @see ColorsPalette
     * @see Dimensions
     */
    data class Small(
        override val shape: Shape
    ) : ShadowDown(
        shape = shape,
        shadows = listOf(
            BaseShadow(
                color = ColorsPalette().backgroundPalette.accent.copy(
                    alpha = 0.04F
                ),
                blur = Dimensions().dp.dp8,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = Dimensions().dp.dp4
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
     * Represents a medium shadow style for UI elements.
     *
     * This class defines a pre-configured shadow effect with two layers:
     * - A soft, blurred shadow with a slight offset, using a translucent accent color.
     * - A subtle, sharper shadow with no offset, using a neutral color.
     *
     * The "Medium" shadow is intended to provide a moderate level of elevation and depth
     * to UI components, creating a noticeable yet not overly dramatic visual effect.
     *
     * @param shape The shape of the element to which this shadow will be applied.
     * It defines the outline or geometry of the element that the shadow will follow.
     */
    data class Medium(
        override val shape: Shape
    ) : ShadowDown(
        shape = shape,
        shadows = listOf(
            BaseShadow(
                color = ColorsPalette().backgroundPalette.accent.copy(
                    alpha = 0.08F
                ),
                blur = Dimensions().dp.dp16,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = Dimensions().dp.dp8
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
     * Represents a large shadow effect applied downwards.
     *
     * This class defines a specific type of downward shadow effect, characterized by a
     * significant blur and offset, along with a subtle inner edge highlight. It is
     * typically used to create a pronounced "floating" or elevated appearance for
     * UI elements.
     *
     * The large shadow effect consists of two layers:
     *
     * 1. **Outer Shadow:** A diffused shadow with a `dp24` blur and a `dp12` downward
     *    offset. This layer creates the primary sense of depth and separation from
     *    the background. The color is derived from `backgroundPalette.accent`
     *    with an opacity of `0.12F`.
     *
     * 2. **Inner Highlight:** A subtle highlight using `backgroundPalette.tertiary`
     *    with a `dp1` blur and no offset. This layer adds a delicate bright edge
     *    around the shape, further emphasizing the elevation.
     *
     * @property shape The shape to which the shadow effect will be applied.
     */
    data class Large(
        override val shape: Shape
    ) : ShadowDown(
        shape = shape,
        shadows = listOf(
            BaseShadow(
                color = ColorsPalette().backgroundPalette.accent.copy(
                    alpha = 0.12F
                ),
                blur = Dimensions().dp.dp24,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = Dimensions().dp.dp12
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