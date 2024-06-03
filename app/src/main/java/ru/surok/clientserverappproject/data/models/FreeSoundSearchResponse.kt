package ru.surok.clientserverappproject.data.models

data class FreeSoundSearchResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<FreeSoundSound>
)