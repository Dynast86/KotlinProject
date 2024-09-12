package navigation

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.login.login
import ui.next.Next

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            login(modifier = Modifier.safeContentPadding()) {
                navController.navigate(route = "next")
            }
        }
        composable("next") {
            Next(modifier = Modifier.safeContentPadding())
        }
    }
}