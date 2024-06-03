package ru.surok.clientserverappproject.data.models

import android.net.Uri

data class FreeSoundSound(
    val id: String,
    val name: String,
    val description: String,
    val duration: Int,
    val tags: List<String>,
    val download: String,
    val previews: Map<String, String>,
    val uri: String,
    val images: Map<String, String>,
    val username: String,
    val license: String
)
