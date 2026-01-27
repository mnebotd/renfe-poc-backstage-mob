package com.core.presentation.ui.components.form.fields.input.text.model

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import com.core.presentation.ui.components.form.fields.input.InputFieldState
import com.core.presentation.ui.components.form.validators.Validators
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.interaction.model.IInteractionStateProperties
import com.core.presentation.ui.utils.shadow.model.ShadowFocus

/**
 * `TextFieldProperties` is a data class that encapsulates the properties and states of a `TextFieldComponent`.
 *
 * It defines the initial value, validators, label, helper text, and content description of a text field.
 * It also specifies the different interaction states (default, focus, hover, press, disabled) and their
 * corresponding visual representations via `TextFieldState`.
 *
 * @property initial The initial value of the text field. Can be null if the field starts empty.
 * @property validators A list of validators that are applied to the text field's value.
 *                    These validators are used to determine if the input is valid or not.
 *                    See [Validators] for available validator options.
 * @property label The label displayed above or within the text field, providing a description of the expected input.
 * @property readOnly Whether or not field is not editable.
 * @property helperText Optional helper text that provides additional guidance or context for the text field.
 *                     It's usually displayed below the text field. Can be null if no helper text is needed.
 * @property contentDescription The content description used for accessibility purposes, describing the text field's purpose.
 *                              Can be null if no specific description is needed.
 * @property default The default [TextFieldState] representing the text field's appearance when no interaction is occurring.
 * @property focus The [TextFieldState] representing the text field's appearance when it has focus.
 *                In this case, a shadow is applied to indicate focus.
 * @property hover The [TextFieldState] representing the text field's appearance when the user's pointer is hovering over it.
 * @property press The [TextFieldState] representing the text field's appearance when the user is pressing down on it.
 * @property disabled The [TextFieldState] representing the text field's appearance when it is disabled.
 *
 * @see InputFieldState
 * @see TextFieldState
 * @see Validators
 * @see IInteractionStateProperties
 * @see ShadowFocus
 * @see Dimensions
 */
@Immutable
data class TextFieldProperties(
    override val initial: String?,
    override val validators: List<Validators>,
    val label: String?,
    val readOnly: Boolean = false,
    val helperText: String?,
    val contentDescription: String?
) : InputFieldState(
    initial = initial,
    validators = validators
), IInteractionStateProperties<TextFieldState> {
    override val default: TextFieldState
        get() = TextFieldState()

    override val focus: TextFieldState
        get() = TextFieldState(
            shadow = ShadowFocus(
                shape = RoundedCornerShape(
                    size = Dimensions().dp.dp8
                ),
                backgroundColor = ColorsPalette().contentPalette.alwaysLight
            )
        )

    override val hover: TextFieldState
        get() = TextFieldState()

    override val press: TextFieldState
        get() = TextFieldState()

    override val disabled: TextFieldState
        get() = TextFieldState()
}