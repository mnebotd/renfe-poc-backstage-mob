package com.demo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.presentation.navigation3.manager.INavigationGraphManager
import com.core.presentation.navigation3.model.entry.INavigationEntry
import com.demo.presentation.model.DemoGlobalPositionScreenNavDestination
import com.demo.presentation.view.DemoGlobalScreen
import com.demo.presentation.viewModel.DemoContract
import com.demo.presentation.viewModel.DemoViewModel
import dagger.Lazy
import javax.inject.Inject

class DemoGlobalPositionScreenNavGraphEntry @Inject constructor(
    override val navigator: Lazy<INavigationGraphManager>
) : INavigationEntry<DemoGlobalPositionScreenNavDestination, DemoViewModel>() {

    @Composable
    override fun Render(
        params: DemoGlobalPositionScreenNavDestination,
        viewModel: DemoViewModel
    ) {
        val state = viewModel.viewState.collectAsStateWithLifecycle().value
        val effectFlow = viewModel.effect

        LaunchedEffect(effectFlow) {
            effectFlow.collect { effect ->
                when (effect) {
                    DemoContract.Effect.Logout ->
                        navigator.get().goBack()

                    else -> {}
                }
            }
        }

        DemoGlobalScreen(
            name = params.name
        ) {
            viewModel.handleUiEvents(
                event = it
            )
        }
    }
}