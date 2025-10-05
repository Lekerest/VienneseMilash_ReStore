package com.example.myapplication.ui.theme

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

fun downloadApk(context: Context, url: String) {
    val request = DownloadManager.Request(Uri.parse(url))
        .setTitle("Скачивание приложения")
        .setDescription("Загрузка APK...")
        .setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "app.apk")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

    val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    manager.enqueue(request)
}