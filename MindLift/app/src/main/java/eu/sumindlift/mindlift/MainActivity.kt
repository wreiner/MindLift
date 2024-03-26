package eu.sumindlift.mindlift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.sumindlift.mindlift.ui.theme.MindLiftTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindLiftTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(text = getString(R.string.app_name))
                            }
                        )
                    }
                ) { innerPadding ->
                    EnergyLevelChooser(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EnergyLevelChooser(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        EnergyCard(cardText = R.string.low_energy, batteryLevel = 0)
        EnergyCard(cardText = R.string.half_energy, batteryLevel = 50)
        EnergyCard(cardText = R.string.high_energy, batteryLevel = 100)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnergyCard(
    @StringRes cardText: Int,
    batteryLevel: Int,
    modifier: Modifier = Modifier
) {
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .padding(8.dp)
    ) {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val icon = when (batteryLevel) {
                in 0..25 -> R.drawable.battery_25
                in 26..95 -> R.drawable.battery_50
                in 96..100 -> R.drawable.battery_100
                else -> R.drawable.battery_25
            }
            val color = when (batteryLevel) {
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
                contentDescription = "Battery Level",
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
        EnergyLevelChooser()
    }
}