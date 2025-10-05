package com.example.myapplication

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun OnBoard(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Основной контент в центре
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.rustore_color_logo),
                contentDescription = "RuStore Logo",
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "Добро пожаловать в RuStore!",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.size(60.dp))
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()

            // Анимация масштаба (сжатие при нажатии)
            val scale by animateFloatAsState(targetValue = if (isPressed) 0.95f else 1f)

            Button(
                onClick = {navController.navigate("main")},
                interactionSource = interactionSource,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressed) Color(0xFF0148F6) else Color(0xFF0077FF),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(250.dp)
                    .height(60.dp)
                    .scale(scale)
            ) {
                Text(
                    text = "Войти",
                    fontSize = 20.sp
                )
            }
        }
        Text(
            text = "ver 1.0",
            fontSize = 25.sp,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter)
        )
    }
}