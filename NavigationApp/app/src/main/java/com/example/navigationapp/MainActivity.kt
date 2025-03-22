package com.example.navigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigationapp.screens.DetailScreen
import com.example.navigationapp.screens.ListScreen
import com.example.navigationapp.screens.RootScreen
import com.example.navigationapp.ui.theme.NavigationAppTheme

// Enum để quản lý các màn hình
enum class AppScreen {
    ROOT,
    LIST,
    DETAIL
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    // Sử dụng state để quản lý màn hình hiện tại
    val (currentScreen, setCurrentScreen) = remember { mutableStateOf(AppScreen.ROOT) }
    // State để lưu ID của item được chọn
    val (selectedItemId, setSelectedItemId) = remember { mutableStateOf(0) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (currentScreen) {
            AppScreen.ROOT -> {
                RootScreen(
                    onNavigateToList = { setCurrentScreen(AppScreen.LIST) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            AppScreen.LIST -> {
                ListScreen(
                    onNavigateToDetail = { itemId ->
                        setSelectedItemId(itemId)
                        setCurrentScreen(AppScreen.DETAIL)
                    },
                    onNavigateToRoot = { setCurrentScreen(AppScreen.ROOT) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            AppScreen.DETAIL -> {
                DetailScreen(
                    itemId = selectedItemId,
                    onNavigateToList = { setCurrentScreen(AppScreen.LIST) },
                    onNavigateToRoot = { setCurrentScreen(AppScreen.ROOT) },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    NavigationAppTheme {
        MyApp()
    }
}