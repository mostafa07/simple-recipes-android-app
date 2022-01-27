package com.example.android.simplerecipes.network.builder

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object RetrofitServiceBuilder {

    private const val RECIPES_BASE_URL = "https://api.npoint.io/"

    fun <S> buildService(serviceType: Class<S>): S {
        val loggingInterceptor =
            HttpLoggingInterceptor { Timber.d(it) }
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        val sOkHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(RECIPES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(sOkHttpClientBuilder.build())
            .build()
            .create(serviceType)
    }
}