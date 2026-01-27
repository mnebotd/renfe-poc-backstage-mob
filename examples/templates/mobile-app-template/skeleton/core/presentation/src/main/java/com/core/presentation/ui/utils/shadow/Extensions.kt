package com.core.presentation.ui.utils.shadow

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.core.presentation.ui.utils.shadow.model.ShadowFocus
import com.core.presentation.ui.utils.shadow.model.base.Shadow

/**
 * An extension function for [Modifier] that adds a drop shadow effect to the composable.
 *
 * This function allows you to easily apply a drop shadow to any composable by using the
 * `dropShadow` modifier. The shadow's appearance is defined by the provided [Shadow] object,
 * which includes properties like color, blur, spread, offset, and shape.
 *
 * @param shadow The [Shadow] object that defines the properties of the drop shadow.
 *               This includes the shadow's color, blur radius, spread, offset, and shape.
 *
 * @return A [Modifier] that draws the specified drop shadow behind the composable.
 *
 * @see Shadow
 */
fun Modifier.stateShadow(
    shadow: Shadow
) = this.drawBehind {
    shadow.shadows.forEach {
        with(it) {
            val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
            val shadowOutline = shadow.shape.createOutline(shadowSize, layoutDirection, this@drawBehind)

            val paint = Paint()
            paint.color = color

            if (blur.toPx() > 0) {
                paint.asFrameworkPaint().apply {
                    maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
                }
            }

            drawIntoCanvas { canvas ->
                canvas.save()
                canvas.translate(offset.x.toPx(), offset.y.toPx())
                canvas.drawOutline(shadowOutline, paint)
                canvas.restore()
            }
        }
    }
}

/**
 * Applies a shadow effect to a composable when it gains focus.
 *
 * This modifier adds a dynamic shadow that appears only when the composable
 * is in a focused state. When the composable loses focus, the shadow disappears.
 * It utilizes the `onFocusChanged` modifier to track the focus state and the
 * `focusable` modifier to make the composable focusable.
 *
 * @param shape The shape of the shadow to be applied. This determines the
 *              outline of the shadow. For instance, you could use
 *              `RoundedCornerShape(16.dp)` for rounded shadows.
 * @param backgroundColor The background color of the composable. This is used
 *                        by the `ShadowFocus` to blend the shadow correctly.
 * @param interactionSource Optional [MutableInteractionSource] to track focus
 *                          interactions. If not provided, a new one will be
 *                          remembered.
 * @param defaultShadow Optional [Shadow] to apply when the composable is not
 *                      focused. If `null`, no shadow will be applied when not focused.
 * @return A [Modifier] that can be applied to a composable to enable focus-based
 *         shadow effects.
 */
@Composable
fun Modifier.focusShadow(
    shape: Shape,
    backgroundColor: Color,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    defaultShadow: Shadow? = null,
    isActionable: Boolean = false
): Modifier {
    val isFocused = remember {
        mutableStateOf(false)
    }

    return this
        .onFocusChanged {
            isFocused.value = it.isFocused
        }
        .then(
            other = if (!isActionable) {
                Modifier.focusable(
                    interactionSource = interactionSource
                )
            } else {
                Modifier
            }
        )
        .then(
            other = if (isFocused.value) {
                Modifier.stateShadow(
                    shadow = ShadowFocus(
                        shape = shape,
                        backgroundColor = backgroundColor
                    )
                )
            } else {
                defaultShadow?.let {
                    Modifier.stateShadow(
                        shadow = it
                    )
                } ?: Modifier
            }
        )
}