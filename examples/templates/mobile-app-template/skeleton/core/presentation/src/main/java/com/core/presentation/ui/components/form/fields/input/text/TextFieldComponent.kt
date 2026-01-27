package com.core.presentation.ui.components.form.fields.input.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.core.presentation.ui.components.cta.button.ButtonComponent
import com.core.presentation.ui.components.cta.button.model.ButtonPrimary
import com.core.presentation.ui.components.cta.button.model.ButtonSize
import com.core.presentation.ui.components.form.FormState
import com.core.presentation.ui.components.form.fields.input.text.model.TextFieldProperties
import com.core.presentation.ui.components.form.validators.Validators
import com.core.presentation.ui.theme.LocalDimensions
import com.core.presentation.ui.theme.LocalPalette
import com.core.presentation.ui.theme.LocalTypographies
import com.core.presentation.ui.theme.Theme
import com.core.presentation.ui.utils.interaction.rememberInteractionState
import com.core.presentation.ui.utils.interaction.utils.state
import com.core.presentation.ui.utils.shadow.focusShadow
import com.core.presentation.ui.utils.shadow.stateShadow

/**
 * A composable function that creates a text field component.
 *
 * This component is designed for general text input. It includes features such as
 * error handling, helper text, custom keyboard options, and visual feedback for
 * different states (enabled, disabled, focused, error).
 *
 * @param modifier The modifier to be applied to the text field.
 * @param properties The properties that define the behavior and appearance of the text field.
 *                   This includes the label, helper text, error state, and change callback.
 * @param enabled Controls whether the field is enabled for user interaction.
 * @param showErrorMessage Whether or not error message should be shown.
 * @param keyboardOptions Options for the keyboard, such as the keyboard type and IME action.
 * @param keyboardActions Actions to be performed when keyboard actions are triggered.
 * @param visualTransformation An optional [VisualTransformation] to apply to the input text.
 *                             Defaults to [VisualTransformation.None]
 *
 * The component handles the following:
 * - **Text Input:** Allows users to enter and edit text.
 * - **Validation:** Supports validation rules defined in [TextFieldProperties].
 * - **Error State:** Displays an error state when the input is invalid or when forced.
 * - **Enabled/Disabled State:** Adjusts its appearance and behavior based on the `enabled` flag.
 * - **Focus Management:** Manages focus and provides default keyboard actions for navigation
 *   (Next, Previous, Done, Go, Search, Send).
 * - **Accessibility:** Provides semantics for accessibility, including content descriptions,
 *   error states, and disabled states.
 * - **Visual Customization:** Allows customization of colors, borders, shadows, and text styles
 *   through [TextFieldProperties] and Material 3 [TextFieldDefaults].
 * - **Helper Text/Error Message:** Displays helper text or an error message based on the
 *   current state.
 *
 * @see TextFieldProperties
 * @see TextField
 * @see Validators
 * @see KeyboardOptions
 * @see KeyboardActions
 * @see VisualTransformation
 */
@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    properties: TextFieldProperties,
    enabled: Boolean,
    showErrorMessage: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
) {
    val focusManager = LocalFocusManager.current

    val interactionSource = remember { MutableInteractionSource() }
    val interactionState = rememberInteractionState(
        enabled = true,
        interactionSource = interactionSource
    )

    val state = remember {
        derivedStateOf {
            interactionState.value.state(
                enabled = enabled,
                properties = properties
            )
        }
    }

    val isError = remember {
        derivedStateOf {
            val error = properties.error ?: return@derivedStateOf false
            if (error.forceError) {
                true
            } else {
                properties.getData()?.isNotBlank() == true
                        && !interactionState.value.focused
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = LocalDimensions.current.dp.dp8
        )
    ) {
        TextField(
            modifier = modifier
                .then(
                    other = state.value.shadow?.let {
                        Modifier
                            .stateShadow(
                                shadow = it
                            )
                    } ?: Modifier
                )
                .background(
                    color = Color.Unspecified,
                    shape = RoundedCornerShape(
                        size = LocalDimensions.current.dp.dp8
                    )
                )
                .border(
                    width = LocalDimensions.current.dp.dp1,
                    color = when {
                        isError.value -> LocalPalette.current.semanticBorderPalette.danger
                        interactionState.value.focused -> LocalPalette.current.borderPalette.accent
                        else -> LocalPalette.current.borderPalette.low
                    },
                    shape = RoundedCornerShape(
                        size = LocalDimensions.current.dp.dp8
                    )
                )
                .onPreviewKeyEvent {
                    if (it.type == KeyEventType.KeyDown && it.key == Key.Tab) {
                        focusManager.moveFocus(
                            FocusDirection.Next
                        )

                        true
                    } else {
                        false
                    }
                }
                .semantics {
                    if (isError.value) {
                        error(description = properties.error?.errorMessage ?: "")
                    }

                    if (!enabled) {
                        disabled()
                    }
                },
            value = properties.getData() ?: "",
            enabled = enabled,
            readOnly = properties.readOnly,
            interactionSource = interactionSource,
            isError = properties.getData()?.isNotBlank() == true
                    && !interactionState.value.focused
                    && properties.error != null,
            shape = RoundedCornerShape(
                size = LocalDimensions.current.dp.dp8,
            ),
            label = {
                properties.label?.let{ labelText ->
                    Text(
                        modifier = Modifier
                            .semantics {
                                contentDescription = buildString {
                                    append(properties.contentDescription ?: properties.label)
                                    properties.helperText?.let {
                                        append(it)
                                    }
                                }
                            },
                        text = labelText,
                        style = LocalTypographies.current.mid.s
                    )
                }
            },
            placeholder = null,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = null,
            supportingText = null,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus(true)
                    keyboardActions.onDone?.invoke(this)
                },
                onGo = {
                    focusManager.clearFocus(true)
                    keyboardActions.onGo?.invoke(this)
                },
                onNext = {
                    focusManager.moveFocus(
                        focusDirection = FocusDirection.Next
                    )
                },
                onPrevious = {
                    focusManager.moveFocus(
                        focusDirection = FocusDirection.Previous
                    )
                },
                onSearch = {
                    focusManager.clearFocus()
                    keyboardActions.onSearch?.invoke(this)
                },
                onSend = {
                    focusManager.clearFocus()
                    keyboardActions.onSend?.invoke(this)
                }
            ),
            colors = TextFieldColors(
                focusedTextColor = LocalPalette.current.contentPalette.high,
                unfocusedTextColor = LocalPalette.current.contentPalette.high,
                disabledTextColor = LocalPalette.current.contentPalette.stateDisabled,
                errorTextColor = LocalPalette.current.contentPalette.high,
                focusedContainerColor = LocalPalette.current.backgroundPalette.base,
                unfocusedContainerColor = LocalPalette.current.backgroundPalette.base,
                disabledContainerColor = LocalPalette.current.backgroundPalette.stateDisabled,
                errorContainerColor = LocalPalette.current.backgroundPalette.base,
                cursorColor = LocalPalette.current.contentPalette.accent,
                errorCursorColor = LocalPalette.current.semanticContentPalette.danger,
                textSelectionColors = TextFieldDefaults.colors().textSelectionColors,
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified,
                disabledIndicatorColor = Color.Unspecified,
                errorIndicatorColor = Color.Unspecified,
                focusedLeadingIconColor = LocalPalette.current.contentPalette.mid,
                unfocusedLeadingIconColor = LocalPalette.current.contentPalette.mid,
                disabledLeadingIconColor = LocalPalette.current.contentPalette.stateDisabled,
                errorLeadingIconColor = LocalPalette.current.semanticContentPalette.danger,
                focusedTrailingIconColor = LocalPalette.current.contentPalette.mid,
                unfocusedTrailingIconColor = LocalPalette.current.contentPalette.mid,
                disabledTrailingIconColor = LocalPalette.current.contentPalette.stateDisabled,
                errorTrailingIconColor = LocalPalette.current.semanticContentPalette.danger,
                focusedLabelColor = LocalPalette.current.contentPalette.accent,
                unfocusedLabelColor = LocalPalette.current.contentPalette.mid,
                disabledLabelColor = LocalPalette.current.contentPalette.stateDisabled,
                errorLabelColor = LocalPalette.current.semanticContentPalette.danger,
                focusedPlaceholderColor = LocalPalette.current.contentPalette.low,
                unfocusedPlaceholderColor = LocalPalette.current.contentPalette.low,
                disabledPlaceholderColor = LocalPalette.current.contentPalette.stateDisabled,
                errorPlaceholderColor = LocalPalette.current.contentPalette.low,
                focusedSupportingTextColor = LocalPalette.current.contentPalette.mid,
                unfocusedSupportingTextColor = LocalPalette.current.contentPalette.mid,
                disabledSupportingTextColor = LocalPalette.current.contentPalette.stateDisabled,
                errorSupportingTextColor = LocalPalette.current.semanticContentPalette.danger,
                focusedPrefixColor = LocalPalette.current.contentPalette.mid,
                unfocusedPrefixColor = LocalPalette.current.contentPalette.mid,
                disabledPrefixColor = LocalPalette.current.contentPalette.stateDisabled,
                errorPrefixColor = LocalPalette.current.contentPalette.mid,
                focusedSuffixColor = LocalPalette.current.contentPalette.mid,
                unfocusedSuffixColor = LocalPalette.current.contentPalette.mid,
                disabledSuffixColor = LocalPalette.current.contentPalette.stateDisabled,
                errorSuffixColor = LocalPalette.current.contentPalette.mid
            ),
            textStyle = LocalTypographies.current.mid.sEmphasis,
            onValueChange = {
                val maxSizeValidator = properties.validators
                    .filterIsInstance<Validators.MaxSize>()
                    .firstOrNull()

                if (maxSizeValidator == null || it.length <= maxSizeValidator.limit) {
                    properties.change(update = it)
                }
            }
        )

        (when {
            isError.value && showErrorMessage -> properties.error?.errorMessage
            else -> properties.helperText
        })?.let {
            Text(
                modifier = Modifier
                    .semantics {
                        hideFromAccessibility()
                    }
                    .padding(
                        horizontal = LocalDimensions.current.dp.dp12
                    ),
                text = it,
                color = when {
                    isError.value -> LocalPalette.current.semanticContentPalette.danger
                    else -> LocalPalette.current.contentPalette.mid
                },
                style = LocalTypographies.current.mid.xs
            )
        }
    }
}

@Preview
@Composable
private fun TextFieldPreview() {
    val textField1 = TextFieldProperties(
        initial = null,
        validators = listOf(
            Validators.Required(
                message = "Requerido"
            )
        ),
        label = "Label text",
        helperText = "Helper text",
        contentDescription = null
    )

    val textField2 = TextFieldProperties(
        initial = null,
        validators = listOf(
            Validators.MinSize(limit = 2, message = "Error limite")
        ),
        label = "Label text",
        helperText = null,
        contentDescription = "Introduce tu usuario, obligatorio"
    )

    val formState = FormState(
        fields = listOf(
            textField1,
            textField2
        )
    )

    Theme {
        val focusManager = LocalFocusManager.current

        val (item1, item2) = remember { FocusRequester.createRefs() }

        Column(
            modifier = Modifier.safeContentPadding()
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        all = LocalDimensions.current.dp.dp16
                    )
                    .focusShadow(
                        shape = RoundedCornerShape(
                            size = LocalDimensions.current.dp.dp8
                        ),
                        backgroundColor = Color.White
                    ),
                text = "Hola"
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        all = LocalDimensions.current.dp.dp16
                    )
                    .semantics {
                        isTraversalGroup = true
                    },
                verticalArrangement = Arrangement.spacedBy(
                    space = LocalDimensions.current.dp.dp16
                )
            ) {
                TextFieldComponent(
                    modifier = Modifier
                        .focusRequester(item1)
                        .focusProperties {
                            next = item2
                        }
                        .onPreviewKeyEvent {
                            if (it.type == KeyEventType.KeyDown && it.key == Key.Tab) {
                                focusManager.moveFocus(
                                    FocusDirection.Next
                                )

                                true
                            } else {
                                false
                            }
                        }
                        .semantics {
                            traversalIndex = 1f
                        },
                    properties = textField1,
                    enabled = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Next
                            )
                        }
                    )
                )

                TextFieldComponent(
                    modifier = Modifier
                        .focusRequester(item2)
                        .onPreviewKeyEvent {
                            if (it.type == KeyEventType.KeyDown && it.key == Key.Tab) {
                                focusManager.moveFocus(
                                    FocusDirection.Next
                                )

                                true
                            } else {
                                false
                            }
                        }
                        .semantics {
                            traversalIndex = 3f
                        },
                    properties = textField2,
                    enabled = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            item2.freeFocus()
                        }
                    )
                )

                key(formState.validate()) {
                    ButtonComponent(
                        modifier = Modifier
                            .semantics {
                                traversalIndex = 2f
                            },
                        properties = ButtonPrimary(
                            size = ButtonSize.Large,
                            text = "Test"
                        ),
                        enabled = formState.validate(),
                        onClick = {
                            textField2.setError("Error forzado")
                            item1.freeFocus()
                        }
                    )
                }
            }
        }
    }
}