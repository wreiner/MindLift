package eu.sumindlift.mindlift.ui.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import eu.sumindlift.mindlift.R
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import eu.sumindlift.mindlift.data.entity.EnergyLevel
import eu.sumindlift.mindlift.ui.viewmodel.CopingStrategySuggestionViewModel

@Composable
fun CopingStrategySuggestion(
    modifier: Modifier = Modifier,
    viewModel: CopingStrategySuggestionViewModel = hiltViewModel(),
    navController: NavController,
    energyLevel: Int
) {
    val defaultCopingStrategy = CopingStrategy(
        id = 424242424,
        title = stringResource(id = R.string.example_coping_strategy_title),
        description = stringResource(id = R.string.example_coping_strategy_description),
        energyLevel = EnergyLevel.LOW.getId()
    )

    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        LaunchedEffect(Unit) {
            viewModel.loadCopingStrategyOrElseDefault(energyLevel, defaultCopingStrategy)
        }
        val strategy by viewModel.strategy.collectAsState()

        Text(
            fontWeight = FontWeight.Bold,
            text = "Your Coping Strategy",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = strategy.title, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Description: " + strategy.description)
    }
}

@Preview(showBackground = true)
@Composable
fun GetCopingStrategyPreview() {
    val navController = rememberNavController()
    CopingStrategySuggestion(navController = navController, energyLevel = EnergyLevel.LOW.getId())
}