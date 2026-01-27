package com.core.presentation.ui.components.cta.icon.model

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Immutable
import com.core.presentation.ui.components.cta.icon.model.base.IconButtonState
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette

/**
 * Represents a secondary icon button.
 *
 * This class defines the visual properties for a secondary-style icon button, including its
 * size, content description, icon, and different states (default, focus, hover, press, disabled).
 * Secondary icon buttons have a distinct style compared to primary ones, typically featuring a
 * border and different color schemes for various interaction states.
 *
 * @property size The size of the icon button, defined by [IconButtonSize].
 * @property contentDescription A textual description of the button's action for accessibility purposes.
 * @property icon The resource ID of the icon to be displayed in the button.
 *
 * @constructor Creates a new instance of IconButtonSecondary.
 *
 * @see IconButtonProperties
 * @see IconButtonSize
 * @see IconButtonState
 * @see ColorsPalette
 * @see Dimensions
 */
@Immutable
data class IconButtonSecondary(
    override val size: IconButtonSize,
    override val contentDescription: IconButtonContentDescription,
    @DrawableRes override val icon: Int
) : IconButtonProperties(
    size = size,
    contentDescription = contentDescription,
    icon = icon
) {
    override val default: IconButtonState
        get() = IconButtonState(
            border = BorderStroke(
                width = Dimensions().dp.dp1,
                color = ColorsPalette().borderPalette.accent
            ),
            containerColor = ColorsPalette().backgroundPalette.alwaysLight,
            contentColor = ColorsPalette().contentPalette.accent
        )

    override val focus: IconButtonState
        get() = IconButtonState(
            border = BorderStroke(
                width = Dimensions().dp.dp3,
                color = ColorsPalette().backgroundPalette.alwaysLight
            ),
            containerColor = ColorsPalette().backgroundPalette.alwaysLight,
            contentColor = ColorsPalette().contentPalette.accent
        )

    override val hover: IconButtonState
        get() = IconButtonState(
            border = null,
            containerColor = ColorsPalette().backgroundPalette.contrastLow,
            contentColor = ColorsPalette().contentPalette.alwaysLight
        )

    override val press: IconButtonState
        get() = IconButtonState(
            border = null,
            containerColor = ColorsPalette().backgroundPalette.contrast,
            contentColor = ColorsPalette().contentPalette.alwaysLight
        )

    override val disabled: IconButtonState
        get() = IconButtonState(
            border = null,
            containerColor = ColorsPalette().backgroundPalette.stateDisabled,
            contentColor = ColorsPalette().contentPalette.stateDisabled
        )
}