package com.core.presentation.ui.components.cta.button.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Immutable
import com.core.presentation.ui.components.cta.button.model.base.ButtonSelectedState
import com.core.presentation.ui.components.cta.button.model.base.ButtonState
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.shadow.model.ShadowFocus

/**
 * Represents the properties and states of a secondary button.
 *
 * A secondary button is typically used for less prominent actions, offering an alternative
 * action to the primary button. It features a transparent or subtly colored background
 * with a colored border and text.
 *
 * This data class defines the button's core properties like size, text, and optional
 * leading/trailing icons, as well as its visual states for default, focus, hover, press,
 * and disabled interactions.
 *
 * @param size The size of the button, defined by [ButtonSize]. Determines the button's height and padding.
 * @param text The text label displayed within the button.
 * @param contentDescription An optional description of the button's purpose for accessibility.
 * @param leadingIcon An optional resource ID for an icon to be displayed before the text.
 * @param trailingIcon An optional resource ID for an icon to be displayed after the text.
 */
@Immutable
data class ButtonSecondary(
    override val size: ButtonSize,
    override val text: String,
    override val contentDescription: ButtonContentDescription? = null,
    override val leadingIcon: ButtonIcon? = null,
    override val trailingIcon: ButtonIcon? = null
) : ButtonProperties(
    size = size,
    text = text,
    contentDescription = contentDescription,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon
) {
    override val default: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.alwaysLight,
                contentColor = ColorsPalette().contentPalette.accent,
                borderStroke = BorderStroke(
                    width = Dimensions().dp.dp1,
                    color = ColorsPalette().borderPalette.accent
                )
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.alwaysLight,
                contentColor = ColorsPalette().contentPalette.accent,
                borderStroke = BorderStroke(
                    width = Dimensions().dp.dp1,
                    color = ColorsPalette().borderPalette.stateDisabled
                )
            )
        )

    override val focus: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.alwaysLight,
                contentColor = ColorsPalette().contentPalette.accent,
                shadow = ShadowFocus(
                    shape = CircleShape,
                    backgroundColor = ColorsPalette().backgroundPalette.alwaysLight
                )
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.stateDisabled,
                contentColor = ColorsPalette().contentPalette.stateDisabled,
                shadow = ShadowFocus(
                    shape = CircleShape,
                    backgroundColor = ColorsPalette().backgroundPalette.alwaysLight
                )
            )
        )

    override val hover: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.contrastLow,
                contentColor = ColorsPalette().contentPalette.alwaysLight
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.stateDisabled,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            )
        )

    override val press: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.contrast,
                contentColor = ColorsPalette().contentPalette.alwaysLight
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.stateDisabled,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            )
        )

    override val disabled: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.stateDisabled,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.stateDisabled,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            )
        )

}