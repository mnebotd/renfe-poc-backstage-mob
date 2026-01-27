package com.core.presentation.ui.components.cta.icon.model

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.core.presentation.ui.components.cta.icon.model.base.IconButtonState
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette

/**
 * Represents the properties of an icon button link.
 *
 * This data class defines the appearance and behavior of an icon button when used as a link,
 * meaning it primarily serves as a navigation or action trigger without a strong visual
 * emphasis like a filled button.
 *
 * @property size The size of the icon button. See [IconButtonSize].
 * @property contentDescription The content description for accessibility. This should
 *   describe the action or destination the button leads to.
 * @property icon The resource ID of the icon to be displayed.
 */
@Immutable
data class IconButtonLink(
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
            containerColor = Color.Transparent,
            contentColor = ColorsPalette().contentPalette.accent
        )

    override val focus: IconButtonState
        get() = IconButtonState(
            border = BorderStroke(
                width = Dimensions().dp.dp3,
                color = ColorsPalette().contentPalette.accent
            ),
            containerColor = Color.Transparent,
            contentColor = ColorsPalette().contentPalette.accent
        )

    override val hover: IconButtonState
        get() = IconButtonState(
            border = null,
            containerColor = Color.Transparent,
            contentColor = ColorsPalette().contentPalette.accent
        )

    override val press: IconButtonState
        get() = IconButtonState(
            border = null,
            containerColor = Color.Transparent,
            contentColor = ColorsPalette().contentPalette.contrast
        )

    override val disabled: IconButtonState
        get() = IconButtonState(
            border = null,
            containerColor = Color.Transparent,
            contentColor = ColorsPalette().contentPalette.stateDisabled
        )
}