package eu.sumindlift.mindlift.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import eu.sumindlift.mindlift.ui.screen.AddCopingStrategyScreen
import eu.sumindlift.mindlift.ui.screen.CopingStrategyListScreen
import eu.sumindlift.mindlift.ui.screen.GetCopingStrategyScreen
import eu.sumindlift.mindlift.ui.screen.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class DrawerMenu(
    val icon: ImageVector,
    val title: String,
    val route: String
)

val menus = arrayOf(
    DrawerMenu(Icons.Filled.Home, "Home", Screens.Home.route),
    DrawerMenu(Icons.Filled.Add, "Add Coping Strategy", Screens.AddCopingStrategy.route),
    DrawerMenu(Icons.Filled.List, "List Coping Strategies", Screens.ListCopingStrategies.route)
)

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
                label = { Text(text = it.title) },
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
                AddCopingStrategyScreen(drawerState = drawerState, coroutineScope = coroutineScope)
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
                    copingStrategyRepository = copingStrategyRepository)
            }
        }
    }
}
