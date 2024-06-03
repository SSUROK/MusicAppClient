package ru.surok.clientserverappproject.data.repos

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.surok.clientserverappproject.data.APIS.ServerApiHelper
import ru.surok.clientserverappproject.data.models.ServerResponse
import java.io.ByteArrayOutputStream

class ServerRepo {
    private val retrofit = ServerApiHelper.retrofit

    fun getLibrary(callback: (ServerResponse?) -> Unit) {
        val call = retrofit.getLibrary().enqueue(object : Callback<ServerResponse> {
            override fun onResponse(
                call: Call<ServerResponse>,
                response: Response<ServerResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val result = response.body()
                        callback(result)
                    }
                } else {
                    println(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                t.printStackTrace()
                callback(null)
            }
        })
    }

//    fun getMusicFile(callback: (File?) -> Unit) {
//        val call = retrofit.getMusicFile().enqueue(object : Callback<File> {
//            override fun onResponse(call: Call<File>, response: Response<File>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<File>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        }
//    }

    fun getMusicCover(id: Int, callback: (ByteArray?) -> Unit) {
        runBlocking {
            launch(Dispatchers.IO) {
                val call = retrofit.getSongCover(id.toString())
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