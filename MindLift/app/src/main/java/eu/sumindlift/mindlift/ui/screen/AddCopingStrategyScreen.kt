package eu.sumindlift.mindlift.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import eu.sumindlift.mindlift.ui.util.AddCopingStrategy
import eu.sumindlift.mindlift.ui.util.MindLiftTopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun AddCopingStrategyScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    copingStrategyRepository: CopingStrategyRepository,
    copingStrategyId: Int?
) {
    Log.d("AddCopingStrategyScreen", "got passed in ${copingStrategyId}}")

    val copingStrategy by (
            copingStrategyId?.let {
                copingStrategyRepository.getById(it)
            } ?: flowOf(null)
        ).collectAsState(initial = null)

    Log.d("AddCopingStrategyScreen", "selected a coping strat? ${copingStrategy}}")

    Scaffold(
        modifier = modifier,
        topBar = { MindLiftTopBar(drawerState, coroutineScope) }
    ) { innerPadding ->
        if (copingStrategy != null) {
            Log.d("AddCopingStrategyScreen", "an existing copingStrat is passed in")
            AddCopingStrategy(
                modifier = Modifier.padding(innerPadding),
                initialCopingStrategy = copingStrategy,
                onSave = { updatedCopingStrategy ->
                    var success = false
                    coroutineScope.launch {
                        success = copingStrategyRepository.update(updatedCopingStrategy)
                    }
                    success
                }
            )
        } else {
            Log.d("AddCopingStrategyScreen", "we need to create a new copingstrat")
            AddCopingStrategy(
                modifier = Modifier.padding(innerPadding),
                onSave = { newCopingStrategy ->
                    var success = false
                    coroutineScope.launch {
                        success = copingStrategyRepository.insert(newCopingStrategy)
                    }
                    success
                }
            )
        }
    }
}