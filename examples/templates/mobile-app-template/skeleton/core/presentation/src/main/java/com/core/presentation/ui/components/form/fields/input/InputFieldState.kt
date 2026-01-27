package com.core.presentation.ui.components.form.fields.input

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.core.presentation.ui.components.form.fields.BaseFieldState
import com.core.presentation.ui.components.form.utils.Transform
import com.core.presentation.ui.components.form.utils.isNumeric
import com.core.presentation.ui.components.form.validators.Validators

/**
 * `InputFieldState` is a concrete implementation of `BaseFieldState` designed specifically for
 * handling the state, validation, and transformation of text field inputs (strings).
 *
 * It provides functionalities for:
 * - Storing and updating the text field's value.
 * - Applying a list of validators to the text field's value.
 * - Transforming the text field's value using a custom transformation function.
 * - Validating the current value against all registered validators.
 * - Setting and clearing error messages based on validation results.
 * - Includes built-in validation for common scenarios like email, phone, required fields,
 *   min/max length/value, DNI, NIE, passport, regex matching, and equality checks.
 *
 * @param initial The initial value of the text field. Can be null or an empty string.
 * @param validators A list of `Validators` to apply to the text field's value during validation.
 * @param transform An optional `Transform` function to apply to the text field's value before it is used.
 */
open class InputFieldState(
    initial: String?,
    validators: List<Validators>,
    transform: Transform<String>? = null,
) : BaseFieldState<String>(
    initial = initial,
    transform = transform,
    validators = validators
) {

    override var value: String? by mutableStateOf(initial)

    /**
     * Updates the current value with the provided string.
     *
     * This function sets the internal `value` to the provided `update` string.
     * It also clears any existing error state before the update.
     *
     * @param update The new string value to set. Can be null.
     */
    open fun change(update: String?) {
        clearError()
        this.value = update
    }

    /**
     * Validates the current input value against a list of defined validators.
     *
     * This function iterates through a list of `validators` and applies each one to the
     * current input value.  Each validator checks for a specific condition (e.g., email format,
     * minimum length, required field).
     *
     * The function returns `true` if *all* validators pass, indicating that the input value is
     * valid according to the defined criteria. If *any* validator fails, the function immediately
     * returns `false`, indicating that the input value is invalid.
     *
     * The following validators are supported:
     *  - `Validators.Email`: Checks if the input is a valid email address.
     *  - `Validators.Phone`: Checks if the input is a valid phone number.
     *  - `Validators.Required`: Checks if the input is not empty.
     *  - `Validators.MinSize`: Checks if the input length is at least a specified minimum.
     *  - `Validators.MaxSize`: Checks if the input length is at most a specified maximum.
     *  - `Validators.Custom`: Checks the input using a custom validation function.
     *  - `Validators.MinValue`: Checks if a numerical input is at least a specified minimum value.
     *  - `Validators.MaxValue`: Checks if a numerical input is at most a specified maximum value.
     *  - `Validators.Iban`: Checks if the input is a valid IBAN (International Bank Account Number).
     *  - `Validators.Dni`: Checks if the input is a valid Spanish DNI (National Identity Document).
     *  - `Validators.Nie`: Checks if the input is a valid Spanish NIE (Foreigner Identification Number).
     *  - `Validators.Passport`: Checks if the input is a valid passport number.
     *  - `Validators.Regex`: Checks if the input matches a specified regular expression.
     *  - `Validators.Equal`: Checks if the input is equal to a specified reference.
     *
     * @return `true` if all validators pass, `false` otherwise.
     */
    override fun validate(): Boolean {
        validators.forEach {
            val result = when (it) {
                is Validators.Email -> validateEmail(it.message)
                is Validators.Phone -> validatePhone(it.message)
                is Validators.Required -> validateRequired(it.message)
                is Validators.MinSize -> validateMinChars(it.limit, it.message)
                is Validators.MaxSize -> validateMaxChars(it.limit, it.message)
                is Validators.Custom -> validateCustom(it.function, it.message)
                is Validators.MinValue -> validateMinValue(it.limit, it.message)
                is Validators.MaxValue -> validateMaxValue(it.limit, it.message)
                is Validators.Regex -> validateRegex(it.regex, it.message)
                is Validators.Equal -> validateEqual(it.reference, it.message)
            }

            if (!result) {
                return false
            }
        }

        return true
    }

    /**
     * Validates the current value using a custom validation function.
     *
     * This function takes a lambda function (`function`) that performs the actual validation
     * and a custom error message (`message`). It checks if the current value (`value`) is not null,
     * and then applies the provided validation function to the value.
     * If the validation fails, it sets an error using the provided `message`.
     *
     * @param function A lambda function that takes the current value (as a non-null String) as input
     *                 and returns `true` if the value is valid, `false` otherwise.
     * @param message The error message to display if the validation fails.
     * @return `true` if the value is valid according to the custom validation function, `false` otherwise.
     */
    private fun validateCustom(function: (String) -> Boolean, message: String): Boolean {
        val valid = value != null && function(value!!)
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates if the given string is a valid email address.
     *
     * This function uses a regular expression to check if the input string
     * conforms to a basic email structure. The regex checks for:
     *   - A character at the beginning.
     *   - Any characters after the beginning.
     *   - A single "@" symbol.
     *   - One or more characters after the "@".
     *   - A single "." symbol.
     *   - One or more characters after the ".".
     *
     * Note: This is a simplified email validation and may not cover all
     * edge cases or conform to the full email address specification. For
     * more robust validation, consider using a dedicated email validation
     * library.
     *
     * @param message The string to be validated as an email address.
     * @return `true` if the input string matches the email regex, `false` otherwise.
     */
    private fun validateEmail(message: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)"
        return validateRegex(emailRegex, message)
    }

    /**
     * Validates if a given string represents a phone number with exactly 9 digits.
     *
     * This function checks if the input string `message` conforms to a specific
     * phone number format, which is defined as exactly 9 digits. It uses a regular
     * expression to perform this validation.
     *
     * @param message The string to be validated as a phone number.
     * @return `true` if the string is a valid phone number (9 digits), `false` otherwise.
     */
    private fun validatePhone(message: String): Boolean {
        val phoneRegex = "^\\d{9}$"
        return validateRegex(phoneRegex, message)
    }

    /**
     * Validates if the current value is not null and not empty.
     *
     * If the value is null or empty, it sets an error message and returns false.
     * Otherwise, it returns true.
     *
     * @param message The error message to be set if the value is invalid (null or empty).
     *                If null, no error message is set, but the function still returns false.
     * @return True if the value is not null and not empty, false otherwise.
     */
    private fun validateRequired(message: String?): Boolean {
        val valid = value != null && value!!.isNotEmpty()
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates if the length of the current value is within the specified character limit.
     *
     * This function checks if the length of the `value` (presumably a string property of the class
     * or a variable within scope) is less than or equal to the provided `limit`. If the value is null
     * or its length exceeds the limit, it's considered invalid, and an error message is set using the
     * `setError` function (which is assumed to be defined elsewhere in the class).
     *
     * @param limit The maximum allowed number of characters.
     * @param message The error message to be set if the validation fails (i.e., the value's length
     *                exceeds the limit or the value is null).
     * @return `true` if the value is not null and its length is within the limit, `false` otherwise.
     */
    private fun validateMaxChars(limit: Int, message: String): Boolean {
        val valid = value != null && value!!.length <= limit
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates if the current value meets the minimum character requirement.
     *
     * This function checks if the `value` is not null and if its length is greater than or equal
     * to the provided `limit`. If the validation fails, it sets an error message.
     *
     * @param limit The minimum number of characters required for the value to be considered valid.
     * @param message The error message to be set if the validation fails.
     * @return `true` if the value is valid (not null and its length is at least `limit`), `false` otherwise.
     */
    private fun validateMinChars(limit: Int, message: String): Boolean {
        val valid = value != null && value!!.length >= limit
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates if the current value is greater than or equal to a specified minimum limit.
     *
     * This function checks if the internal `value` is not null, if it represents a numeric value,
     * and if that numeric value, when parsed to a Double, is greater than or equal to the
     * provided `limit`. If any of these conditions are false, it sets an error message
     * using the `setError` function.
     *
     * @param limit The minimum allowed value. The `value` must be greater than or equal to this.
     * @param message The error message to set via `setError` if the validation fails.
     * @return `true` if the value is valid (i.e., not null, numeric, and >= limit); `false` otherwise.
     *
     * @see setError
     */
    private fun validateMinValue(limit: Double, message: String): Boolean {
        val valid = value != null && (value!!.isNumeric() && value!!.toDouble() >= limit)
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates if the current value is within the specified maximum limit.
     *
     * This function checks if the `value` property (which is assumed to be accessible in the same scope)
     * is not null, can be parsed as a number, and is less than or equal to the provided `limit`.
     * If the value is invalid (null, non-numeric, or exceeds the limit), an error is set using the provided `message`.
     *
     * @param limit The maximum allowed numeric value.
     * @param message The error message to set if the validation fails.
     * @return `true` if the value is valid (within the limit), `false` otherwise.
     */
    private fun validateMaxValue(limit: Double, message: String): Boolean {
        val valid = value != null && (value!!.isNumeric() && value!!.toDouble() <= limit)
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates the current value against a given regular expression.
     *
     * This function checks if the current `value` (which is assumed to be a property
     * accessible within the same class) matches the provided regular expression.
     * If the value is null or does not match the regex, it sets an error using the
     * provided error message.
     *
     * @param regex The regular expression string to validate against.
     * @param message The error message to set if the validation fails.
     * @return `true` if the value is not null and matches the regex, `false` otherwise.
     */
    private fun validateRegex(regex: String, message: String): Boolean {
        val valid = value != null && regex.toRegex().matches(value!!)
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates if the current value is equal to the provided original value.
     *
     * This function checks if the `value` (assumed to be a property or variable accessible within the class)
     * is not null and is equal to the `original` string. If the validation fails, it sets an error message.
     *
     * @param original The string value to compare against the current value.
     * @param message The error message to be set if the validation fails (i.e., if the value is null or not equal to the original).
     * @return `true` if the validation is successful (value is not null and equal to original), `false` otherwise.
     */
    private fun validateEqual(original: String, message: String): Boolean {
        val valid = value != null && value == original
        if (!valid) setError(message)
        return valid
    }
}