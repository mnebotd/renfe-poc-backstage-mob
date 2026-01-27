package com.core.presentation.ui.components.cta.icon.model

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Immutable
import com.core.presentation.ui.components.cta.icon.model.base.IconButtonState
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette

/**
 * Represents the properties and state definitions for a tertiary icon button.
 *
 * This class defines the appearance of a tertiary icon button, including its colors and border
 * in various states like default, focused, hovered, pressed, and disabled. Tertiary buttons
 * are typically used for less prominent actions.
 *
 * @param size The size of the icon button, defined by [IconButtonSize].
 * @param contentDescription The accessibility description for the icon button.
 * @param icon The resource ID of the icon to display.
 */
@Immutable
data class IconButtonTertiary(
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
            border = null,
            containerColor = ColorsPalette().backgroundPalette.tertiary,
            contentColor = ColorsPalette().contentPalette.accent
        )

    override val focus: IconButtonState
        get() = IconButtonState(
            border = BorderStroke(
                width = Dimensions().dp.dp3,
                color = ColorsPalette().backgroundPalette.alwaysLight
            ),
            containerColor = ColorsPalette().backgroundPalette.tertiary,
            contentColor = ColorsPalette().contentPalette.accent
        )

    override val hover: IconButtonState
        get() = IconButtonState(
            border = null,
            containerColor = ColorsPalette().backgroundPalette.tertiary,
            contentColor = ColorsPalette().contentPalette.accent
        )

    override val press: IconButtonState
        get() = IconButtonState(
            border = null,
            containerColor = ColorsPalette().backgroundPalette.contrast,
            contentColor = ColorsPalette().backgroundPalette.alwaysLight
        )

    override val disabled: IconButtonState
        get() = IconButtonState(
            border = null,
            containerColor = ColorsPalette().backgroundPalette.secondary,
            contentColor = ColorsPalette().contentPalette.stateDisabled
        )
}