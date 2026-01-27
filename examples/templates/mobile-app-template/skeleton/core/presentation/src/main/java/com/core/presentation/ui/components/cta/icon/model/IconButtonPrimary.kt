package com.core.presentation.ui.components.cta.icon.model

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Immutable
import com.core.presentation.ui.components.cta.icon.model.base.IconButtonState
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette

/**
 * Represents the properties and state configurations for a primary icon button.
 *
 * This class defines the appearance and behavior of a primary icon button in different states
 * such as default, focus, hover, press, and disabled. It inherits from [IconButtonProperties]
 * and specifies the distinct styles for a primary button.
 *
 * @property size The size of the icon button, defined by [IconButtonSize].
 * @property contentDescription A string describing the icon button for accessibility.
 * @property icon The resource ID of the icon to be displayed on the button.
 */
@Immutable
data class IconButtonPrimary(
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
            containerColor = ColorsPalette().backgroundPalette.accent,
            contentColor = ColorsPalette().contentPalette.alwaysLight
        )

    override val focus: IconButtonState
        get() = IconButtonState(
            border = BorderStroke(
                width = Dimensions().dp.dp3,
                color = ColorsPalette().backgroundPalette.alwaysLight
            ),
            containerColor = ColorsPalette().backgroundPalette.accent,
            contentColor = ColorsPalette().contentPalette.alwaysLight
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