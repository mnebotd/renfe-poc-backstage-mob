package com.core.presentation.ui.components.form.fields.select

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.core.presentation.ui.components.form.fields.BaseFieldState
import com.core.presentation.ui.components.form.validators.Validators

/**
 * Represents the state of a select field (e.g., a checkbox or toggle).
 *
 * This class extends [BaseFieldState] and manages the state of a boolean value,
 * along with its validation. It's specifically designed for fields where the
 * user selects or deselects an option, represented by `true` or `false` respectively.
 *
 * @param initial The initial state of the select field (true if selected, false otherwise).
 * @param validators A list of [Validators] to apply to the select field's state.
 *                   Supports [Validators.Required] and [Validators.Custom] validation types.
 *
 * @property value The current boolean value of the select field. It is a nullable boolean.
 *                  The default value is the [initial] parameter.
 *                  Changes to this property will trigger recomposition.
 */
open class SelectFieldState(
    initial: Boolean,
    validators: List<Validators>
) : BaseFieldState<Boolean>(
    initial = initial,
    validators = validators,
    transform = null
) {
    override var value: Boolean? by mutableStateOf(initial)

    /**
     * Changes the current value of the object and clears any existing error state.
     *
     * @param select The new boolean value to set.
     */
    fun change(select: Boolean) {
        clearError()
        this.value = select
    }

    /**
     * Validates the current state of the checkbox based on the registered validators.
     *
     * This function iterates through the list of validators and executes them accordingly.
     * It supports `Validators.Required` and `Validators.Custom` validators.
     *
     * - `Validators.Required`: Checks if the checkbox is currently checked (true).
     * - `Validators.Custom`: Executes a user-defined validation function.
     *
     * If any other type of validator is encountered, it throws an exception, suggesting the use of `Validators.Custom`.
     *
     * @return `true` if all validators pass (i.e., the checkbox state is valid), `false` otherwise.
     * @throws Exception if a validator other than `Validators.Required` or `Validators.Custom` is used.
     */
    override fun validate(): Boolean {
        val validations = validators.map {
            when (it) {
                is Validators.Required -> validateRequired(it.message)
                is Validators.Custom -> validateCustom(it.function, it.message)
                else -> throw Exception("${it::class.simpleName} validator cannot be called on checkbox state. Did you mean Validators.Custom?")
            }
        }
        return validations.all { it }
    }

    /**
     * Validates if a value is considered "required" (i.e., not null and not false).
     * If the value is considered invalid (null or false), it sets an error message.
     *
     * @param message The error message to display if the value is invalid. If null, no error is displayed.
     * @return `true` if the value is considered valid (not null and not false), `false` otherwise.
     *
     * This function assumes that the `value` property is accessible within the same class scope
     * and that there is a `setError()` function available to display the provided error message.
     * It's typically used in validation scenarios where a certain value must be present and true.
     */
    private fun validateRequired(message: String?): Boolean {
        val valid = value ?: false
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates a custom condition against the current value.
     *
     * This function takes a lambda function [function] and a [message] as input.
     * It executes the provided [function] with the current value (or `false` if the value is null) and checks if the result is true (valid).
     * If the result is false (invalid), it sets the error message using the provided [message].
     *
     * @param function A lambda function that takes a Boolean (the current value or false if null) and returns a Boolean indicating validity.
     *                 The input to this function represents the value being validated. If the value is null, it defaults to `false`.
     *                 The output represents whether the value is valid according to the custom rule.
     * @param message The error message to be set if the custom validation fails.
     * @return `true` if the custom validation is successful (function returns true), `false` otherwise.
     */
    private fun validateCustom(function: (Boolean) -> Boolean, message: String): Boolean {
        val valid = function(value ?: false)
        if (!valid) setError(message)
        return valid
    }
}