package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.ApiService
import com.example.myapplication.ui.AppItem
import com.example.myapplication.ui.Product

@Composable
fun ProductListScreen(navController: NavController) {
    val productsState = produceState<List<Product>?>(initialValue = null) {
        value = try {
            ApiService.getApps()
        }
        catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    if (productsState.value == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text ="Загрузка...",
                fontSize = 25.sp
            )

        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .padding(bottom = 16.dp, top = 8.dp)
        ) {
            item {
                Text(
                    text = "Магазин приложений",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(bottom = 16.dp, start = 8.dp)
                )
            }
            items(productsState.value!!) {  product ->
                AppItem(product) {
                    navController.navigate("app/${product.id}")
                }
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}