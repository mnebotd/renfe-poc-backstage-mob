package com.core.presentation.ui.utils.shadow.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpOffset
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.shadow.model.base.BaseShadow
import com.core.presentation.ui.utils.shadow.model.base.Shadow

/**
 * Represents a "floating" shadow effect applied to a shape.
 *
 * This shadow effect is created by combining multiple shadows with different offsets and blur
 * radii, giving the visual appearance that the shape is slightly elevated or floating above
 * the surface. It uses a combination of horizontal and vertical shadows to achieve this effect.
 *
 * The shadow is composed of the following layers:
 *
 * 1. **Right Shadow:** A shadow extending to the right of the shape.
 *    - Color: `backgroundPalette.secondary`
 *    - Blur: `Dimensions().dp.dp20`
 *    - Offset: `(Dimensions().dp.dp8, 0.dp)`
 *    - Spread: `0.dp`
 * 2. **Left Shadow:** A shadow extending to the left of the shape.
 *    - Color: `backgroundPalette.secondary`
 *    - Blur: `Dimensions().dp.dp20`
 *    - Offset: `(-Dimensions().dp.dp8, 0.dp)`
 *    - Spread: `0.dp`
 * 3. **Bottom Shadow:** A shadow extending below the shape.
 *    - Color: `backgroundPalette.secondary`
 *    - Blur: `Dimensions().dp.dp20`
 *    - Offset: `(0.dp, Dimensions().dp.dp8)`
 *    - Spread: `0.dp`
 * 4. **Top Shadow:** A shadow extending above the shape.
 *    - Color: `backgroundPalette.secondary`
 *    - Blur: `Dimensions().dp.dp20`
 *    - Offset: `(0.dp, -Dimensions().dp.dp8)`
 *    - Spread: `0.dp`
 * 5. **Bottom Highlight Shadow:** A subtle shadow below the shape to enhance the floating effect.
 *    - Color: `backgroundPalette.alwaysDark` with 20% opacity
 *    - Blur: `Dimensions().dp.dp10`
 *    - Offset: `(0.dp, Dimensions().dp.dp6)`
 */
@Immutable
data class ShadowFloat(
    override val shape: Shape
) : Shadow(
    shape = shape,
    shadows = listOf(
        BaseShadow(
            color = ColorsPalette().backgroundPalette.secondary,
            blur = Dimensions().dp.dp20,
            offset = DpOffset(
                x = Dimensions().dp.dp8,
                y = Dimensions().dp.dp0
            ),
            spread = Dimensions().dp.dp0
        ),
        BaseShadow(
            color = ColorsPalette().backgroundPalette.secondary,
            blur = Dimensions().dp.dp20,
            offset = DpOffset(
                x = -Dimensions().dp.dp8,
                y = Dimensions().dp.dp0
            ),
            spread = Dimensions().dp.dp0
        ),
        BaseShadow(
            color = ColorsPalette().backgroundPalette.secondary,
            blur = Dimensions().dp.dp20,
            offset = DpOffset(
                x = Dimensions().dp.dp0,
                y = Dimensions().dp.dp8
            ),
            spread = Dimensions().dp.dp0
        ),
        BaseShadow(
            color = ColorsPalette().backgroundPalette.secondary,
            blur = Dimensions().dp.dp20,
            offset = DpOffset(
                x = Dimensions().dp.dp0,
                y = -Dimensions().dp.dp8
            ),
            spread = Dimensions().dp.dp0
        ),
        BaseShadow(
            color = ColorsPalette().backgroundPalette.alwaysDark.copy(
                alpha = 0.20F
            ),
            blur = Dimensions().dp.dp10,
            offset = DpOffset(
                x = Dimensions().dp.dp0,
                y = Dimensions().dp.dp6
            ),
            spread = Dimensions().dp.dp0
        )
    )
)