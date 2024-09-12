package ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_visibility
import kotlinproject.composeapp.generated.resources.ic_visibility_off
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun login(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = LoginViewModel(),
    onClick: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    when (uiState.value) {
        is LoginUiState.Success -> {
            onClick()
        }

        is LoginUiState.Error -> {
            val message = (uiState.value as LoginUiState.Error).message
            scope.launch {
                snackbarHostState.showSnackbar(message)
            }
        }

        LoginUiState.Loading -> Unit
    }

    Scaffold(modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Text(
                text = "Welcome Screen",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp),
                style = MaterialTheme.typography.displayMedium,
            )

            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = id, onValueChange = { id = it },
                    label = { Text("사번") },
                    singleLine = true,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("비밀번호") },
                    singleLine = true,
                    keyboardActions = KeyboardActions(onDone = {
                        viewModel.getLoginInfo(id, password)
                        focusManager.clearFocus()
                    }),
                    visualTransformation = if (showPassword) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        if (showPassword) {
                            IconButton(onClick = { showPassword = false }) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_visibility),
                                    contentDescription = "hide_password"
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { showPassword = true }) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_visibility_off),
                                    contentDescription = "show_password"
                                )
                            }
                        }
                    },
                    modifier = Modifier.focusRequester(focusRequester)
                )
                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        viewModel.getLoginInfo(id, password)
                        focusManager.clearFocus()
                    }) {
                    Text("Login")
                }
            }
        }
    }
}