package eu.sumindlift.mindlift.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import eu.sumindlift.mindlift.ui.util.CopingStrategyList
import eu.sumindlift.mindlift.ui.util.MindLiftTopBar
import kotlinx.coroutines.CoroutineScope

@Composable
fun CopingStrategyListScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    copingStrategyRepository: CopingStrategyRepository,
    navController: NavController
) {
    Scaffold(
        modifier = modifier,
        topBar = { MindLiftTopBar(drawerState, coroutineScope) }
    ) { innerPadding ->
        CopingStrategyList(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            copingStrategyRepository = copingStrategyRepository
        )
    }
}
