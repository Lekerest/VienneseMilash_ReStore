package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.ui.ApiService
import com.example.myapplication.ui.AppScreenshots
import com.example.myapplication.ui.ExpandableText
import com.example.myapplication.ui.Product
import com.example.myapplication.ui.theme.downloadApk
import com.example.myapplication.ui.truncateText

@Composable
fun App_Screen(appId: Int?, navController: NavController) {
    val context = LocalContext.current
    val products = produceState<List<Product>?>(initialValue = null) {
        value = try {
            ApiService.getApps()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    val product = products.value?.find { it.id == appId }

    if (product == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Загрузка", fontSize = 20.sp)
        }
    } else {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Назад"
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = product.title,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.size(50.dp))
            Row() {
                AsyncImage(
                    model = product.icon_url,
                    contentDescription = product.title,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.size(20.dp))
                Column {
                    Text(product.title, fontSize = 30.sp, color = Color.Black)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(truncateText(product.developer, 5), fontSize = 14.sp, color = Color.LightGray)
                        Text(", ", fontSize = 14.sp, color = Color.LightGray)
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(truncateText(product.category, 5), fontSize = 14.sp, color = Color.LightGray)
                        Text(", ", fontSize = 14.sp, color = Color.LightGray)
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(product.age_rating, fontSize = 14.sp, color = Color.LightGray)
                    }
                    Spacer(modifier = Modifier.size(2.dp))
                    Button(
                        onClick = {
                            product.apk_url?.let { url ->
                                downloadApk(context, url)
                            }},
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
            Spacer(modifier = Modifier.height(16.dp))
            ExpandableText(product.description)
            AppScreenshots(product.screenshots)
        }

    }
}