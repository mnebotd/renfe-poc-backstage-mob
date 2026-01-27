package com.core.presentation.ui.components.cta.button.model

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.core.presentation.ui.components.cta.button.model.base.ButtonSelectedState
import com.core.presentation.ui.components.cta.button.model.base.ButtonState
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.shadow.model.ShadowFocus

/**
 * Represents the properties and states of a link-style button in the ButtonComponent.
 *
 * `ButtonLink` defines a button that behaves like a hyperlink, with specific styling
 * for its different interaction states (default, focus, hover, press, disabled).
 * It inherits common properties from [ButtonProperties].
 *
 * @property size The size of the button, defined by [ButtonSize].
 * @property text The text content of the button.
 * @property contentDescription An optional description for accessibility purposes.
 * @property leadingIcon An optional resource ID for an icon to be displayed before the text.
 * @property trailingIcon An optional resource ID for an icon to be displayed after the text.
 *
 * **States:**
 *
 * - **default:**
 *   - `containerColor`: Transparent.
 *   - `contentColor`: `contentPalette.accent` (primary link color).
 *
 * - **focus:**
 *   - `containerColor`: Transparent.
 *   - `contentColor`: `contentPalette.accent`.
 *   - `shadow`: `ShadowFocus` with a circular shape and `backgroundColor = containerColor`.
 *
 * - **hover:**
 *   - `containerColor`: Transparent.
 *   - `contentColor`: `contentPalette.accent`.
 *   - `textDecoration`: `TextDecoration.Underline`.
 *
 * - **press:**
 *   - `containerColor`: Transparent.
 *   - `contentColor`: `contentPalette.contrast` (darker accent for pressed state).
 *   - `textDecoration`: `TextDecoration.Underline`.
 *
 * - **disabled:**
 *   - `containerColor`: Transparent.
 *   - `contentColor`: `contentPalette.stateDisabled` (muted color for disabled state).
 *
 * This class is `Immutable`, meaning its properties cannot be changed after creation, ensuring predictable behavior.
 */
@Immutable
data class ButtonLink(
    override val size: ButtonSize,
    override val text: String,
    override val contentDescription: ButtonContentDescription? = null,
    override val leadingIcon: ButtonIcon? = null,
    override val trailingIcon: ButtonIcon? = null,
    val containerColor: Color
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
                containerColor = Color.Transparent,
                contentColor = ColorsPalette().contentPalette.accent
            ),
            nonEnabledState = ButtonState(
                containerColor = Color.Transparent,
                contentColor = ColorsPalette().contentPalette.accent
            )
        )

    override val focus: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = Color.Transparent,
                contentColor = ColorsPalette().contentPalette.accent,
                shadow = ShadowFocus(
                    shape = CircleShape,
                    backgroundColor = containerColor
                )
            ),
            nonEnabledState = ButtonState(
                containerColor = Color.Transparent,
                contentColor = ColorsPalette().contentPalette.accent,
                shadow = ShadowFocus(
                    shape = CircleShape,
                    backgroundColor = containerColor
                )
            )
        )


    override val hover: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = Color.Transparent,
                contentColor = ColorsPalette().contentPalette.accent,
                textDecoration = TextDecoration.Underline
            ),
            nonEnabledState = ButtonState(
                containerColor = Color.Transparent,
                contentColor = ColorsPalette().contentPalette.accent,
                textDecoration = TextDecoration.Underline
            )
        )


    override val press: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = Color.Transparent,
                contentColor = ColorsPalette().contentPalette.contrast,
                textDecoration = TextDecoration.Underline
            ),
            nonEnabledState = ButtonState(
                containerColor = Color.Transparent,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            )
        )


    override val disabled: ButtonSelectedState
        get() = ButtonSelectedState(
            enabledState = ButtonState(
                containerColor = Color.Transparent,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            ),
            nonEnabledState = ButtonState(
                containerColor = Color.Transparent,
                contentColor = ColorsPalette().contentPalette.stateDisabled
            )
        )

}