package com.example.myapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.ui.theme.downloadApk

@Composable
fun AppItem(product: Product, onClick: (Product) -> Unit) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clickable {onClick(product)}
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = product.icon_url,
                contentDescription = product.title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = truncateText(product.title, 13),
                    fontSize = 18.sp
                )
                Text(
                    text = "Оценка 5.0",
                    fontSize = 12.sp
                )
            }
        }
        Button(
            onClick = {
                product.apk_url?.let { url ->
                    downloadApk(context, url)
                }
            },
            modifier = Modifier.align(Alignment.CenterEnd),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0077FF),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Скачать",
                fontSize = 16.sp
            )
        }
    }
}