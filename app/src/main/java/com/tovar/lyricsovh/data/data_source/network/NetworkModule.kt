package com.tovar.lyricsovh.data.data_source.network

import android.util.Log
import com.tovar.lyricsovh.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor.Logger
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor.Level

class NetworkModule {

    private val TAG_NETWORK = "WS"

    fun provideRetrofit(baseURL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(
            HttpLoggingInterceptor(object: Logger {
                override fun log(message: String) {
                    if (BuildConfig.DEBUG) {
                        Log.i(TAG_NETWORK,message)
                    }
                }
            }
            ).setLevel(Level.BODY)
        )
        okHttpClientBuilder.connectTimeout(100, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(100,TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(100,TimeUnit.SECONDS)
        return  okHttpClientBuilder.build()
    }

    fun <T> provideApi(retrofit: Retrofit, service:Class<T>): T {
        return retrofit.create(service)
    }
}