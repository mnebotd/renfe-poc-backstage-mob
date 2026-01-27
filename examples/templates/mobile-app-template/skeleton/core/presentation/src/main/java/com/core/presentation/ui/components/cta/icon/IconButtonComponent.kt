package com.core.presentation.ui.components.cta.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.core.presentation.ui.components.cta.icon.model.IconButtonContentDescription
import com.core.presentation.ui.components.cta.icon.model.IconButtonLink
import com.core.presentation.ui.components.cta.icon.model.IconButtonPrimary
import com.core.presentation.ui.components.cta.icon.model.IconButtonProperties
import com.core.presentation.ui.components.cta.icon.model.IconButtonSecondary
import com.core.presentation.ui.components.cta.icon.model.IconButtonSize
import com.core.presentation.ui.components.cta.icon.model.IconButtonTertiary
import com.core.presentation.ui.theme.LocalDimensions
import com.core.presentation.ui.theme.Theme
import com.core.presentation.ui.utils.interaction.rememberInteractionState
import com.core.presentation.ui.utils.interaction.utils.state
import com.core.presentation.ui.utils.ripple.NoRippleComponent

/**
 * A composable function that creates a customizable icon button.
 *
 * This component displays an icon within a circular background and handles click interactions.
 * It supports different states (enabled/disabled) and allows customization of size, colors,
 * content description, and border.
 *
 * @param modifier The modifier to be applied to the button.
 * @param properties The [IconButtonProperties] that define the button's appearance,
 *                   including size, icon, content description and colors.
 * @param enabled Controls whether the button is enabled or disabled. When false,
 *                the button will be visually and functionally disabled.
 * @param onClick The callback that will be executed when the button is clicked. It is
 *                only invoked if the button is enabled.
 */
@Composable
fun IconButtonComponent(
    modifier: Modifier = Modifier,
    properties: IconButtonProperties,
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
        Box(
            modifier = modifier
                .size(
                    size = properties.size.size
                )
                .semantics(
                    mergeDescendants = true
                ) {

                    role = Role.Button
                    when (enabled) {
                        true -> {
                            contentDescription = properties.contentDescription.enabledText
                            onClick {
                                onClick()
                                true
                            }
                        }
                        false -> {
                            contentDescription = properties.contentDescription.disabledText ?: ""
                            disabled()
                        }
                    }
                }
                .background(
                    color = state.value.containerColor,
                    shape = CircleShape
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
                .then(
                    state.value.border?.let {
                        Modifier.border(
                            border = it,
                            shape = CircleShape
                        )
                    } ?: Modifier
                )
        ) {
            Icon(
                modifier = Modifier
                    .align(
                        alignment = Alignment.Center
                    )
                    .padding(
                        all = LocalDimensions.current.dp.dp8
                    )
                    .fillMaxSize(),
                painter = painterResource(
                    id = properties.icon,
                ),
                tint = state.value.contentColor,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun IconButtonPreview() {
    Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding(),
            verticalArrangement = Arrangement.spacedBy(
                space = LocalDimensions.current.dp.dp8
            )
        ) {
            IconButtonComponent(
                modifier = Modifier,
                properties = IconButtonPrimary(
                    size = IconButtonSize.Large,
                    contentDescription = IconButtonContentDescription(
                        enabledText = "test",
                        disabledText = "test"
                    ),
                    icon = android.R.drawable.star_on
                ),
                enabled = true,
                onClick = {}
            )

            IconButtonComponent(
                modifier = Modifier,
                properties = IconButtonSecondary(
                    size = IconButtonSize.Medium,
                    contentDescription = IconButtonContentDescription(
                        enabledText = "test",
                        disabledText = "test"
                    ),
                    icon = android.R.drawable.star_on
                ),
                enabled = true,
                onClick = {}
            )

            IconButtonComponent(
                modifier = Modifier,
                properties = IconButtonTertiary(
                    size = IconButtonSize.Small,
                    contentDescription = IconButtonContentDescription(
                        enabledText = "test",
                        disabledText ="test"
                    ),
                    icon = android.R.drawable.star_on
                ),
                enabled = true,
                onClick = {}
            )

            IconButtonComponent(
                modifier = Modifier,
                properties = IconButtonLink(
                    size = IconButtonSize.Large,
                    contentDescription = IconButtonContentDescription(
                        enabledText = "test",
                        disabledText = null
                    ),
                    icon = android.R.drawable.star_on
                ),
                enabled = true,
                onClick = {}
            )

            IconButtonComponent(
                modifier = Modifier,
                properties = IconButtonPrimary(
                    size = IconButtonSize.Large,
                    contentDescription = IconButtonContentDescription(
                        enabledText = "test",
                        disabledText = "test"
                    ),
                    icon = android.R.drawable.star_on
                ),
                enabled = false,
                onClick = {}
            )
        }
    }
}