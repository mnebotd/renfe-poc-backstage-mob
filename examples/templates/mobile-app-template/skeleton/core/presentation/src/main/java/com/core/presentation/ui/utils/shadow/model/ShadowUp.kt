package com.core.presentation.ui.utils.shadow.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpOffset
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.shadow.model.base.BaseShadow
import com.core.presentation.ui.utils.shadow.model.base.Shadow

/**
 * Represents a shadow that is cast upwards, simulating a raised or floating effect.
 *
 * This sealed class defines different sizes of "upwards" shadows, each with a unique combination
 * of blur, offset, color and alpha to achieve different visual effects. It inherits from [Shadow].
 *
 * @property shape The shape of the shadow. This defines the area where the shadow is applied.
 * @property shadows The list of [BaseShadow] objects that define the individual shadow layers.
 */
@Immutable
sealed class ShadowUp(
    override val shape: Shape,
    override val shadows: List<BaseShadow>
): Shadow(
    shape = shape,
    shadows = shadows
) {

    /**
     * Represents a small shadow configuration for UI elements.
     *
     * This class defines a specific shadow style characterized by a subtle, upward shadow effect.
     * It is designed for UI elements where a gentle lift or separation from the background is desired.
     *
     * The `Small` shadow is composed of two distinct shadows:
     *
     * 1. **Primary Shadow:**
     *    - Color: A semi-transparent shade of backgroundPalette.accent, with an alpha value of 0.04 (4% opacity).
     *    - Blur: 8.dp, creating a soft, diffused edge.
     *    - Offset: -4.dp vertically (upwards), with no horizontal offset. This is what creates the upward shift.
     *    - Spread: 0.dp, meaning the shadow doesn't expand beyond the bounds of the element.
     *
     * 2. **Secondary Shadow:**
     *    - Color: A lighter neutral shade backgroundPalette.tertiary, fully opaque.
     *    - Blur: 1.dp, providing a very subtle softening.
     *    - Offset: 0.dp in both horizontal and vertical directions.
     *    - Spread: 0.dp, meaning the shadow doesn't expand beyond the bounds of the element.
     *
     * @property shape The shape of the shadow.
     */
    data class Small(
        override val shape: Shape
    ) : ShadowUp(
        shape = shape,
        shadows = listOf(
            BaseShadow(
                color = ColorsPalette().backgroundPalette.accent.copy(
                    alpha = 0.04F
                ),
                blur = Dimensions().dp.dp8,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = -Dimensions().dp.dp4
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
     * Represents a medium shadow configuration for UI elements.
     *
     * This class defines a specific shadow style characterized by a moderate, upward shadow effect.
     * It is designed for UI elements where a noticeable lift or separation from the background is desired.
     *
     * The `Medium` shadow is composed of two distinct shadows:
     *
     * 1. **Primary Shadow:**
     *    - Color: A semi-transparent shade of backgroundPalette.accent, with an alpha value of 0.08 (8% opacity).
     *    - Blur: 16.dp, creating a soft, diffused edge.
     *    - Offset: -8.dp vertically (upwards), with no horizontal offset. This is what creates the upward shift.
     *    - Spread: 0.dp, meaning the shadow doesn't expand beyond the bounds of the element.
     *
     * 2. **Secondary Shadow:**
     *    - Color: A lighter neutral shade backgroundPalette.tertiary, fully opaque.
     *    - Blur: 1.dp, providing a very subtle softening.
     *    - Offset: 0.dp in both horizontal and vertical directions.
     *    - Spread: 0.dp, meaning the shadow doesn't expand beyond the bounds of the element.
     *
     * @property shape The shape of the shadow.
     */
    data class Medium(
        override val shape: Shape
    ) : ShadowUp(
        shape = shape,
        shadows = listOf(
            BaseShadow(
                color = ColorsPalette().backgroundPalette.accent.copy(
                    alpha = 0.08F
                ),
                blur = Dimensions().dp.dp16,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = -Dimensions().dp.dp8
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
     * Represents a large shadow configuration for UI elements.
     *
     * This class defines a specific shadow style characterized by a strong, upward shadow effect.
     * It is designed for UI elements where a significant lift or separation from the background is desired.
     *
     * The `Large` shadow is composed of two distinct shadows:
     *
     * 1. **Primary Shadow:**
     *    - Color: A semi-transparent shade of backgroundPalette.accent, with an alpha value of 0.12 (12% opacity).
     *    - Blur: 24.dp, creating a soft, diffused edge.
     *    - Offset: -12.dp vertically (upwards), with no horizontal offset. This is what creates the upward shift.
     *    - Spread: 0.dp, meaning the shadow doesn't expand beyond the bounds of the element.
     *
     * 2. **Secondary Shadow:**
     *    - Color: A lighter neutral shade backgroundPalette.tertiary, fully opaque.
     *    - Blur: 1.dp, providing a very subtle softening.
     *    - Offset: 0.dp in both horizontal and vertical directions.
     *    - Spread: 0.dp, meaning the shadow doesn't expand beyond the bounds of the element.
     *
     * @property shape The shape of the shadow.
     */
    data class Large(
        override val shape: Shape
    ) : ShadowUp(
        shape = shape,
        shadows = listOf(
            BaseShadow(
                color = ColorsPalette().backgroundPalette.accent.copy(
                    alpha = 0.12F
                ),
                blur = Dimensions().dp.dp24,
                offset = DpOffset(
                    x = Dimensions().dp.dp0,
                    y = -Dimensions().dp.dp12
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