package ru.surok.clientserverappproject.data.APIS

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerApiHelper {
    var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .build()
        chain.proceed(newRequest)
    }.build()

    val retrofit: ServerAPI by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl("http://192.168.0.203:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerAPI::class.java)
    }
}