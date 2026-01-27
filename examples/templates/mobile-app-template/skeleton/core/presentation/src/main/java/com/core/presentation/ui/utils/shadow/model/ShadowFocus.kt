package com.core.presentation.ui.utils.shadow.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpOffset
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.shadow.model.base.BaseShadow
import com.core.presentation.ui.utils.shadow.model.base.Shadow

/**
 * Represents a focused shadow effect for UI elements.
 *
 * This class defines a specific type of shadow that creates a "focused" or
 * "highlighted" appearance. It uses two distinct shadow layers:
 *
 * 1. **Accent Shadow:** A vibrant shadow using `backgroundPalette.contrast`
 *    with a slightly negative offset and a moderate spread, creating a subtle glow.
 *    - `color`: `backgroundPalette.contrast`
 *    - `blur`: `Dimensions().dp.dp0` (no blur)
 *    - `offset`: `DpOffset(x = -Dimensions().dp.dp2, y = -Dimensions().dp.dp2)` (moves the shadow up and to the left)
 *    - `spread`: `Dimensions().dp.dp4` (extends the shadow outwards)
 *
 * 2. **White Inner Shadow:** A lighter, backgroundColor shadow
 *    with a smaller negative offset and spread, creating an inner highlight effect.
 *    - `color`: backgroundColor
 *    - `blur`: `Dimensions().dp.dp0` (no blur)
 *    - `offset`: `DpOffset(x = -Dimensions().dp.dp1, y = -Dimensions().dp.dp1)` (moves the shadow up and to the left)
 *    - `spread`: `Dimensions().dp.dp2` (extends the shadow outwards)
 *
 * Together, these two shadows create a visual cue indicating that an element is in
 * focus or has been selected.
 *
 * @property shape The shape of the shadow. This determines the overall outline of
 *                  the shadow effect (e.g., rectangle, rounded rectangle, etc.).
 * @param backgroundColor The background color used for the inner shadow layer.
 */
@Immutable
data class ShadowFocus(
    override val shape: Shape,
    val backgroundColor: Color
) : Shadow(
    shape = shape,
    shadows = listOf(
        BaseShadow(
            color = ColorsPalette().backgroundPalette.contrast,
            blur = Dimensions().dp.dp0,
            offset = DpOffset(
                x = -Dimensions().dp.dp2,
                y = -Dimensions().dp.dp2
            ),
            spread = Dimensions().dp.dp4
        ),
        BaseShadow(
            color = backgroundColor,
            blur = Dimensions().dp.dp0,
            offset = DpOffset(
                x = -Dimensions().dp.dp1,
                y = -Dimensions().dp.dp1
            ),
            spread = Dimensions().dp.dp2
        )
    )
)