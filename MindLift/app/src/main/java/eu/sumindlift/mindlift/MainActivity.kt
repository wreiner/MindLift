package eu.sumindlift.mindlift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import eu.sumindlift.mindlift.ui.navigation.MindLiftNavHost
import eu.sumindlift.mindlift.ui.theme.MindLiftTheme
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var copingStrategyRepository: CopingStrategyRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindLiftTheme {
                MyMindLiftApp(copingStrategyRepository)
            }
        }
    }
}

@Composable
fun MyMindLiftApp(
    copingStrategyRepository: CopingStrategyRepository,
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    MindLiftNavHost(
        copingStrategyRepository = copingStrategyRepository,
        navController = navController,
        drawerState = drawerState,
        coroutineScope = coroutineScope
    )
}