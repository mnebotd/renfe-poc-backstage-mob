package com.core.presentation.ui.components.form.validators

/**
 * `Validators` is a sealed interface that defines a set of validation rules that can be applied to data.
 *
 * Each class implementing this interface represents a specific validation rule.
 * These validators can be used to verify data integrity and enforce constraints on input values.
 *
 * **Available Validators:**
 *
 * - `MinSize`: Checks if the size (e.g., length of a string, number of elements in a list) is at least a given limit.
 * - `MaxSize`: Checks if the size is at most a given limit.
 * - `Email`: Checks if the input string is a valid email address.
 * - `Phone`: Checks if the input string is a valid phone number.
 * - `MinValue`: Checks if the numeric value is at least a given limit.
 * - `MaxValue`: Checks if the numeric value is at most a given limit.
 * - `Dni`: Checks if the input string is a valid Spanish DNI (National Identity Document).
 * - `Nie`: Checks if the input string is a valid Spanish NIE (Foreigner Identification Number).
 * - `Passport`: Checks if the input string is a valid passport number.
 * - `Required`: Checks if the value is present (not null or empty).
 * - `Custom`: Allows defining a custom validation function for more complex validation logic.
 * - `Regex`: Checks if the input string matches a given regular expression.
 * - `Equal`: Checks if the input string is equal to a reference string.
 */
sealed interface Validators {
    class MinSize(var limit: Int, var message: String) : Validators
    class MaxSize(var limit: Int, var message: String) : Validators
    class Email(var message: String) : Validators
    class Phone(var message: String) : Validators
    class MinValue(var limit: Double, var message: String) : Validators
    class MaxValue(var limit: Double, var message: String) : Validators
    class Required(var message: String?) : Validators
    class Custom(var message: String, var function: (value: Any) -> Boolean) : Validators
    class Regex(var regex: String, var message: String) : Validators
    class Equal(var reference: String, var message: String) : Validators
}