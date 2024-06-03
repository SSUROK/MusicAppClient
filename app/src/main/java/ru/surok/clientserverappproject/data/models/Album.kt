package ru.surok.clientserverappproject.data.models

data class Album (
    val id: String,
    val title: String,
    val artist_id: String,
    val cover_path: String
)

data class AlbumDTO(
    val title: String,
    val artist: Artist,
    val cover_path: String
)

data class AlbumResponse (
    val result: Album
)