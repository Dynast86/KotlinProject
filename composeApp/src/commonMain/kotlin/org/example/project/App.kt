package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.example.project.ui.AppViewModel

@Composable
@Preview
fun App(
    viewModel: AppViewModel = viewModel { AppViewModel() },
    navController: NavHostController = rememberNavController()
) {
    MaterialTheme {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val showContent by viewModel.showContent.collectAsStateWithLifecycle()

        Scaffold(
            modifier = Modifier.safeDrawingPadding(),
            topBar = {
                TopAppBar(title = { Text("Top app bar") })
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.height(80.dp)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                    contentColor = Color.Gray,
                    cutoutShape = CircleShape,
                ) {

//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        textAlign = TextAlign.Center,
//                        text = "Bottom app bar",
//                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.size(72.dp),
                    onClick = { }) {
                    Icon(
                        painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = "Add"
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    viewModel.changeContentState()
                    viewModel.setClickCount()
                }) {
                    Text("Click me! $uiState")
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
            }
        }
    }
}