package com.core.presentation.ui.components.form

import com.core.presentation.ui.components.form.fields.BaseFieldState


/**
 * Represents the state of a form, composed of a list of [BaseFieldState] objects.
 *
 * This class provides a way to manage and validate multiple fields within a form.
 *
 * @param T The type of [BaseFieldState] that this form manages.
 * @property fields The list of [BaseFieldState] objects representing the fields in the form.
 */
open class FormState<T : BaseFieldState<*>>(val fields: List<T>) {

    fun validate(): Boolean = fields.map {
        it.validate()
    }.all { it } && fields.any { it.error == null }
}