package eu.sumindlift.mindlift.ui.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.sumindlift.mindlift.R
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import eu.sumindlift.mindlift.data.entity.EnergyLevel
import eu.sumindlift.mindlift.ui.navigation.Screens
import eu.sumindlift.mindlift.ui.viewmodel.CopingStrategyListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CopingStrategyList(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CopingStrategyListViewModel = hiltViewModel(),
) {
    val copingStrategiesList by viewModel.copingStrategies.collectAsState()
    LaunchedEffect(copingStrategiesList) {
        viewModel.loadAllCopingStrategies()
    }

    if (copingStrategiesList.isEmpty()) {
        CopingStrategyListEmptyPlaceholder(navController, modifier)
    } else {
        LazyColumn(
            modifier = modifier
        ) {
            items(copingStrategiesList) { copingStrategy ->
                CopingStrategyListCard(
                    navController = navController,
                    copingStrategy = copingStrategy,
                    modifier = Modifier.padding(2.dp),
                    onDelete = { strategy ->
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.delete(strategy)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CopingStrategyListEmptyPlaceholder(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.coping_strategy_list_empty_placeholder_text)
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            onClick = {
                navController.navigate(Screens.AddCopingStrategy.route)
            }
        ) {
            Text(text = stringResource(id = R.string.coping_strategy_list_empty_placeholder_add_button_text))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CopingStrategyListCard(
    navController: NavController,
    copingStrategy: CopingStrategy,
    modifier: Modifier = Modifier,
    onDelete: (CopingStrategy) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .combinedClickable(
                onClick = {
                    navController.navigate("addCopingStrategyScreen/${copingStrategy.id}")
                },
                onLongClick = {
                    showMenu = true
                }
            )
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text (
                    text = copingStrategy.title,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.headlineSmall
                )

                Text (
                    text = copingStrategy.description,
                    modifier = Modifier.padding(4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text (
                text = stringResource(
                    id = EnergyLevel.fromId(copingStrategy.energyLevel).getTitleResourceId()
                ),
                modifier = Modifier
                    .padding(start = 4.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    onDelete(copingStrategy)
                    showMenu = false
                },
                text = { Text("Delete") }
            )
        }
    }
}

@Preview
@Composable
private fun CopingStrategyListCardPreview() {
    val navController = rememberNavController()
    CopingStrategyListCard(
        navController = navController,
        copingStrategy = CopingStrategy(
            id = 42,
            title = "Some Title",
            description = "Some Description\nwith a newline",
            energyLevel = EnergyLevel.LOW.getId()
        ),
        onDelete = {}
    )
}