package com.core.presentation.ui.utils

import android.content.res.Configuration
import android.content.res.Resources

fun isSystemDarkMode(): Boolean {
    val uiMode = Resources.getSystem().configuration.uiMode
    return (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
}