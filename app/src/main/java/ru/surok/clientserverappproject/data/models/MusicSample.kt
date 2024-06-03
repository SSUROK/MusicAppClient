package ru.surok.clientserverappproject.data.models

import java.io.InputStream

data class MusicSample (
    val id: Int,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Int,
    val fileId: Int,
    var songStream: InputStream?,
    var coverStream: ByteArray?
)