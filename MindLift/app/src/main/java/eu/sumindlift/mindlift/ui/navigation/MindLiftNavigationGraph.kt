package eu.sumindlift.mindlift.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import eu.sumindlift.mindlift.ui.screen.AddCopingStrategyScreen
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
    DrawerMenu(Icons.Filled.Add, "Add Coping Strategy", Screens.AddCopingStrategy.route)
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
                HomeScreen(drawerState = drawerState, coroutineScope = coroutineScope)
            }g
        }
    }
}