package navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home_page")
    data object Login : Screen("login")
}