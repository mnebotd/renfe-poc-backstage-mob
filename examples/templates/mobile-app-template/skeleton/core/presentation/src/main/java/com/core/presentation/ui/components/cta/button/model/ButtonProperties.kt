package com.core.presentation.ui.components.cta.button.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import com.core.presentation.ui.components.cta.button.model.base.ButtonSelectedState
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.dimensions.sp.SpDimensions
import com.core.presentation.ui.theme.typographies.Typographies
import com.core.presentation.ui.utils.interaction.model.IInteractionStateProperties


/**
 * Represents the properties of a ButtonComponent, defining its appearance and behavior.
 *
 * This class serves as a data holder for various aspects of a button, including its size,
 * text content, accessibility description, and optional leading/trailing icons.
 * It's designed to be immutable, ensuring consistency and thread safety when used in UI components.
 *
 * @property size The size of the button, determining its dimensions and visual footprint.
 *                 See [ButtonSize] for available options (e.g., SMALL, MEDIUM, LARGE).
 * @property text The text displayed on the button. This is the primary label visible to the user.
 * @property contentDescription An optional description for accessibility purposes.
 *                              This is crucial for users relying on screen readers, providing
 *                              context and meaning to the button's function. If null, the default
 *                              accessibility label might be derived from the text or other context.
 *                              See [ButtonContentDescription] for available options.
 * @property leadingIcon An optional drawable resource ID for an icon to be displayed at the
 *                       beginning (left for LTR languages) of the button, before the text.
 *                       If null, no leading icon is shown.
 * @property trailingIcon An optional drawable resource ID for an icon to be displayed at the
 *                        end (right for LTR languages) of the button, after the text.
 *                        If null, no trailing icon is shown.
 *
 * @see ButtonSize
 * @see ButtonContentDescription
 * @see com.core.presentation.ui.components.cta.button.model.base.ButtonState
 * @see ButtonIcon
 * @see IInteractionStateProperties
 */
@Immutable
sealed class ButtonProperties(
    open val size: ButtonSize,
    open val text: String,
    open val contentDescription: ButtonContentDescription?,
    open val leadingIcon: ButtonIcon?,
    open val trailingIcon: ButtonIcon?,
) : IInteractionStateProperties<ButtonSelectedState>

/**
 * Represents the different sizes available for a ButtonComponent.
 *
 * This sealed class defines the available sizes for ButtonComponent, each with
 * a specific minimum size ([minSize]) and text style ([textStyle]).
 *
 * The `minSize` property specifies the minimum width and height that the
 * button should occupy. The actual size might be larger depending on the
 * button's content.
 *
 * The `textStyle` property defines the style to be applied to the text
 * content within the button.
 *
 * @property minSize The minimum size of the button.
 * @property textStyle The text style of the button's text content.
 */
@Immutable
sealed class ButtonSize(
    val minSize: DpSize,
    val textStyle: TextStyle
) {

    /**
     * Represents the "Large" button size configuration for Buttons.
     *
     * This object defines the dimensions and text style for buttons considered "Large" within the  design system.
     *
     * @property minSize The minimum size of the button, specified as a [DpSize].
     *   - `width`: Set to `Dimensions().dp.dp48` (48dp).
     *   - `height`: Set to `Dimensions().dp.dp48` (48dp).
     * @property textStyle The text style to be applied to the button's label.
     *   - Configured using the `mEmphasis` style with dimensions from `Dimensions().sp`. This implies a bold font style.
     */
    data object Large : ButtonSize(
        minSize = DpSize(
            width = Dimensions().dp.dp48,
            height = Dimensions().dp.dp48
        ),
        textStyle = Typographies(dimensions = SpDimensions()).mid.mEmphasis
    )

    /**
     * Represents the "Medium" size configuration for a Button.
     *
     * This object defines the styling and size constraints for a button when designated as "Medium".
     * It inherits from [ButtonSize], providing a structure for defining button properties.
     *
     * @property minSize The minimum dimensions allowed for a Medium sized button.
     *                   It's set to 40dp x 40dp, ensuring a reasonable touch target size.
     * @property textStyle The text style applied to the button's label.
     *                    It uses [mEmphasis], which is likely a custom style with bold formatting
     *                    and font size defined by [Dimensions().sp].
     *
     * @see ButtonSize
     */
    data object Medium : ButtonSize(
        minSize = DpSize(
            width = Dimensions().dp.dp40,
            height = Dimensions().dp.dp40
        ),
        textStyle = Typographies(dimensions = SpDimensions()).mid.mEmphasis
    )

    /**
     * Represents the "Small" size configuration for a Button.
     *
     * This object defines the visual and dimensional properties for a button when it's set to the "Small" size.
     * It includes specifications for the minimum size and the text style to be used within the button.
     *
     * @property minSize The minimum size of the button. Set to 32dp x 32dp. Buttons of this size or larger will be rendered.
     * @property textStyle The text style to be applied to the button's label. Uses the sEmphasis style with the defined dimensions.
     */
    data object Small : ButtonSize(
        minSize = DpSize(
            width = Dimensions().dp.dp32,
            height = Dimensions().dp.dp32
        ),
        textStyle = Typographies(dimensions = SpDimensions()).mid.sEmphasis
    )
}

/**
 * Represents the content description for a ButtonComponent in different states.
 *
 * This class holds the text that should be used for accessibility purposes, such as screen readers,
 * to describe the button's function. It defines separate descriptions for when the button is enabled
 * and when it is disabled.
 *
 * @property enabledText The content description text to use when the button is enabled. This should
 *                       clearly describe the action that will be performed when the button is clicked.
 *                       This property is mandatory.
 * @property disabledText The content description text to use when the button is disabled. If provided,
 *                        it should indicate why the button is currently inactive. If `null`,
 *                        the `enabledText` will be used as a fallback when the button is disabled.
 *                        This property is optional.
 */
@Immutable
data class ButtonContentDescription(
    val enabledText: String,
    val disabledText: String?
)

/**
 *  Represents the leading icon for a ButtonComponent in different states.
 *
 * `ButtonIcon` represents the static properties for the icon of ButtonComponent.
 *  It's an immutable data class intended to describe the visual aspects of an action that
 *  remain constant across different interaction states.
 *
 * @property icon A drawable resource ID for an icon to be displayed.
 *  *
 * @property Color Optional color of the desired icon.
 */
@Immutable
data class ButtonIcon(
    @DrawableRes val icon: Int,
    val color: Color? = null
)