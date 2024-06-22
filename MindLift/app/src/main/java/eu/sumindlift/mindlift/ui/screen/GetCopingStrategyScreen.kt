package eu.sumindlift.mindlift.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import eu.sumindlift.mindlift.ui.util.AddCopingStrategy
import eu.sumindlift.mindlift.ui.util.GetCopingStrategy
import eu.sumindlift.mindlift.ui.util.MindLiftTopBar
import kotlinx.coroutines.CoroutineScope

@Composable
fun GetCopingStrategyScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    navController: NavController,
    energyLevel: Int
) {
    Scaffold(
        modifier = modifier,
        topBar = { MindLiftTopBar(drawerState, coroutineScope) }
    ) { innerPadding ->
        GetCopingStrategy(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            energyLevel = energyLevel
        )
    }
}