package com.example.mytestapp.compose.ui.chapter2

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AppBarColumn
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FlexibleBottomAppBar
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.FloatingToolbarDefaults.ScreenOffset
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.LoadingIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.material3.VerticalFloatingToolbar
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.mytestapp.R

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun LoadingIndicatorTest() {
    Column (
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        // Default
        LoadingIndicator()

        // With 2 shapes only
        LoadingIndicator(
            polygons = LoadingIndicatorDefaults.IndeterminateIndicatorPolygons.take(3)
        )


        // Default
        ContainedLoadingIndicator()

        // Custom Container Color
        ContainedLoadingIndicator(
            containerColor = Color.Red
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview(showBackground = true)
fun FilledSplitButton() {
    var checked by remember { mutableStateOf(false) }
    SplitButtonLayout(
        leadingButton = {
            SplitButtonDefaults.LeadingButton(
                onClick = { /* */ },
            ) {
                Icon(
                    painterResource(R.drawable.ic_hide),
                    modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                    contentDescription = "",
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("My Button")
            }
        },
        trailingButton = {
            SplitButtonDefaults.TrailingButton(
                checked = checked,
                onCheckedChange = { checked = it },
            ) {
                Icon(
                    painterResource(R.drawable.ic_hide),
                    contentDescription = ""
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview(showBackground = true)
fun ElevatedSplitButton() {
    var checked by remember { mutableStateOf(false) }

    SplitButtonLayout(
        leadingButton = {
            SplitButtonDefaults.ElevatedLeadingButton(
                onClick = { /* */ },
            ) {
                Icon(
                    painterResource(R.drawable.ic_hide),
                    modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                    contentDescription = "",
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("My Button")
            }
        },
        trailingButton = {
            SplitButtonDefaults.ElevatedTrailingButton(
                checked = checked,
                onCheckedChange = { checked = it },
            ) {
                Icon(
                    painterResource(R.drawable.ic_hide),
                    contentDescription = ""
                )
            }
        })
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview(showBackground = true)
fun OutlinedSplitButton() {

    var checked by remember { mutableStateOf(false) }

    SplitButtonLayout(
        // Custom Spacing
        spacing = 8.dp,
        leadingButton = {
            SplitButtonDefaults.OutlinedLeadingButton(
                onClick = { /* */ },
            ) {
                Icon(
                    painterResource(R.drawable.ic_hide),
                    modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                    contentDescription = "",
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("My Button")
            }
        },
        trailingButton = {
            SplitButtonDefaults.OutlinedTrailingButton(
                checked = checked,
                onCheckedChange = { checked = it },
            ) {
                // Rotate the icon based on the check value
                val rotation: Float by
                animateFloatAsState(
                    targetValue = if (checked) 180f else 0f,
                    label = "Trailing Icon Rotation"
                )
                Icon(
                    painterResource(R.drawable.ic_hide),
                    modifier =
                        Modifier
                            .size(SplitButtonDefaults.TrailingIconSize)
                            .graphicsLayer {
                                this.rotationZ = rotation
                            },
                    contentDescription = ""
                )
            }
        })
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun FloatingActionButtonMenuTest() {
    val listState = rememberLazyListState()
    val fabVisible by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    Box {
        LazyColumn(state = listState) {
            for (index in 0 until 5) {
                item {
                    Text(
                        text = "Item - $index",
                        modifier = Modifier.fillMaxWidth().padding(24.dp)
                    )
                }
            }
        }

        val items =
            listOf(
                painterResource(R.drawable.ic_hide) to "test1",
                painterResource(R.drawable.ic_hide) to "test2",
                painterResource(R.drawable.ic_hide) to "test3"
            )

        var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }

        FloatingActionButtonMenu(
            modifier = Modifier.align(Alignment.BottomEnd),
            expanded = fabMenuExpanded,
            button = {
                ToggleFloatingActionButton(
                    modifier =
                        Modifier
                            .animateFloatingActionButton(
                                visible = fabVisible || fabMenuExpanded,
                                alignment = Alignment.BottomEnd
                            ),
                    checked = fabMenuExpanded,
                    // Large size
                    containerSize = ToggleFloatingActionButtonDefaults.containerSizeLarge(),
                    onCheckedChange = { fabMenuExpanded = !fabMenuExpanded }
                ) {
                    val imageVector = ImageVector.vectorResource(id = R.drawable.ic_hide)
                    Icon(
                        painter = rememberVectorPainter(imageVector),
                        contentDescription = null,
                        modifier = Modifier.animateIcon({ checkedProgress })
                    )
                }
            }
        ) {
            items.forEachIndexed { i, item ->
                FloatingActionButtonMenuItem(
                    onClick = { fabMenuExpanded = false },
                    icon = { Icon(item.first, contentDescription = null) },
                    text = { Text(text = item.second) },
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun ButtonGroupTest() {
    val numButtons = 10
    Column (modifier = Modifier.height(100.dp)){
        ButtonGroup(
            overflowIndicator = { menuState ->
                FilledIconButton(
                    onClick = {
                        if (menuState.isExpanded) {
                            menuState.dismiss()
                        } else {
                            menuState.show()
                        }
                    }
                ) {
                    Icon(
                        painterResource(R.drawable.ic_hide),
                        contentDescription = ""
                    )
                }
            }
        ) {
            for (i in 0 until numButtons) {
                clickableItem(onClick = {}, label = "$i")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview(showBackground = true)
fun SingleSelectConnectedButtonGroupTest() {
    val options = listOf("Work", "Restaurant", "Coffee")
    val unCheckedIcons =
        listOf(painterResource(R.drawable.ic_hide), painterResource(R.drawable.ic_hide), painterResource(R.drawable.ic_hide))
    val checkedIcons = listOf(painterResource(R.drawable.ic_hide), painterResource(R.drawable.ic_hide), painterResource(R.drawable.ic_hide))
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        Modifier.padding(horizontal = 8.dp),
    ) {

        options.forEachIndexed { index, label ->
            ToggleButton(
                checked = selectedIndex == index,
                onCheckedChange = { selectedIndex = index }
            ) {
                Icon(
                    if (selectedIndex == index) checkedIcons[index] else unCheckedIcons[index],
                    contentDescription = ""
                )
                Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
                Text(label)
            }
        }

        ToggleButton(
            checked = selectedIndex == 4,
            onCheckedChange = { selectedIndex = 4 }
        ) {
            Icon(
                painterResource(R.drawable.ic_hide),
                contentDescription = ""
            )
            Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
            Text("test")
        }
    }
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun MultiSelectConnectedButtonGroupTest() {
    val options = listOf("Work", "Restaurant", "Coffee")
    val unCheckedIcons =
        listOf(painterResource(R.drawable.ic_hide), painterResource(R.drawable.ic_hide), painterResource(R.drawable.ic_hide))
    val checkedIcons = listOf(painterResource(R.drawable.ic_hide), painterResource(R.drawable.ic_hide), painterResource(R.drawable.ic_hide))
    val checked = remember { mutableStateListOf(false, false, false) }

    Row(
        Modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween)
    ) {
        options.forEachIndexed { index, label ->
            ToggleButton(
                checked = checked[index],
                onCheckedChange = { checked[index] = it },
                // Custom shapes
                shapes =
                    when (index) {
                        0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        options.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                        else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    }
            ) {
                Icon(
                    if (checked[index]) checkedIcons[index] else unCheckedIcons[index],
                    contentDescription = ""
                )
                Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
                Text(label)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun OverflowingVerticalFloatingToolbarSample() {
    Scaffold(
        content = { innerPadding ->
            Box(Modifier.fillMaxSize().padding(innerPadding)) {
                // Content
                LazyColumn(
                    state = rememberLazyListState(),
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val list = (0..25).map { it.toString() }
                    items(count = list.size) {
                        Text(text = list[it])
                    }
                }

                // VerticalFloatingToolbar
                VerticalFloatingToolbar(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = -ScreenOffset),
                    expanded = true,
                    leadingContent = { },
                    trailingContent = {
                        AppBarColumn(
                            overflowIndicator = { menuState ->
                                IconButton(
                                    onClick = {
                                        if (menuState.isExpanded) {
                                            menuState.dismiss()
                                        } else {
                                            menuState.show()
                                        }
                                    }
                                ) {
                                    Icon(
                                        painterResource(R.drawable.ic_hide),
                                        contentDescription = ""
                                    )
                                }
                            }
                        ) {
                            clickableItem(
                                onClick = { /* */ },
                                icon = {
                                    Icon(
                                        painterResource(R.drawable.ic_hide),
                                        contentDescription = ""
                                    )
                                },
                                label = "Download"
                            )

                            clickableItem(
                                onClick = { /* */ },
                                icon = {
                                    Icon(
                                        painterResource(R.drawable.ic_hide),
                                        contentDescription = ""
                                    )
                                },
                                label = "Person"
                            )

                        }
                    },
                    content = {
                        FilledIconButton(
                            modifier = Modifier.height(64.dp),
                            onClick = { /* */ }
                        ) {
                            Icon(painterResource(R.drawable.ic_hide), contentDescription = "")
                        }
                    }
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun HorizontalFloatingToolbarWithFab() {
    var expanded by rememberSaveable { mutableStateOf(true) }
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()
    Scaffold { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                Modifier.verticalScroll(rememberScrollState())
            ) {
                Text(text = remember { LoremIpsum().values.first().take(800) })
            }

            HorizontalFloatingToolbar(
                expanded = expanded,
                floatingActionButton = {
                    FloatingToolbarDefaults.VibrantFloatingActionButton(
                        onClick = { /* */ },
                    ) {
                        Icon(painterResource(R.drawable.ic_hide), contentDescription = "")
                    }
                },
                modifier =
                    Modifier.align(Alignment.BottomEnd),
                colors = vibrantColors,
                content = {
                    IconButton(onClick = { }) {
                        Icon(painterResource(R.drawable.ic_hide), contentDescription = "")
                    }
                    IconButton(onClick = { /* */ }) {
                        Icon(painterResource(R.drawable.ic_hide), contentDescription = "")
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun FlexibleBottomAppBarEx() {
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    Scaffold(
        bottomBar = {
            FlexibleBottomAppBar(
                horizontalArrangement = Arrangement.SpaceEvenly,
                scrollBehavior = scrollBehavior,
                contentPadding = PaddingValues(horizontal = 0.dp),
                content = {
                    IconButton(onClick = { /* */ }) {
                        Icon(
                            painterResource(R.drawable.ic_hide),
                            contentDescription = ""
                        )
                    }

                    FilledIconButton(
                        modifier = Modifier.width(56.dp),
                        onClick = { /* */ }
                    ) {
                        Icon(painterResource(R.drawable.ic_hide), contentDescription = "")
                    }
                    IconButton(onClick = { /* */ }) {
                        Icon(painterResource(R.drawable.ic_hide), contentDescription = "")
                    }

                }
            )
        },
        content = { innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                val list = (0..250).map { it.toString() }
                items(count = list.size) {
                    Text(text = list[it], modifier = Modifier.fillMaxWidth())
                }
            }
        }
    )
}