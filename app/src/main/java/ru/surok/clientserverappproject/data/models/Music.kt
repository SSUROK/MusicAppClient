package ru.surok.clientserverappproject.data.models

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import ru.surok.clientserverappproject.data.repos.ServerRepo
import java.io.InputStream

data class Music (
    val id: String,
    val title: String,
    val artist_id: List<String>,
    val album_id: List<String>,
    val duration: Int,
    val filePath: String,
    val coverStream: InputStream?
){
    fun toMusicDTO(): MusicDTO {
        val repo = ServerRepo()
        return MusicDTO(
            title = title,
            artist = artist_id.map { repo.getArtist(it)!! },
            album = album_id.map  { repo.getAlbum(it)!!  },
            duration  = duration,
            filePath = filePath
        )
    }
}

data class MusicDTO (
    val title: String,
    val artist: List<Artist>,
    val album: List<Album>,
    val duration: Int,
    val filePath: String,
    val cover: MutableLiveData<Bitmap> = MutableLiveData()
)

data class LibraryResponse (
    val result: List<Music>
)