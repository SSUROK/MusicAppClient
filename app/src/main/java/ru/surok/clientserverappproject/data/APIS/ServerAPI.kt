package ru.surok.clientserverappproject.data.APIS

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Streaming
import ru.surok.clientserverappproject.data.models.Album
import ru.surok.clientserverappproject.data.models.AlbumResponse
import ru.surok.clientserverappproject.data.models.ArtistResponse
import ru.surok.clientserverappproject.data.models.LibraryResponse

interface ServerAPI {

    @GET("library")
    fun getLibrary(): Call<LibraryResponse>

    @GET("resource")
    @Streaming
    fun getFile(@Body path: String): ResponseBody

    @GET("album")
    fun getAlbum(@Body id: String): Call<AlbumResponse>

    @GET("artist")
    fun getArtist(@Body id: String): Call<ArtistResponse>
}