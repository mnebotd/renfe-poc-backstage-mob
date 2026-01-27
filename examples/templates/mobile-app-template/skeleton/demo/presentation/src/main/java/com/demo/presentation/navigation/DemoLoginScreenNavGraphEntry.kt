package com.demo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.presentation.navigation3.manager.INavigationGraphManager
import com.core.presentation.navigation3.model.entry.INavigationEntry
import com.demo.presentation.model.DemoGlobalPositionScreenNavDestination
import com.demo.presentation.model.DemoLoginScreenNavDestination
import com.demo.presentation.model.DemoNotAvailableScreenNavDestination
import com.demo.presentation.view.DemoLoginScreen
import com.demo.presentation.viewModel.DemoContract
import com.demo.presentation.viewModel.DemoViewModel
import dagger.Lazy
import javax.inject.Inject

class DemoLoginScreenNavGraphEntry @Inject constructor(
    override val navigator: Lazy<INavigationGraphManager>
) : INavigationEntry<DemoLoginScreenNavDestination, DemoViewModel>() {

    @Composable
    override fun Render(
        params: DemoLoginScreenNavDestination,
        viewModel: DemoViewModel
    ) {
        val state = viewModel.viewState.collectAsStateWithLifecycle().value
        val effectFlow = viewModel.effect

        LaunchedEffect(effectFlow) {
            effectFlow.collect { effect ->
                when (effect) {
                    DemoContract.Effect.ShowFeatureNotAvailable -> {
                        navigator.get().goToDestination(
                            DemoNotAvailableScreenNavDestination
                        )
                    }

                    DemoContract.Effect.GoGlobalPosition -> {
                        navigator.get().goToDestination(
                            DemoGlobalPositionScreenNavDestination(
                                name = state.user.getData() ?: ""
                            )
                        )
                    }
                    else -> {}
                }
            }
        }

        DemoLoginScreen(
            state = state
        ) {
            viewModel.handleUiEvents(
                event = it
            )
        }
    }
}