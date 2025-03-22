package com.example.navigationapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationapp.components.CircleNavigationButton
import com.example.navigationapp.components.SquareNavigationButton
import com.example.navigationapp.model.DataProvider
import com.example.navigationapp.ui.theme.NavigationAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToRoot: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Tạo danh sách items
    val items = remember { DataProvider.generateItems(1000000) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "LazyColumn",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF2196F3)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    Box(modifier = Modifier.padding(start = 8.dp)) {
                        CircleNavigationButton(
                            onClick = onNavigateToRoot,
                            backgroundColor = Color(0xFF2196F3)
                        )
                    }
                }
            )
        },
        modifier = modifier,
        containerColor = Color.White
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            items(
                count = items.size,
                key = { index -> items[index].id }
            ) { index ->
                val item = items[index]
                val itemNumber = if (index < 5) {
                    "${(index + 1).toString().padStart(2, '0')} | ${item.content}"
                } else if (index == items.size - 1) {
                    "1.000.000 | ${item.content}"
                } else {
                    "${(index + 1).toString().padStart(2, '0')} | ${item.content}"
                }

                ListItem(
                    text = itemNumber,
                    onItemClick = { onNavigateToDetail(item.id) }
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ListItem(
    text: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFCCE5FF) // Màu xanh nhạt
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            SquareNavigationButton(
                onClick = onItemClick,
                backgroundColor = Color.Black,

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    NavigationAppTheme {
        ListScreen(
            onNavigateToDetail = {},
            onNavigateToRoot = {}
        )
    }
}