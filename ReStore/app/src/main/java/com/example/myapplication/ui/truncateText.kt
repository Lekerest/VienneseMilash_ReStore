package com.example.myapplication.ui

import androidx.compose.runtime.Composable

@Composable
fun truncateText(text: String, maxLength: Int): String {
    return if (text.length > maxLength) {
        text.substring(0, maxLength) + "..."
    } else {
        text
    }
}