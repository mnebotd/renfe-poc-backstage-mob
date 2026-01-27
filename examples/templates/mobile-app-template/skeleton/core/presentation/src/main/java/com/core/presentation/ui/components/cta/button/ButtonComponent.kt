package com.core.presentation.ui.components.cta.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.presentation.ui.components.cta.button.model.ButtonContentDescription
import com.core.presentation.ui.components.cta.button.model.ButtonIcon
import com.core.presentation.ui.components.cta.button.model.ButtonLink
import com.core.presentation.ui.components.cta.button.model.ButtonPrimary
import com.core.presentation.ui.components.cta.button.model.ButtonProperties
import com.core.presentation.ui.components.cta.button.model.ButtonSecondary
import com.core.presentation.ui.components.cta.button.model.ButtonSize
import com.core.presentation.ui.components.cta.button.model.ButtonTertiary
import com.core.presentation.ui.components.cta.button.model.base.ButtonSelectedState
import com.core.presentation.ui.theme.LocalDimensions
import com.core.presentation.ui.theme.LocalPalette
import com.core.presentation.ui.theme.Theme
import com.core.presentation.ui.utils.ProvideContentColorTextStyle
import com.core.presentation.ui.utils.interaction.rememberInteractionState
import com.core.presentation.ui.utils.interaction.utils.state
import com.core.presentation.ui.utils.ripple.NoRippleComponent
import com.core.presentation.ui.utils.shadow.stateShadow

/**
 * A composable function that renders a button based on the provided [ButtonProperties].
 *
 * This function handles the different button types and their states (enabled/disabled, pressed, hovered).
 * It uses a [MutableInteractionSource] to track user interactions and a derived state to reflect
 * the button's current visual state.
 *
 * @param modifier The modifier to be applied to the button.
 * @param properties The [ButtonProperties] that define the button's appearance and behavior.
 *        This determines if the button is a link or a default button.
 * @param enabled Whether the button is enabled. If `false`, the button will be visually
 *        disabled and will not respond to clicks.
 * @param onClick The callback to be invoked when the button is clicked.
 *
 * @see ButtonProperties
 * @see ButtonLink
 * @see ButtonLinkComponent
 * @see ButtonDefaultComponent
 * @see NoRippleComponent
 * @see rememberInteractionState
 * @see MutableInteractionSource
 */
@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    properties: ButtonProperties,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val interactionState = rememberInteractionState(
        enabled = true,
        interactionSource = interactionSource
    )

    val state = remember(enabled) {
        derivedStateOf {
            interactionState.value.state(
                enabled = enabled,
                properties = properties
            )
        }
    }

    NoRippleComponent {
        when (properties) {
            is ButtonLink -> ButtonLinkComponent(
                modifier = modifier,
                properties = properties,
                enabled = enabled,
                interactionSource = interactionSource,
                state = state,
                onClick = onClick
            )

            else -> ButtonDefaultComponent(
                modifier = modifier,
                properties = properties,
                enabled = enabled,
                interactionSource = interactionSource,
                state = state,
                onClick = onClick
            )
        }
    }
}

/**
 *  ButtonDefaultComponent is a composable function that creates a default button component
 *  based on the provided properties and state.
 *
 * @param modifier Modifier for styling and positioning the button.
 * @param properties [ButtonProperties] containing the configuration for the button,
 *        like text, icons, content description and size.
 * @param enabled Boolean indicating if the button is enabled or disabled.
 * @param interactionSource [MutableInteractionSource] to handle interactions like pressed, hovered, etc.
 * @param state [State]<[ButtonSelectedState]> representing the current visual state of the button,
 *        including colors, borders, and text decoration.
 * @param onClick Lambda function executed when the button is clicked.
 */
@Composable
private fun ButtonDefaultComponent(
    modifier: Modifier = Modifier,
    properties: ButtonProperties,
    enabled: Boolean,
    interactionSource: MutableInteractionSource,
    state: State<ButtonSelectedState>,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .sizeIn(
                minWidth = properties.size.minSize.width,
                minHeight = properties.size.minSize.height
            )
            .semantics {
                role = Role.Button
                when (enabled) {
                    true -> {
                        contentDescription =
                            properties.contentDescription?.enabledText ?: properties.text
                        onClick {
                            onClick()
                            true
                        }
                    }

                    false -> {
                        contentDescription =
                            properties.contentDescription?.disabledText ?: properties.text
                        disabled()
                    }
                }
            }
            .then(
                other = if (enabled) {
                    state.value.enabledState.shadow?.let {
                        Modifier.stateShadow(
                            shadow = it
                        )
                    } ?: Modifier
                } else {
                    state.value.nonEnabledState.shadow?.let {
                        Modifier.stateShadow(
                            shadow = it
                        )
                    } ?: Modifier
                }
            )
            .background(
                color = if (enabled) state.value.enabledState.containerColor else state.value.nonEnabledState.containerColor,
                shape = CircleShape,
            )
            .then(
                if (enabled) {
                    state.value.enabledState.borderStroke?.let {
                        Modifier.border(
                            border = it,
                            shape = CircleShape
                        )
                    } ?: Modifier
                } else {
                    state.value.nonEnabledState.borderStroke?.let {
                        Modifier.border(
                            border = it,
                            shape = CircleShape
                        )
                    } ?: Modifier
                }
            )
            .clickable(
                enabled = true,
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (enabled) {
                        onClick()
                    }
                }
            )
    ) {
        ProvideContentColorTextStyle(
            contentColor = if (enabled) state.value.enabledState.contentColor else state.value.nonEnabledState.contentColor,
            textStyle = properties.size.textStyle,
            content = {
                Row(
                    modifier = Modifier
                        .semantics {
                            hideFromAccessibility()
                        }
                        .align(
                            alignment = Alignment.Center
                        )
                        .width(
                            intrinsicSize = IntrinsicSize.Max
                        )
                        .padding(
                            all = LocalDimensions.current.dp.dp8
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        properties.leadingIcon?.let {
                            Icon(
                                modifier = Modifier,
                                painter = painterResource(
                                    id = it.icon
                                ),
                                contentDescription = null,
                                tint = it.color ?: LocalContentColor.current
                            )
                        }

                        Text(
                            modifier = Modifier
                                .weight(
                                    weight = 1F
                                )
                                .padding(
                                    horizontal = LocalDimensions.current.dp.dp16
                                ),
                            text = properties.text.uppercase(),
                            textDecoration = if (enabled) state.value.enabledState.textDecoration else state.value.nonEnabledState.textDecoration,
                            textAlign = TextAlign.Center
                        )

                        properties.trailingIcon?.let {
                            Icon(
                                painter = painterResource(
                                    id = it.icon
                                ),
                                contentDescription = null,
                                tint = it.color ?: LocalContentColor.current
                            )
                        }
                    }
                )
            }
        )
    }
}

/**
 * A composable function that renders a link-style button with customizable properties.
 *
 * This component creates a button with a background, text, and optional leading/trailing icons.
 * It supports different states (enabled/disabled), text styling, and custom content descriptions
 * for accessibility.
 *
 * @param modifier The modifier to be applied to the button's container.
 * @param properties The properties that define the button's appearance and behavior, including text,
 *                   icons, content descriptions, and size.
 * @param enabled Whether the button is enabled or disabled. Disabled buttons cannot be clicked
 *                and have adjusted semantics.
 * @param interactionSource [MutableInteractionSource] to handle interactions like pressed, hovered, etc.
 * @param state [State]<[ButtonSelectedState]> representing the current visual state of the button,
 *        including colors, borders, and text decoration.
 * @param onClick Lambda function executed when the button is clicked.
 */
@Composable
private fun ButtonLinkComponent(
    modifier: Modifier = Modifier,
    properties: ButtonProperties,
    enabled: Boolean,
    interactionSource: MutableInteractionSource,
    state: State<ButtonSelectedState>,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .sizeIn(
                minWidth = properties.size.minSize.width,
                minHeight = properties.size.minSize.height
            )
            .semantics {
                role = Role.Button
                when (enabled) {
                    true -> {
                        contentDescription =
                            properties.contentDescription?.enabledText ?: properties.text
                        onClick {
                            onClick()
                            true
                        }
                    }

                    false -> {
                        contentDescription =
                            properties.contentDescription?.disabledText ?: properties.text
                        disabled()
                    }
                }
            }
            .then(
                other = state.value.enabledState.shadow?.let {
                    Modifier.stateShadow(
                        shadow = it
                    )
                } ?: Modifier
            )
            .background(
                color = state.value.enabledState.containerColor,
                shape = CircleShape,
            )
            .clickable(
                enabled = true,
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (enabled) {
                        onClick()
                    }
                }
            )
    ) {
        ProvideContentColorTextStyle(
            contentColor = state.value.enabledState.contentColor,
            textStyle = properties.size.textStyle,
            content = {
                Row(
                    modifier = Modifier
                        .semantics {
                            hideFromAccessibility()
                        }
                        .align(
                            alignment = Alignment.Center
                        )
                        .width(
                            intrinsicSize = IntrinsicSize.Max
                        )
                        .padding(
                            vertical = LocalDimensions.current.dp.dp8
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = LocalDimensions.current.dp.dp16
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        properties.leadingIcon?.let {
                            Icon(
                                modifier = Modifier,
                                painter = painterResource(
                                    id = it.icon
                                ),
                                contentDescription = null,
                                tint = it.color ?: LocalContentColor.current
                            )
                        }
                        Text(
                            modifier = Modifier
                                .weight(
                                    weight = 1F
                                ),
                            text = properties.text.uppercase(),
                            textDecoration = state.value.enabledState.textDecoration,
                            textAlign = TextAlign.Center
                        )

                        properties.trailingIcon?.let {
                            Icon(
                                painter = painterResource(
                                    id = it.icon
                                ),
                                contentDescription = null,
                                tint = it.color ?: LocalContentColor.current
                            )
                        }
                    }
                )
            }
        )
    }
}

@Preview
@Composable
private fun ButtonPreview() {
    Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
                .verticalScroll(
                    state = rememberScrollState()
                ),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp
            )
        ) {
            ButtonComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        height = 80.dp
                    ),
                properties = ButtonPrimary(
                    size = ButtonSize.Large,
                    text = "Enabled Modifier.fillMaxWidth()",
                    contentDescription = ButtonContentDescription(
                        enabledText = "Enabled",
                        disabledText = null
                    ),
                    leadingIcon = ButtonIcon(
                        icon = android.R.drawable.star_on
                    ),

                    ),
                enabled = true,
                onClick = {}
            )

            ButtonComponent(
                modifier = Modifier
                    .width(
                        width = 254.dp
                    )
                    .height(
                        height = 80.dp
                    ),
                properties = ButtonSecondary(
                    size = ButtonSize.Large,
                    text = "Enabled Modifier.width()",
                    leadingIcon = ButtonIcon(
                        icon =  android.R.drawable.star_on
                    )
                ),
                enabled = true,
                onClick = {}
            )

            ButtonComponent(
                properties = ButtonPrimary(
                    size = ButtonSize.Medium,
                    text = "Enabled"
                ),
                enabled = true,
                onClick = {}
            )

            ButtonComponent(
                properties = ButtonTertiary(
                    size = ButtonSize.Small,
                    text = "Enabled icon",
                    leadingIcon = ButtonIcon(
                        icon =  android.R.drawable.star_on,
                        color = LocalPalette.current.contentPalette.contrast
                    ),
                    trailingIcon = ButtonIcon(
                        icon = android.R.drawable.star_on
                    )
                ),
                enabled = true,
                onClick = {}
            )

            ButtonComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        height = 80.dp
                    ),
                properties = ButtonSecondary(
                    size = ButtonSize.Large,
                    text = "Disabled Modifier.fillMaxWidth()",
                    leadingIcon = ButtonIcon(
                        icon = android.R.drawable.star_on
                    )
                ),
                enabled = false,
                onClick = {}
            )

            ButtonComponent(
                modifier = Modifier
                    .width(
                        width = 254.dp
                    )
                    .height(
                        height = 80.dp
                    ),
                properties = ButtonTertiary(
                    size = ButtonSize.Large,
                    text = "Disabled Modifier.width()",
                    trailingIcon = ButtonIcon(
                        icon = android.R.drawable.star_on,
                        color = LocalPalette.current.contentPalette.contrast
                    )
                ),
                enabled = false,
                onClick = {}
            )

            ButtonComponent(
                properties = ButtonPrimary(
                    size = ButtonSize.Medium,
                    text = "Disabled"
                ),
                enabled = false,
                onClick = {}
            )

            ButtonComponent(
                properties = ButtonPrimary(
                    size = ButtonSize.Small,
                    text = "Disabled icon",
                    leadingIcon = ButtonIcon(
                        icon =  android.R.drawable.star_on
                    ),
                    trailingIcon = ButtonIcon(
                        icon = android.R.drawable.star_on
                    )
                ),
                enabled = false,
                onClick = {}
            )

            ButtonComponent(
                properties = ButtonLink(
                    size = ButtonSize.Small,
                    text = "Link enabled icon",
                    containerColor = LocalPalette.current.contentPalette.contrast
                ),
                enabled = true,
                onClick = {}
            )
        }
    }
}