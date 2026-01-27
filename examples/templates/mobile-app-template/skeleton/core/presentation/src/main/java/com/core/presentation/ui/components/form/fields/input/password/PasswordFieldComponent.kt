package com.core.presentation.ui.components.form.fields.input.password

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component3
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import com.core.presentation.R
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.core.presentation.ui.components.cta.button.ButtonComponent
import com.core.presentation.ui.components.cta.button.model.ButtonContentDescription
import com.core.presentation.ui.components.cta.button.model.ButtonPrimary
import com.core.presentation.ui.components.cta.button.model.ButtonSize
import com.core.presentation.ui.components.cta.icon.IconButtonComponent
import com.core.presentation.ui.components.cta.icon.model.IconButtonContentDescription
import com.core.presentation.ui.components.cta.icon.model.IconButtonLink
import com.core.presentation.ui.components.cta.icon.model.IconButtonSize
import com.core.presentation.ui.components.form.FormState
import com.core.presentation.ui.components.form.fields.input.password.model.PasswordFieldProperties
import com.core.presentation.ui.components.form.validators.Validators
import com.core.presentation.ui.theme.LocalDimensions
import com.core.presentation.ui.theme.LocalPalette
import com.core.presentation.ui.theme.LocalTypographies
import com.core.presentation.ui.theme.Theme
import com.core.presentation.ui.utils.interaction.rememberInteractionState
import com.core.presentation.ui.utils.interaction.utils.state
import com.core.presentation.ui.utils.shadow.stateShadow

/**
 * A composable function that creates a password field component.
 *
 * This component is designed for secure text input, specifically for passwords.
 * It includes features such as password masking, error handling, helper text,
 * custom keyboard options, and a toggle for showing/hiding the password.
 *
 * @param modifier The modifier to be applied to the password field.
 * @param properties The properties that define the behavior and appearance of the password field.
 *                   This includes the label, helper text, error state, and change callback.
 * @param enabled Controls whether the field is enabled for user interaction.
 * @param showErrorMessage Whether or not error message should be shown.
 * @param keyboardOptions Options for the keyboard, such as the keyboard type and IME action.
 * @param keyboardActions Actions to be performed when keyboard actions are triggered.
 *
 * @see PasswordFieldProperties
 * @see TextField
 * @see IconButtonComponent
 */
@Composable
fun PasswordFieldComponent(
    modifier: Modifier = Modifier,
    properties: PasswordFieldProperties,
    enabled: Boolean,
    showErrorMessage: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
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

    val passwordVisibility = remember {
        mutableStateOf(value = false)
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
            interactionSource = interactionSource,
            isError = isError.value,
            shape = RoundedCornerShape(
                size = LocalDimensions.current.dp.dp8,
            ),
            label = {
                Text(
                    modifier = Modifier
                        .semantics {
                            contentDescription = buildString {
                                if (passwordVisibility.value) {
                                    append(properties.label)
                                }

                                append(properties.contentDescription)
                                properties.helperText?.let {
                                    append(it)
                                }
                            }
                        },
                    text = properties.label,
                    style = LocalTypographies.current.mid.s
                )
            },
            placeholder = null,
            leadingIcon = null,
            trailingIcon = {
                val showPasswordContentDescription = stringResource(
                    id = R.string.form_password_show
                )

                IconButtonComponent(
                    modifier = Modifier
                        .clearAndSetSemantics {
                            role = Role.Button
                            contentDescription = showPasswordContentDescription
                            toggleableState = when (passwordVisibility.value) {
                                true -> ToggleableState.On
                                false -> ToggleableState.Off
                            }

                            onClick {
                                passwordVisibility.value = !passwordVisibility.value

                                true
                            }
                        },
                    properties = IconButtonLink(
                        size = IconButtonSize.Medium,
                        contentDescription = IconButtonContentDescription(
                            enabledText = "",
                            disabledText = null
                        ),
                        icon = if (passwordVisibility.value) {
                            R.drawable.ic_preview_hide
                        } else {
                            R.drawable.ic_preview_show
                        }
                    ),
                    enabled = enabled,
                    onClick = {
                        passwordVisibility.value = !passwordVisibility.value
                    }
                )
            },
            prefix = null,
            suffix = null,
            supportingText = null,
            visualTransformation = if (passwordVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = keyboardOptions.copy(
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Password
            ),
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
            textStyle = if (passwordVisibility.value) {
                LocalTypographies.current.mid.sEmphasis
            } else {
                LocalTypographies.current.mid.sEmphasis.copy(
                    letterSpacing = LocalDimensions.current.sp.sp4
                )
            },
            onValueChange = {
                properties.change(update = it)
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
private fun PasswordFieldPreview() {
    val passwordField1 = PasswordFieldProperties(
        validators = listOf(
            Validators.Required(
                message = "Requerido"
            )
        ),
        label = "Label text",
        helperText = "Helper text",
        contentDescription = null
    )

    val passwordField2 = PasswordFieldProperties(
        validators = listOf(
            Validators.MinSize(limit = 2, message = "Error limite")
        ),
        label = "Contraseña",
        helperText = null,
        contentDescription = ", Introduce tu contraseña, obligatorio"
    )

    val formState = FormState(
        fields = listOf(
            passwordField1,
            passwordField2
        )
    )

    Theme {
        val (item1, item2, item3) = remember { FocusRequester.createRefs() }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
                .padding(
                    all = LocalDimensions.current.dp.dp16
                ),
            verticalArrangement = Arrangement.spacedBy(
                space = LocalDimensions.current.dp.dp16
            )
        ) {
            PasswordFieldComponent(
                modifier = Modifier
                    .focusRequester(item1)
                    .onPreviewKeyEvent {
                        if (it.key == Key.Enter) {
                            item1.freeFocus()
                            item2.requestFocus()

                            true
                        } else {
                            false
                        }
                    },
                properties = passwordField1,
                enabled = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        item1.freeFocus()
                        item2.requestFocus()
                    }
                )
            )

            PasswordFieldComponent(
                modifier = Modifier
                    .focusRequester(item2)
                    .onPreviewKeyEvent {
                        if (it.key == Key.Enter) {
                            item2.freeFocus()
                            item3.requestFocus()

                            true
                        } else {
                            false
                        }
                    },
                properties = passwordField2,
                enabled = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        item2.freeFocus()
                        item3.requestFocus()
                    }
                )
            )

            key(formState.validate()) {
                ButtonComponent(
                    modifier = Modifier
                        .focusRequester(item3)
                        .focusable(),
                    properties = ButtonPrimary(
                        size = ButtonSize.Large,
                        text = "Test",
                        contentDescription = ButtonContentDescription(
                            enabledText = "Acceder",
                            disabledText = "Acceder, completa los campos obligatorios"
                        )
                    ),
                    enabled = formState.validate(),
                    onClick = {
                        passwordField2.forceError(
                            errorMessage = null,
                            clearValue = true
                        )

                        item1.freeFocus()
                    }
                )
            }
        }
    }
}