package eu.sumindlift.mindlift.ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import eu.sumindlift.mindlift.data.entity.EnergyLevel
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository

@Composable
fun CopingStrategiesList(
    navController: NavController,
    copingStrategyRepository: CopingStrategyRepository,
    modifier: Modifier = Modifier
) {
    val copingStrategiesList by copingStrategyRepository.allCopingStrategies.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = modifier
    ) {
        items(copingStrategiesList) { copingStrategy ->
            CopingStrategyListCard(
                navController = navController,
                copingStrategy = copingStrategy,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}

@Composable
fun CopingStrategyListCard(
    navController: NavController,
    copingStrategy: CopingStrategy,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
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
            energyLevel = 1
        )
    )
}