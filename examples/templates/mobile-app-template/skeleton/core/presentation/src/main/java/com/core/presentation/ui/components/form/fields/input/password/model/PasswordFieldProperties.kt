package com.core.presentation.ui.components.form.fields.input.password.model

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import com.core.presentation.ui.components.form.fields.input.InputFieldState
import com.core.presentation.ui.components.form.validators.Validators
import com.core.presentation.ui.theme.dimensions.Dimensions
import com.core.presentation.ui.theme.palette.ColorsPalette
import com.core.presentation.ui.utils.interaction.model.IInteractionStateProperties
import com.core.presentation.ui.utils.shadow.model.ShadowFocus

/**
 * `PasswordFieldProperties` is a data class that encapsulates the properties
 * required to define the behavior and appearance of a password field. It extends `InputFieldState`
 * to manage the field's value and validation state and implements `IInteractionStateProperties`
 * to define visual and behavior states for different interaction modes (default, focus, hover,
 * press, disabled).
 *
 * This class is designed to be immutable, ensuring that once an instance is created,
 * its properties cannot be changed. This enhances predictability and simplifies
 * state management in composable functions.
 *
 * @property validators A list of `Validators` to be applied to the password field's input value.
 *                       These validators are used to check if the entered text meets certain criteria,
 *                       such as minimum length, presence of special characters, etc.
 * @property label The label text that describes the purpose of the password field. This label
 *                 is typically displayed above or beside the field to provide context to the user.
 * @property helperText Optional helper text that can be displayed below the password field to
 *                     provide additional guidance or feedback to the user, such as requirements for
 *                     password complexity or error messages related to validation.
 * @property contentDescription Optional content description for accessibility purposes. This description
 *                             is used by screen readers to provide context about the password field to
 *                             users with visual impairments.
 * @see InputFieldState
 * @see IInteractionStateProperties
 * @see PasswordFieldState
 * @see Validators
 */
@Immutable
data class PasswordFieldProperties(
    override val validators: List<Validators>,
    val label: String,
    val helperText: String?,
    val contentDescription: String?
) : InputFieldState(
    initial = null,
    validators = validators
), IInteractionStateProperties<PasswordFieldState> {
    override val default: PasswordFieldState
        get() = PasswordFieldState()

    override val focus: PasswordFieldState
        get() = PasswordFieldState(
            shadow = ShadowFocus(
                shape = RoundedCornerShape(
                    size = Dimensions().dp.dp8
                ),
                backgroundColor = ColorsPalette().contentPalette.alwaysLight
            )
        )

    override val hover: PasswordFieldState
        get() = PasswordFieldState()

    override val press: PasswordFieldState
        get() = PasswordFieldState()

    override val disabled: PasswordFieldState
        get() = PasswordFieldState()
}