package eu.sumindlift.mindlift.ui.util

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eu.sumindlift.mindlift.R
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import eu.sumindlift.mindlift.data.entity.EnergyLevel
import eu.sumindlift.mindlift.ui.navigation.Screens
import eu.sumindlift.mindlift.ui.theme.MindLiftTheme
import eu.sumindlift.mindlift.ui.viewmodel.EnergyLevelViewModel
import eu.sumindlift.mindlift.ui.viewmodel.GetCopingStrategyViewModel

@Composable
fun EnergyLevelChooser(
    modifier: Modifier = Modifier,
    energyLevelViewModel: EnergyLevelViewModel = hiltViewModel(),
    navController: NavController,
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            text = "How do you feel?",
            style = MaterialTheme.typography.headlineSmall
        )
        EnergyLevel.entries.forEach {
            EnergyCard(
                cardText = it.getTitleResourceId(),
                energyLevel = it.getBatteryLevel(),
                onClick = {
                    energyLevelViewModel.newEnergyLevelRecord(it.getId())
                    navController.navigate("getCopingStrategy/${it.getId()}")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnergyCard(
    @StringRes cardText: Int,
    energyLevel: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier.padding(8.dp),
        onClick = onClick
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val icon = when (energyLevel) {
                in 0..25 -> R.drawable.battery_25
                in 26..95 -> R.drawable.battery_50
                in 96..100 -> R.drawable.battery_100
                else -> R.drawable.battery_25
            }
            val color = when (energyLevel) {
                in 0..20 -> Color.Red
                in 41..60 -> Color.Yellow
                in 96..100 -> Color.Green
                else -> Color.Gray
            }

            Text(
                text = stringResource(id = cardText),
                modifier = Modifier
                    .wrapContentWidth(),
                style = MaterialTheme.typography.headlineMedium
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = "Energy Level",
                tint = color,
                modifier = Modifier
                    .height(48.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnergyLevelChooserPreview() {
    MindLiftTheme {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "How do you feel?",
                style = MaterialTheme.typography.headlineSmall
            )
            EnergyCard(
                cardText = R.string.low_energy,
                energyLevel = 0,
                onClick = { })
            EnergyCard(
                cardText = R.string.half_energy,
                energyLevel = 50,
                onClick = { })
            EnergyCard(
                cardText = R.string.high_energy,
                energyLevel = 100,
                onClick = { })
        }
    }
}