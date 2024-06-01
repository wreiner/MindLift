package eu.sumindlift.mindlift.ui.navigation

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object AddCopingStrategy : Screens("addCopingStrategy")
}