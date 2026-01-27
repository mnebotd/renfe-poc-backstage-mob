package com.demo.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.presentation.navigation3.manager.INavigationGraphManager
import com.core.presentation.navigation3.model.entry.type.INavigationEntryBottomSheet
import com.core.presentation.ui.theme.LocalDimensions
import com.core.presentation.ui.theme.LocalPalette
import com.core.presentation.ui.theme.LocalTypographies
import com.demo.presentation.model.DemoNotAvailableScreenNavDestination
import com.demo.presentation.viewModel.DemoViewModel
import dagger.Lazy
import javax.inject.Inject

class DemoNotAvailableScreenNavEntry @Inject constructor(
    override val navigator: Lazy<INavigationGraphManager>
) : INavigationEntryBottomSheet<DemoNotAvailableScreenNavDestination, DemoViewModel>() {

//    override val dialogProperties: DialogProperties
//        get() = DialogProperties()

    @OptIn(ExperimentalMaterial3Api::class)
    override val modalBottomSheetProperties: ModalBottomSheetProperties
        get() = ModalBottomSheetProperties()

    @Composable
    override fun Render(
        params: DemoNotAvailableScreenNavDestination,
        viewModel: DemoViewModel
    ) {
        val state = viewModel.viewState.collectAsStateWithLifecycle().value

        Text(
            modifier = Modifier
                .padding(
                    all = LocalDimensions.current.dp.dp16
                ),
            text = "Función no disponible en la demo",
            style = LocalTypographies.current.high.lEmphasis,
            color = LocalPalette.current.contentPalette.accent,
            textAlign = TextAlign.Center
        )
    }
}