package com.core.presentation.ui.components.cta.button.model

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Immutable
import com.core.presentation.ui.components.cta.button.model.base.ButtonSelectedState
import com.core.presentation.ui.components.cta.button.model.base.ButtonState
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.shadow.model.ShadowFocus

/**
 * Represents a tertiary button.
 *
 * Tertiary buttons are typically used for less prominent actions, such as secondary or optional actions.
 * They have a transparent background by default and are less visually emphasized than primary or secondary buttons.
 *
 * @property size The size of the button (e.g., small, medium, large). See [ButtonSize].
 * @property text The text displayed on the button.
 * @property contentDescription The content description for accessibility purposes. See [ButtonContentDescription].
 * @property leadingIcon Optional resource ID of an icon to be displayed before the text.
 * @property trailingIcon Optional resource ID of an icon to be displayed after the text.
 *
 * This class defines the visual states of a tertiary button, including:
 * - **default:** The default, uninteraction state.
 * - **focus:** The state when the button has focus.
 * - **hover:** The state when the mouse hovers over the button.
 * - **press:** The state when the button is being pressed.
 * - **disabled:** The state when the button is disabled.
 *
 * Each state specifies the button's container color, content color, and optionally a shadow.
 */
@Immutable
data class ButtonTertiary(
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
                containerColor = ColorsPalette().backgroundPalette.tertiary,
                contentColor = ColorsPalette().contentPalette.accent
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.tertiary,
                contentColor = ColorsPalette().contentPalette.accent
            )
        )

    override val focus: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.tertiary,
                contentColor = ColorsPalette().contentPalette.accent,
                shadow = ShadowFocus(
                    shape = CircleShape,
                    backgroundColor = ColorsPalette().backgroundPalette.alwaysLight
                )
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.alwaysLight,
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
                containerColor = ColorsPalette().backgroundPalette.tertiary,
                contentColor = ColorsPalette().contentPalette.accent
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.secondary,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            )
        )

    override val press: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.contrast,
                contentColor = ColorsPalette().backgroundPalette.alwaysLight
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.secondary,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            )
        )


    override val disabled: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.secondary,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.secondary,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            )
        )

}