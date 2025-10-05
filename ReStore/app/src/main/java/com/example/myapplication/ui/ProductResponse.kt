package com.example.myapplication.ui

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val total_apps: Int? = null,
    val apps: List<Product> = emptyList()
)
