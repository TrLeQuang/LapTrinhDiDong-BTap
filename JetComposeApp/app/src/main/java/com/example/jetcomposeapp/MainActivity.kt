package com.example.jetcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetcomposeapp.ui.theme.JetComposeAppTheme
import com.example.jetcomposeapp.ui.screens.WelcomeScreen
import com.example.jetcomposeapp.ui.screens.ComponentListScreen
import com.example.jetcomposeapp.ui.screens.TextDetailScreen

// Enum để quản lý các màn hình
enum class AppScreen {
    WELCOME,
    COMPONENT_LIST,
    TEXT_DETAIL
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetComposeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {
    // State để theo dõi màn hình hiện tại
    var currentScreen by remember { mutableStateOf(AppScreen.WELCOME) }

    // Chuyển đổi giữa các màn hình
    when (currentScreen) {
        AppScreen.WELCOME -> WelcomeScreen(
            onReadyClick = { currentScreen = AppScreen.COMPONENT_LIST }
        )
        AppScreen.COMPONENT_LIST -> ComponentListScreen(
            onBackClick = { currentScreen = AppScreen.WELCOME },
            onTextClick = { currentScreen = AppScreen.TEXT_DETAIL }
        )
        AppScreen.TEXT_DETAIL -> TextDetailScreen(
            onBackClick = { currentScreen = AppScreen.COMPONENT_LIST }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    JetComposeAppTheme {
        WelcomeScreen(onReadyClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentListScreenPreview() {
    JetComposeAppTheme {
        ComponentListScreen(onBackClick = {}, onTextClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun TextDetailScreenPreview() {
    JetComposeAppTheme {
        TextDetailScreen(onBackClick = {})
    }
}