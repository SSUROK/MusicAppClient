package ru.surok.clientserverappproject.data.repos

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.surok.clientserverappproject.data.APIS.FreeSoundApiHelper
import ru.surok.clientserverappproject.data.models.FreeSoundSearchResponse

class FreeSoundRepo {
    private val retrofit = FreeSoundApiHelper.retrofit
    fun searchByText(text:String, fields:String, callback: (FreeSoundSearchResponse?) -> Unit) {
        val data: MutableMap<String, String> = HashMap()
        data["query"] = text
        data["fields"] = fields
        val call = retrofit.searchByText(data).enqueue(object : Callback<FreeSoundSearchResponse> {
            override fun onResponse(
                call: Call<FreeSoundSearchResponse>,
                response: Response<FreeSoundSearchResponse>
            ) {
                if (response.isSuccessful){
                    if (response.code() == 200){
                        val result = response.body()
                        callback(result)
                    }
                } else {
                    println(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<FreeSoundSearchResponse>, t: Throwable) {
                t.printStackTrace()
                callback(null)
            }
        })
//        try {
//            val response = call.execute();
//            if (response.isSuccessful){
//                if (response.code() == 200){
//                    return response.body()
//                }
//            } else {
//                println(response.errorBody()?.string())
//            }
//        } catch (e: Exception) {
//            e.printStackTrace();
//        }
    }
}