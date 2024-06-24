package eu.sumindlift.mindlift.ui.util

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import eu.sumindlift.mindlift.ui.viewmodel.AddCopingStrategyViewModel
import eu.sumindlift.mindlift.ui.viewmodel.GetCopingStrategyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetCopingStrategy(
    modifier: Modifier = Modifier,
    viewModel: GetCopingStrategyViewModel = hiltViewModel(),
    navController: NavController,
    energyLevel: Int
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        viewModel.getCopingStrategy(energyLevel)
        val strategy by viewModel.strategy.collectAsState()

        // If the strategy is empty, set the default values
        if (strategy.title.isEmpty() && strategy.description.isEmpty()) {
            viewModel.setStrategy(
                CopingStrategy(
                    id = 424242424,
                    title = stringResource(id = R.string.example_coping_strategy_title),
                    description = stringResource(id = R.string.example_coping_strategy_description),
                    energyLevel = EnergyLevel.LOW.getId()
                )
            )
        }

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
    GetCopingStrategy(navController = navController, energyLevel = EnergyLevel.LOW.getId())
}