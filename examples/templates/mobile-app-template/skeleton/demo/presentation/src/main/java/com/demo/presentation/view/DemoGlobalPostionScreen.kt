package com.demo.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.core.presentation.ui.components.container.ContainerComponent
import com.core.presentation.ui.components.cta.button.ButtonComponent
import com.core.presentation.ui.components.cta.button.model.ButtonContentDescription
import com.core.presentation.ui.components.cta.button.model.ButtonPrimary
import com.core.presentation.ui.components.cta.button.model.ButtonSize
import com.core.presentation.ui.components.form.FormState
import com.core.presentation.ui.components.form.fields.multiselect.MultiSelectFieldState
import com.core.presentation.ui.components.form.fields.multiselect.MultiselectComponent
import com.core.presentation.ui.components.form.fields.select.checkbox.CheckboxComponent
import com.core.presentation.ui.components.form.fields.select.checkbox.CheckboxState
import com.core.presentation.ui.components.form.fields.select.toggle.ToggleComponent
import com.core.presentation.ui.components.form.fields.select.toggle.ToggleState
import com.core.presentation.ui.components.tab.TabComponent
import com.core.presentation.ui.components.tab.model.Tab
import com.core.presentation.ui.components.tab.model.TabTitle
import com.core.presentation.ui.theme.LocalDimensions
import com.core.presentation.ui.theme.LocalPalette
import com.core.presentation.ui.theme.LocalTypographies
import com.demo.presentation.viewModel.DemoContract

@Composable
fun DemoGlobalScreen(
    name: String,
    event: (DemoContract.Event) -> Unit
) {
    ContainerComponent(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        bottomBar = {
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                properties = ButtonPrimary(
                    size = ButtonSize.Large,
                    text = "Cerrar sesión",
                    contentDescription = ButtonContentDescription(
                        enabledText = "Cerrar sesión",
                        disabledText = null
                    )
                ),
                enabled = true,
                onClick = {
                    event(DemoContract.Event.OnLogoutClicked)
                }
            )
        }
    ) {
        val tabs = remember {
            listOf(
                Tab(
                    data = "Tab1",
                    title = TabTitle(
                        text = "CheckBox",
                        icon = null
                    ),
                    content = {
                        val formState = FormState(
                            fields = listOf(
                                CheckboxState(
                                    default = false
                                ),
                                CheckboxState(
                                    default = true
                                )
                            )
                        )

                        formState.fields.forEachIndexed { index, state ->
                            CheckboxComponent(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                state = state,
                                label = "CheckBox $index"
                            )
                        }
                    }
                ),
                Tab(
                    data = "Tab2",
                    title = TabTitle(
                        text = "Toggle",
                        icon = null
                    ),
                    content = {
                        val formState = FormState(
                            fields = listOf(
                                ToggleState(
                                    default = false
                                ),
                                ToggleState(
                                    default = true
                                )
                            )
                        )

                        formState.fields.forEachIndexed { index, state ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Toggle $index",
                                    color = LocalPalette.current.contentPalette.accent
                                )

                                ToggleComponent(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    state = state
                                )
                            }
                        }
                    }
                ),
                Tab(
                    data = "Tab3",
                    title = TabTitle(
                        text = "Multiselect",
                        icon = null
                    ),
                    content = {
                        MultiselectComponent(
                            modifier = Modifier
                                .fillMaxWidth(),
                            state = MultiSelectFieldState<String>(),
                            items = listOf(
                                "Item 1",
                                "Item 2",
                                "Item 3",
                                "Item 4",
                                "Item 5",
                                "Item 6",
                            ),
                            isEnabled = {
                                true
                            }
                        ) {
                            Column {
                                Icon(
                                    painter = painterResource(
                                        id = android.R.drawable.star_on
                                    ),
                                    contentDescription = null
                                )

                                Text(
                                    text = it,
                                    color = LocalPalette.current.contentPalette.mid,
                                    style = LocalTypographies.current.mid.lEmphasis
                                )
                            }
                        }
                    }
                )
            )
        }

        var selectedTab by remember {
            mutableStateOf(value = tabs.first())
        }

        Text(
            modifier = Modifier
                .padding(top = LocalDimensions.current.dp.dp32)
                .fillMaxWidth(),
            text = "Hola $name, bienvenido",
            style = LocalTypographies.current.high.lEmphasis,
            color = LocalPalette.current.contentPalette.accent,
            textAlign = TextAlign.Center
        )

        TabComponent(
            modifier = Modifier
                .fillMaxSize(),
            tabs = tabs,
            selectedTab = selectedTab,
            onTabSelected = {
                selectedTab = it
            }
        )
    }
}