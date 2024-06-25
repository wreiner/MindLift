package eu.sumindlift.mindlift.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import eu.sumindlift.mindlift.R
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import eu.sumindlift.mindlift.ui.screen.AddCopingStrategyScreen
import eu.sumindlift.mindlift.ui.screen.CopingStrategyListScreen
import eu.sumindlift.mindlift.ui.screen.EnergyLevelProgressScreen
import eu.sumindlift.mindlift.ui.screen.GetCopingStrategyScreen
import eu.sumindlift.mindlift.ui.screen.HomeScreen
import eu.sumindlift.mindlift.ui.screen.InspirationalQuotesScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class DrawerMenu(
    val icon: ImageVector,
    val title: Int,
    val route: String
)

val menus = arrayOf(
    DrawerMenu(Icons.Filled.Home, R.string.drawer_home, Screens.Home.route),
    DrawerMenu(Icons.Filled.Add, R.string.drawer_add_strategy, Screens.AddCopingStrategy.route),
    DrawerMenu(Icons.Filled.List, R.string.drawer_list_strategies, Screens.ListCopingStrategies.route),
    DrawerMenu(Icons.Filled.CheckCircle, R.string.drawer_energy_level_progress, Screens.EnergyLevelProgress.route),
    DrawerMenu(Icons.Filled.Star, R.string.drawer_inspirational_quotes, Screens.InspirationalQuotes.route)
)

@Composable
fun getLocalizedString(@StringRes id: Int): String {
    val context = LocalContext.current
    return context.getString(id)
}

@Composable
private fun DrawerContent(
    menus: Array<DrawerMenu>,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        menus.forEach {
            NavigationDrawerItem(
                label = { Text(text = stringResource(id = it.title)) },
                icon = { Icon(imageVector = it.icon, contentDescription = null) },
                selected = false,
                onClick = {
                    onMenuClick(it.route)
                }
            )
        }
    }
}

@Composable
fun MindLiftNavHost(
    copingStrategyRepository: CopingStrategyRepository,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(menus) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route)
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = modifier
        ) {
            composable(route = Screens.Home.route) {
                HomeScreen(
                    drawerState = drawerState,
                    coroutineScope = coroutineScope,
                    navController = navController
                )
            }

            composable(route = Screens.AddCopingStrategy.route) {
                AddCopingStrategyScreen(
                    drawerState = drawerState,
                    coroutineScope = coroutineScope,
                    copingStrategyRepository = copingStrategyRepository,
                    copingStrategyId = null
                )
            }

            composable(
                route = "addCopingStrategyScreen/{copingStrategyId}",
                arguments = listOf(
                    navArgument("copingStrategyId") { type = NavType.IntType }
                )
            ) { navBackStackEntry ->
                val copingStrategyId = navBackStackEntry.arguments?.getInt("copingStrategyId")

                AddCopingStrategyScreen(
                    drawerState = drawerState,
                    coroutineScope = coroutineScope,
                    copingStrategyRepository = copingStrategyRepository,
                    copingStrategyId = copingStrategyId
                )
            }

            composable(
                route = "getCopingStrategy/{energyLevel}",
                arguments = listOf(
                    navArgument("energyLevel") { type = NavType.IntType }
                )
            ) { navBackStackEntry ->
                val energyLevel = navBackStackEntry.arguments?.getInt("energyLevel") ?: 1

                GetCopingStrategyScreen(
                    drawerState = drawerState,
                    coroutineScope = coroutineScope,
                    navController = navController,
                    energyLevel = energyLevel
                )
            }

            composable(route = Screens.ListCopingStrategies.route) {
                CopingStrategyListScreen(
                    drawerState = drawerState,
                    coroutineScope = coroutineScope,
                    copingStrategyRepository = copingStrategyRepository,
                    navController = navController
                )
            }

            composable(route = Screens.EnergyLevelProgress.route) {
                EnergyLevelProgressScreen(
                    drawerState = drawerState,
                    coroutineScope = coroutineScope,
                    navController = navController
                )
            }

            composable(route = Screens.InspirationalQuotes.route) {
                InspirationalQuotesScreen(
                    drawerState = drawerState,
                    coroutineScope = coroutineScope,
                    navController = navController
                )
            }
        }
    }
}
