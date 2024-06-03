package ru.surok.clientserverappproject.data.repos

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.surok.clientserverappproject.data.APIS.ServerApiHelper
import ru.surok.clientserverappproject.data.models.Album
import ru.surok.clientserverappproject.data.models.AlbumResponse
import ru.surok.clientserverappproject.data.models.Artist
import ru.surok.clientserverappproject.data.models.ArtistResponse
import ru.surok.clientserverappproject.data.models.LibraryResponse
import ru.surok.clientserverappproject.data.models.Music
import ru.surok.clientserverappproject.data.models.MusicDTO
import java.io.ByteArrayOutputStream

class ServerRepo {
    private val retrofit = ServerApiHelper.retrofit

    fun getLibrary(callback: (List<MusicDTO>?) -> Unit) {
        val call = retrofit.getLibrary().enqueue(object : Callback<LibraryResponse> {
            override fun onResponse(
                call: Call<LibraryResponse>,
                response: Response<LibraryResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val result = response.body()?.result
                        val resultDto = result?.map{ it.toMusicDTO() }
                        callback(resultDto)
                    }
                } else {
                    println(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<LibraryResponse>, t: Throwable) {
                t.printStackTrace()
                callback(null)
            }
        })
    }

    fun getArtist(id: String) : Artist?{
        var resp: Artist?  = null
        val call = retrofit.getArtist(id).enqueue(object : Callback<ArtistResponse> {
            override fun onResponse(
                call: Call<ArtistResponse>,
                response: Response<ArtistResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val result = response.body()?.result
                        resp = result
                    }
                } else {
                    println(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return resp
    }

    fun getAlbum(id: String): Album?{
        var resp: Album?  = null
        val call = retrofit.getAlbum(id).enqueue(object : Callback<AlbumResponse> {
            override fun onResponse(
                call: Call<AlbumResponse>,
                response: Response<AlbumResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val result = response.body()?.result
                        resp = result
                    }
                } else {
                    println(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return resp
    }

    fun getResource(file: String, callback: (ByteArray?) -> Unit) {
        runBlocking {
            launch(Dispatchers.IO) {
                val call = retrofit.getFile(file)
                val io = call.byteStream()
                val outputStream = ByteArrayOutputStream()
                io.copyTo(outputStream)
                val fileBytes = outputStream.toByteArray()
                withContext(Dispatchers.IO) {
                    io.close()
                    outputStream.close()
                }
                callback(fileBytes)
            }
        }
    }
}