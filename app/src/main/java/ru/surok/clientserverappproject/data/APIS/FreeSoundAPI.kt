package ru.surok.clientserverappproject.data.APIS

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.surok.clientserverappproject.data.models.FreeSoundSearchResponse

interface FreeSoundAPI {

    @GET("search/text/")
    fun searchByText(
        @QueryMap options: Map<String, String>
    ): Call<FreeSoundSearchResponse>
}