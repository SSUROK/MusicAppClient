package ru.surok.clientserverappproject.data.APIS

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap
import retrofit2.http.Streaming
import ru.surok.clientserverappproject.data.models.Album
import ru.surok.clientserverappproject.data.models.AlbumResponse
import ru.surok.clientserverappproject.data.models.ArtistResponse
import ru.surok.clientserverappproject.data.models.LibraryResponse
import ru.surok.clientserverappproject.data.models.MusicRequest
import ru.surok.clientserverappproject.data.models.MusicResponse

interface ServerAPI {

    @GET("library")
    fun getLibrary(): Call<LibraryResponse>

    @GET("music")
    fun getMusic(): Call<MusicResponse>

    @GET("resource")
    @Streaming
    fun getFile(@QueryMap params: Map<String, String>): ResponseBody

    @GET("album")
    fun getAlbum(@QueryMap params: Map<String, String>): Call<AlbumResponse>

    @GET("artist")
    fun getArtist(@QueryMap params: Map<String, String>): Call<ArtistResponse>

    @POST("music")
    fun postMusic(@Body body: MusicRequest): Call<MusicResponse>
}