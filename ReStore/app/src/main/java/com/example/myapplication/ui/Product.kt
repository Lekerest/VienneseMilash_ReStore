package com.example.myapplication.ui

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val icon_url: String,
    val developer: String,
    val category: String,
    val age_rating: String,
    val description: String,
    val screenshots: List<String>,
    val apk_url: String? = null
)