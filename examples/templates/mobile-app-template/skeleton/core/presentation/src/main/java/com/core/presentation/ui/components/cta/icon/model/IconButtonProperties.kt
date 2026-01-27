package com.core.presentation.ui.components.cta.icon.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import com.core.presentation.ui.components.cta.icon.model.base.IconButtonState
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.utils.interaction.model.IInteractionStateProperties

/**
 * Represents the properties of a IconButtonComponent, defining its appearance and behavior.
 *
 * This class encapsulates the visual characteristics and accessibility information. It's designed
 * to be immutable, ensuring that the properties of a button cannot change after it's created.
 *
 * @property size The size of the icon button, determining its physical dimensions.
 *                  It should be one of the values defined in [IconButtonSize].
 *                  e.g., [IconButtonSize.Small], [IconButtonSize.Medium], [IconButtonSize.Large]
 * @property contentDescription The text used by accessibility services to describe the
 *                               button's purpose. This is essential for users with
 *                               visual impairments.
 * @property icon The resource ID of the drawable to be used as the icon for the button.
 *                This should be a drawable resource available in the project's resources.
 */
@Immutable
sealed class IconButtonProperties(
    open val size: IconButtonSize,
    open val contentDescription: IconButtonContentDescription,
    @DrawableRes open val icon: Int
) : IInteractionStateProperties<IconButtonState>

/**
 * Represents the different sizes available for a IconButtonComponent.
 *
 * This sealed class defines the possible sizes for an icon button, providing
 * a consistent and type-safe way to specify the desired size. Each size is
 * represented by a data object that encapsulates the corresponding [Dp] value.
 *
 * @property size The size of the icon button, represented as a [Dp] value.
 */
@Immutable
sealed class IconButtonSize(
    val size: Dp
) {

    /**
     * Represents the "Large" size for a IconButton.
     *
     * This object defines the dimensions for an icon button considered to be large, typically used
     * for primary actions or in areas where a larger, more prominent button is desired.
     *
     * @property size The size of the icon button, which is set to 48dp.
     */
    data object Large : IconButtonSize(
        size = Dimensions().dp.dp48
    )

    /**
     * Represents a medium-sized icon button.
     *
     * This object defines the specifications for a medium-sized icon button within the  (presumably, a design system or library) context.
     * It inherits from `IconButtonSize` and sets the size to 40dp, as defined by `Dimensions.dp.dp40`.
     *
     * This object is likely used to provide a consistent size for medium icon buttons across an application or UI library.
     *
     * @see IconButtonSize
     * @see Dimensions
     */
    data object Medium : IconButtonSize(
        size = Dimensions().dp.dp40
    )

    /**
     * Represents a small-sized icon button.
     *
     * This object defines the configuration for an icon button with a small size.
     * It inherits from [IconButtonSize] and sets the button's size to 32dp.
     *
     * Use this object when you need a smaller icon button in your UI.
     *
     * @see IconButtonSize
     * @see Dimensions
     */
    data object Small : IconButtonSize(
        size = Dimensions().dp.dp32
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
data class IconButtonContentDescription(
    val enabledText: String,
    val disabledText: String?
)