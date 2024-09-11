package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_check
import org.example.project.ui.AppViewModel
import org.example.project.ui.list.ReportUiState
import org.example.project.ui.list.content.ListItemContent
import org.example.project.ui.login.login
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var clicked by remember { mutableStateOf(0) }
        val nav = rememberNavController()

        NavHost(
            navController = nav,
            startDestination = "login"
        ) {
            composable("login") {
                login(modifier = Modifier.safeContentPadding()) {
                    nav.navigate(route = "next")
                }
            }
            composable("next") {
                Text("NEXT")
//                viewModel: AppViewModel = AppViewModel(),
//
//                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//                val showContent by viewModel.showContent.collectAsStateWithLifecycle()
//                Scaffold(
//                    topBar = {
//                        TopAppBar(title = {
//                            Text(
//                                "SalesReport", maxLines = 1, overflow = TextOverflow.Ellipsis
//                            )
//                        })
//                    },
//                    floatingActionButton = {
//                        FloatingActionButton(
//                            modifier = Modifier.padding(16.dp),
//                            onClick = { }) {
//                            Icon(
//                                painterResource(Res.drawable.ic_check),
//                                contentDescription = "저장하기"
//                            )
//                        }
//                    },
//                    containerColor = MaterialTheme.colorScheme.surface,
//                ) { innerPadding ->
//                    Box(
//                        modifier = Modifier.padding(innerPadding)
//                            .fillMaxSize()
//                    ) {
//                        when (uiState) {
//                            is ReportUiState.Success -> {
//                                val data = (uiState as ReportUiState.Success).data
//                                LazyColumn(
//                                    modifier = Modifier
//                                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
//                                        .fillMaxWidth()
//                                        .background(
//                                            color = Color.White,
//                                            shape = RoundedCornerShape(24.dp)
//                                        ),
//                                    horizontalAlignment = Alignment.CenterHorizontally,
//                                    verticalArrangement = Arrangement.spacedBy(4.dp),
//                                    contentPadding = PaddingValues(16.dp),
//                                ) {
//                                    items(data.keys.size) {
//                                        val index = data.keys.elementAt(it)
//                                        if (data[index] == null) return@items
//
//                                        ListItemContent(key = index, item = data[index]!!)
//                                    }
//                                }
//                            }
//
//                            ReportUiState.Error -> Unit
//                            ReportUiState.Loading -> Unit
//                        }
//                    }
//                }
            }
        }
    }
}