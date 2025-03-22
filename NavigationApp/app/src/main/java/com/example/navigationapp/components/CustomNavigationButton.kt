package com.example.navigationapp.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigationapp.ui.theme.NavigationAppTheme

@Composable
fun CircleNavigationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF2196F3),
    chevronColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .size(36.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(18.dp)) {
            // Vẽ chevron trái (<) với góc rộng hơn
            val strokeWidth = 3.dp.toPx()

            // Phần trên của chevron
            drawLine(
                color = chevronColor,
                start = Offset(size.width * 0.8f, size.height * 0.2f),
                end = Offset(size.width * 0.2f, size.height * 0.5f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )

            // Phần dưới của chevron
            drawLine(
                color = chevronColor,
                start = Offset(size.width * 0.2f, size.height * 0.5f),
                end = Offset(size.width * 0.8f, size.height * 0.8f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
fun SquareNavigationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    chevronColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(12.dp)) {
            // Vẽ chevron phải (>) với góc rộng hơn
            val strokeWidth = 2.5.dp.toPx()

            // Phần trên của chevron
            drawLine(
                color = chevronColor,
                start = Offset(size.width * 0.2f, size.height * 0.2f),
                end = Offset(size.width * 0.8f, size.height * 0.5f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )

            // Phần dưới của chevron
            drawLine(
                color = chevronColor,
                start = Offset(size.width * 0.8f, size.height * 0.5f),
                end = Offset(size.width * 0.2f, size.height * 0.8f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview
@Composable
fun CircleNavigationButtonPreview() {
    NavigationAppTheme {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
        ) {
            CircleNavigationButton(onClick = {})
        }
    }
}

@Preview
@Composable
fun SquareNavigationButtonPreview() {
    NavigationAppTheme {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
        ) {
            SquareNavigationButton(onClick = {})
        }
    }
}