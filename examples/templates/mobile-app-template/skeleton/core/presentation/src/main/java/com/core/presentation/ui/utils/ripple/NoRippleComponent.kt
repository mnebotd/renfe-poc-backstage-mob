package com.core.presentation.ui.utils.ripple

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * A composable function that disables the ripple effect for its content.
 *
 * This function provides a way to remove the default ripple effect that is typically
 * displayed when interactive components are pressed or clicked. It achieves this by
 * providing a custom [RippleConfiguration] with all ripple alphas set to 0.
 *
 * @param content The composable content for which the ripple effect should be disabled.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoRippleComponent(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        value = LocalRippleConfiguration provides RippleConfiguration(
            rippleAlpha = RippleAlpha(
                draggedAlpha = 0f,
                focusedAlpha = 0f,
                hoveredAlpha = 0f,
                pressedAlpha = 0f
            )
        )
    ) {
        content()
    }
}