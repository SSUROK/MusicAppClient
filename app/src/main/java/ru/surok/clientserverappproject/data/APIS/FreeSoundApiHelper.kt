package ru.surok.clientserverappproject.data.APIS

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object FreeSoundApiHelper {
    var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Token rLKVWyEfGezsKKMtoi15tLI8SZgliFeciIqnL89X")
            .build()
        chain.proceed(newRequest)
    }.build()

    val retrofit: FreeSoundAPI by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://freesound.org/apiv2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FreeSoundAPI::class.java)
    }
}