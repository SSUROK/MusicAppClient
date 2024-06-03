package ru.surok.clientserverappproject.data.models

data class Artist(
    val id: String,
    val title: String
)

data class ArtistResponse (
    val result: Artist
)
