package eu.sumindlift.mindlift.ui.navigation

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object AddCopingStrategy : Screens("addCopingStrategy")
    data object GetCopingStrategy : Screens("getCopingStrategy")
    data object ListCopingStrategies : Screens("listCopingStrategies")
    data object EnergyLevelProgress : Screens("energyLevelProgress")
}