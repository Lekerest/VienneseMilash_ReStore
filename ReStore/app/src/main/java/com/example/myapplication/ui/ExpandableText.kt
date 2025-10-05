package com.example.myapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableText(text: String, collapsedMaxLines: Int = 3) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else collapsedMaxLines
        )
        Text(
            text = if (expanded) "Свернуть" else "Читать полностью",
            color = androidx.compose.ui.graphics.Color.Blue,
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(top = 4.dp)
        )
    }
}