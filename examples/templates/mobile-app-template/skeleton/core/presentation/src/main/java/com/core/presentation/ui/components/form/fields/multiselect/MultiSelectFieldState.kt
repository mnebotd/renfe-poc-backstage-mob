package com.core.presentation.ui.components.form.fields.multiselect

import androidx.compose.runtime.toMutableStateList
import com.core.presentation.ui.components.form.fields.BaseFieldState
import com.core.presentation.ui.components.form.utils.Transform
import com.core.presentation.ui.components.form.validators.Validators

/**
 * Represents the state of a multi-select field, allowing for selection, unselection,
 * and validation of multiple values.
 *
 * This class extends `BaseFieldState` to manage a mutable list of selected items of type `T`.
 * It provides methods for manipulating the selection and validating the state against
 * predefined or custom rules.
 *
 * @param T The type of items that can be selected in the multi-select field.
 * @property initial The initial list of selected values. Defaults to an empty mutable list.
 * @property transform An optional transformation function to be applied to the list of selected values.
 * @property validators A list of validators to be applied to the selected values.
 */
open class MultiSelectFieldState<T : Any?>(
    initial: MutableList<T> = mutableListOf(),
    transform: Transform<MutableList<T>>? = null,
    validators: List<Validators> = listOf()
) : BaseFieldState<MutableList<T>>(
    initial = initial,
    transform = transform,
    validators = validators
) {

    override var value: MutableList<T>? = initial.toMutableStateList()

    /**
     * Selects the given value.
     *
     * This function adds the provided `selectValue` to the internal list of selected values
     * and clears any existing error messages associated with the selection.
     *
     * @param selectValue The value of type T to be selected.
     */
    open fun select(selectValue: T) {
        value!!.add(selectValue)
        clearError()
    }

    fun unselect(selectValue: T) = value!!.remove(selectValue)

    fun unselectAll(){
        value?.clear()
    }

    /**
     * Validates the checkbox state against a list of validators.
     *
     * @return `true` if all validations pass, `false` otherwise.
     * @throws Exception if an unsupported validator is used.
     */
    override fun validate(): Boolean {
        val validations = validators.map {
            when (it) {
                is Validators.MinSize -> validateMin(it.limit, it.message)
                is Validators.MaxSize -> validateMax(it.limit, it.message)
                is Validators.Required -> validateRequired(it.message)
                is Validators.Custom -> validateCustom(it.function, it.message)
                else -> throw Exception("${it::class.simpleName} validator cannot be called on checkbox state. Did you mean Validators.Custom?")
            }
        }
        return validations.all { it }
    }

    /**
     * Validates if the input's length is greater than or equal to a minimum limit.
     *
     * @param limit The minimum allowed length for the input.
     * @param message The error message to display if the validation fails.
     *                If null, a default error message might be used or no message set.
     * @return `true` if the input's length is greater than or equal to the limit, `false` otherwise.
     *         If `false`, an error message is set using the `setError` method.
     * @throws NullPointerException if `value` is null. This indicates an issue with
     *                              how the `value` property is being managed before this
     *                              validation is called.
     */
    private fun validateMin(limit: Int, message: String?): Boolean {
        val valid = value!!.size >= limit
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates if the input's length is less than or equal to a specified maximum limit.
     *
     * This function checks if the `value` (presumably a string or collection) of the
     * enclosing class has a size (length or number of elements) that does not exceed
     * the provided `limit`.
     *
     * If the validation fails (i.e., the size is greater than the `limit`), it sets
     * an error message using the `setError` method with the provided `message`.
     *
     * @param limit The maximum allowed size (length or number of elements).
     * @param message The error message to be set if the validation fails.
     *                If `null`, the `setError` method might use a default message
     *                or handle it accordingly.
     * @return `true` if the input's size is less than or equal to the `limit`,
     *         `false` otherwise.
     * @throws NullPointerException if `value` is null when this function is called.
     *                              It's assumed that `value` is initialized before
     *                              this validation is performed.
     */
    private fun validateMax(limit: Int, message: String?): Boolean {
        val valid = value!!.size <= limit
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates that the input field is not empty.
     *
     * @param message The error message to display if validation fails.
     * @return True if the input field is not empty, false otherwise.
     */
    private fun validateRequired(message: String?): Boolean {
        val valid = value!!.isNotEmpty()
        if (!valid) setError(message)
        return valid
    }

    /**
     * Validates the input using a custom validation function.
     *
     * @param function A lambda function that takes a list of type T and returns a Boolean indicating whether the validation passed.
     * @param message An optional error message to display if the validation fails.
     * @return True if the validation passes, false otherwise.
     */
    private fun validateCustom(function: (List<T>) -> Boolean, message: String?): Boolean {
        val valid = function(value!!)
        if (!valid) setError(message)
        return valid
    }
}