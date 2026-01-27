package com.core.presentation.ui.components.cta.button.model

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Immutable
import com.core.presentation.ui.components.cta.button.model.base.ButtonSelectedState
import com.core.presentation.ui.components.cta.button.model.base.ButtonState
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.shadow.model.ShadowFocus

/**
 * Represents the primary button style.
 *
 * This class defines the visual appearance and behavior of a primary button,
 * including its colors, content, and states (default, focused, hovered, pressed, disabled).
 * It extends [ButtonProperties] to inherit common button properties.
 *
 * The primary button is typically used for the main call-to-action on a screen,
 * indicating the most important or frequently used action.
 *
 * @param size The size of the button, defined by [ButtonSize].
 * @param text The text content displayed within the button.
 * @param contentDescription Optional text description for accessibility purposes.
 * @param leadingIcon Optional drawable resource ID for an icon displayed at the start of the button.
 * @param trailingIcon Optional drawable resource ID for an icon displayed at the end of the button.
 *
 * @property default The default state of the button.
 * @property focus The state of the button when it has focus.
 * @property hover The state of the button when the pointer is hovering over it.
 * @property press The state of the button when it is being pressed.
 * @property disabled The state of the button when it is disabled.
 */
@Immutable
data class ButtonPrimary(
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
                containerColor = ColorsPalette().backgroundPalette.accent,
                contentColor = ColorsPalette().contentPalette.alwaysLight
            ),
            nonEnabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.accent,
                contentColor = ColorsPalette().contentPalette.alwaysLight
            )
        )

    override val focus: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = ColorsPalette().backgroundPalette.accent,
                contentColor = ColorsPalette().contentPalette.alwaysLight,
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