package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.example.project.ui.AppViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    viewModel: AppViewModel = viewModel { AppViewModel() },
    navController: NavHostController = rememberNavController()
) {
    MaterialTheme {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val showContent by viewModel.showContent.collectAsStateWithLifecycle()
        var clicked by remember { mutableStateOf(0) }

        Scaffold(
            modifier = Modifier.safeDrawingPadding(),
            topBar = {
                TopAppBar(title = { Text("Top app bar") })
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.height(80.dp)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                    contentColor = Color.White,
                    backgroundColor = Color.Red,
                    cutoutShape = CircleShape,
                ) {
                    BottomNavigationItem(
                        modifier = Modifier.weight(1f),
                        selected = clicked == 0,
                        icon = {},
                        label = { Text("Home", fontSize = 16.sp) },
                        onClick = { clicked = 0 }
                    )
                    Spacer(modifier = Modifier.width(88.dp))
                    BottomNavigationItem(
                        modifier = Modifier.weight(1f),
                        selected = clicked == 2,
                        icon = {},
                        label = { Text("More", fontSize = 16.sp) },
                        onClick = { clicked = 2 }
                    )
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