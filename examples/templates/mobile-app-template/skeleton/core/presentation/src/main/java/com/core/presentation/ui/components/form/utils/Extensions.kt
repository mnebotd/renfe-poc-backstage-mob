package com.core.presentation.ui.components.form.utils

/**
 * Checks if the string can be parsed as a numeric value (Double).
 *
 * This function attempts to convert the string to a Double. If the conversion is successful,
 * it returns true, indicating that the string is numeric. If the conversion fails (e.g., the
 * string contains non-numeric characters), it returns false.
 *
 * Leading and trailing whitespace is allowed and will be trimmed before attempting to parse.
 * The string can represent positive or negative numbers, including floating-point values.
 *
 * @return `true` if the string can be parsed as a numeric value (Double), `false` otherwise.
 */
internal fun String.isNumeric(): Boolean {
    return this.toDoubleOrNull()?.let { true } ?: false
}