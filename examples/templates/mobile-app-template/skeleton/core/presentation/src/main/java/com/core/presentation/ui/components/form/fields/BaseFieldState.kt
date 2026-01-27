package com.core.presentation.ui.components.form.fields

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.core.presentation.ui.components.form.utils.Transform
import com.core.presentation.ui.components.form.validators.Validators

/**
 * Represents the state of a form field, including its value, validation status, and transformation logic.
 *
 * This abstract class provides a foundation for managing the state of different types of form fields.
 * It handles basic functionalities like setting and clearing errors, validating the field's value,
 * and applying a transformation to the value before returning it.
 *
 * @param T The type of the field's value.
 * @property initial The initial value of the field. Can be null.
 * @property validators A list of [Validators] to be applied to the field's value during validation.
 * @property transform An optional [Transform] function to apply to the field's value before returning it via [getData].
 *                     If null, the raw value is returned.
 */
abstract class BaseFieldState<T>(
    open val initial: T?,
    open val validators: List<Validators>,
    open val transform: Transform<T>?,
) {
    abstract var value: T?
        internal set

    var error: BaseFieldError? by mutableStateOf(value = null)

    /**
     * Sets an error state for the field.
     *
     * This function sets an error on the field, represented by a [BaseFieldError].
     * Once an error is set, subsequent calls to this function will be ignored.
     * This prevents overwriting an existing error with a new one.
     *
     * @param errorMessage The error message to be associated with the error.
     *                     If null, the error will still be set, but it won't have a message.
     */
    fun setError(
        errorMessage: String?
    ) {
        if (error != null) {
            return
        }

        error = BaseFieldError(
            errorMessage = errorMessage
        )
    }

    /**
     * Forces an error state for the field.
     *
     * This function sets the field's error state to true, allowing you to
     * display an error message regardless of the field's actual validation state.
     * Optionally, it can also clear the field's current value.
     *
     * @param errorMessage The error message to display. If null, a default error
     *                     message might be used depending on the implementation of
     *                     `BaseFieldError`.
     * @param clearValue   If true, the field's current value will be cleared (set to null).
     *                     Defaults to false.
     */
    fun forceError(
        errorMessage: String?,
        clearValue: Boolean = false
    ) {
        error = BaseFieldError(
            errorMessage = errorMessage,
            forceError = true
        )

        if (clearValue) {
            value = null
        }
    }

    /**
     * Clears the current error state by setting the `error` property to null.
     *
     * This function is used to reset the error status, typically after an error
     * has been handled or resolved.  Subsequent operations will no longer be
     * considered in an error state until a new error is encountered.
     */
    fun clearError() {
        error = null
    }

    /**
     * Validates the current state of the object.
     *
     * This function should be overridden by concrete classes to implement their specific validation logic.
     * It is intended to check if the object's properties are in a valid state according to the
     * constraints and requirements of the object's domain.
     *
     * @return `true` if the object is in a valid state, `false` otherwise.
     */
    abstract fun validate(): Boolean

    /**
     * Retrieves the data held by this object.
     *
     * This function returns the data, potentially after applying a transformation.
     *
     * If a transformation function (`transform`) is defined and the internal value (`value`) is not null,
     * the transformation function is applied to the value, and the result is returned.
     *
     * If either the transformation function (`transform`) is null or the internal value (`value`) is null,
     * the function returns the internal value (`value`) directly (which might be null).
     *
     * @return The transformed data if a transformation is applied and the value is not null,
     *         otherwise the internal value which might be null.
     */
    open fun getData(): T? {
        return if (transform == null || value == null) {
            value
        } else {
            transform!!.transform(value!!)
        }
    }
}

/**
 * Represents a base error associated with a field in a form or data structure.
 *
 * This class encapsulates an optional error message and a flag indicating whether the error
 * should be forcefully displayed, regardless of other validation conditions.
 *
 * @property errorMessage An optional string containing the error message. If `null`, it suggests no error.
 * @property forceError A boolean flag. If `true`, the error should be displayed even if other
 *                     validation conditions are met. Defaults to `false`.
 */
@Immutable
data class BaseFieldError(
    val errorMessage: String?,
    val forceError: Boolean = false
)