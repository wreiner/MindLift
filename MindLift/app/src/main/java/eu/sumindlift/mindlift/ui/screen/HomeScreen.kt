package eu.sumindlift.mindlift.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.sumindlift.mindlift.ui.util.EnergyLevelChooser
import eu.sumindlift.mindlift.ui.util.MindLiftTopBar
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    onNavigateToGetCopingStrategy: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { MindLiftTopBar(drawerState, coroutineScope) }
    ) { innerPadding ->
        EnergyLevelChooser(modifier = Modifier.padding(innerPadding))
    }
}