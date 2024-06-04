package ru.surok.clientserverappproject.data.repos

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import ru.surok.clientserverappproject.data.models.MusicRequest
import ru.surok.clientserverappproject.data.models.MusicResponse
import java.io.ByteArrayOutputStream

class ServerRepo {
    private val retrofit = ServerApiHelper.retrofit

    private val downloadedCovers = mutableMapOf<String, Bitmap>()
    private val cachedArtists = mutableMapOf<String, Artist>()
    private val cachedAlbums  = mutableMapOf<String, Album>()

    fun getLibrary(callback: (List<MusicDTO>?) -> Unit) {
        val call = retrofit.getLibrary().enqueue(object : Callback<LibraryResponse> {
            override fun onResponse(
                call: Call<LibraryResponse>,
                response: Response<LibraryResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val result = response.body()?.result
                        if (result != null) {
                            val resultDto = result.map{it.toMusicDTO()}
                            for (i in result.indices)  {
                                if (cachedArtists.containsKey(result[i].artist_id[0])){
                                    resultDto[i].artist.postValue(listOf(cachedArtists[result[i].artist_id[0]]))
                                } else{
                                    assignArtist(result[i].artist_id[0], resultDto[i])
                                }

                                if (cachedAlbums.containsKey(result[i].albumn_id[0])){
                                    resultDto[i].album.postValue(listOf(cachedAlbums[result[i].albumn_id[0]]))
                                } else{
                                    assignAlbum(result[i].albumn_id[0], resultDto[i])
                                }

                                if (downloadedCovers.containsKey(result[i].albumn_id[0])){
                                    resultDto[i].cover.postValue(downloadedCovers[result[i].albumn_id[0]])
                                } else{
                                    resultDto[i].album.value?.get(0)
                                        ?.let { assignCover(it.cover_path, resultDto[i]) }
                                }
                            }
                            callback(resultDto)
                        }
                        callback(null)
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

    private fun assignArtist(id: String, music: MusicDTO)  {
        getArtist(id) {
            if (it != null) {
                music.artist.postValue(listOf(it))
                cachedArtists[id] = it
            }
        }
    }

    private fun assignAlbum(id: String, music: MusicDTO)  {
        getAlbum(id) {
            if (it!= null) {
                music.album.postValue(listOf(it))
                cachedAlbums[id] = it
            }
        }
    }

    private fun assignCover(path: String, music: MusicDTO)  {
        getResource(path) {
            if (it!= null) {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                music.cover.postValue(bitmap)
                downloadedCovers[path] = bitmap
            }
        }
    }

    fun getArtist(id: String, callback: (Artist?) -> Unit){
        val params = mapOf("id" to id)
        val call = retrofit.getArtist(params).enqueue(object : Callback<ArtistResponse> {
            override fun onResponse(
                call: Call<ArtistResponse>,
                response: Response<ArtistResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val result = response.body()?.result
                        callback(result)
                    }
                } else {
                    println(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                t.printStackTrace()
                callback(null)
            }
        })
    }

    fun getAlbum(id: String, callback: (Album?) -> Unit){
        val params = mapOf("id" to id)
        val call = retrofit.getAlbum(params).enqueue(object : Callback<AlbumResponse> {
            override fun onResponse(
                call: Call<AlbumResponse>,
                response: Response<AlbumResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val result = response.body()?.result
                        callback(result)
                    }
                } else {
                    println(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                t.printStackTrace()
                callback(null)
            }
        })
    }

    fun getResource(file: String, callback: (ByteArray?) -> Unit) {
        runBlocking {
            launch(Dispatchers.IO) {
                val params = mapOf("path" to file)
                val call = retrofit.getFile(params)
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

    fun addMusic(request: MusicRequest, callback:  (Music?)  -> Unit)  {
        retrofit.postMusic(request).enqueue(object  : Callback<MusicResponse>  {
            override fun onResponse(
                call: Call<MusicResponse>,
                response: Response<MusicResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val result = response.body()?.result
                        callback(result)
                    }
                } else {
                    println(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                t.printStackTrace()
                callback(null)
            }
        })
    }
}