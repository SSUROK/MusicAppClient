package ru.surok.clientserverappproject.data.APIS

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import retrofit2.http.Streaming
import ru.surok.clientserverappproject.data.models.FreeSoundSearchResponse
import ru.surok.clientserverappproject.data.models.MusicSample
import ru.surok.clientserverappproject.data.models.ServerResponse

interface ServerAPI {

    @GET("library")
    fun getLibrary(): Call<ServerResponse>

    @GET("song/file/{id}")
    @Streaming
    suspend fun getSongFile(
        @Path("id") id: String,
    ): ResponseBody

    @GET("song/cover/{id}")
    @Streaming
    suspend fun getSongCover(
        @Path("id") id: String,
    ): ResponseBody
}