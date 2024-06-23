package eu.sumindlift.mindlift.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import eu.sumindlift.mindlift.ui.util.AddCopingStrategy
import eu.sumindlift.mindlift.ui.util.CopingStrategiesList
import eu.sumindlift.mindlift.ui.util.MindLiftTopBar
import kotlinx.coroutines.CoroutineScope

@Composable
fun CopingStrategyListScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    copingStrategyRepository: CopingStrategyRepository
) {
    Scaffold(
        modifier = modifier,
        topBar = { MindLiftTopBar(drawerState, coroutineScope) }
    ) { innerPadding ->
        CopingStrategiesList(
            modifier = Modifier.padding(innerPadding),
            navController = rememberNavController(),
            copingStrategyRepository = copingStrategyRepository
        )
    }
}
