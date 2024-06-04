package ru.surok.clientserverappproject.data.models

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import ru.surok.clientserverappproject.data.repos.ServerRepo
import java.io.InputStream

data class Music (
    val id: String,
    val title: String,
    val artist_id: List<String>,
    val albumn_id: List<String>,
    val duration: Int,
    val filePath: String
){
    fun toMusicDTO(): MusicDTO {
        return MusicDTO(
            title = title,
            duration  = duration,
            filePath = filePath
        )
    }
}

data class MusicDTO (
    val title: String,
    val artist: MutableLiveData<List<Artist?>> = MutableLiveData(),
    val album: MutableLiveData<List<Album?>> = MutableLiveData(),
    val duration: Int,
    val filePath: String,
    val cover: MutableLiveData<Bitmap> = MutableLiveData()
)

data class MusicRequest(
    val title: String,
    val artist: String,
    val album: String,
    val duration: Int,
    val filePath: String
)

data class MusicResponse(
    val result: Music
)

data class LibraryResponse (
    val result: List<Music>
)