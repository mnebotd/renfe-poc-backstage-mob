package com.core.presentation.ui.components.form.utils

/**
 * A functional interface representing a transformation operation on a value of type [T].
 *
 * Implementations of this interface define how a given value should be transformed into a new value
 * of the same type. This interface is useful for encapsulating reusable transformation logic that can
 * be applied to different values or within various contexts.
 *
 * @param T The type of the value being transformed.
 */
fun interface Transform<T> {
    fun transform(value: T): T
}